package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.ConfirmReservationDTO;
import com.BrStefan.BrStefan.domain.dto.HonorDTO;
import com.BrStefan.BrStefan.domain.dto.ReservationDTO;
import com.BrStefan.BrStefan.exceptions.AddReservationException;
import com.BrStefan.BrStefan.exceptions.ConfirmReservationException;
import com.BrStefan.BrStefan.exceptions.HonorException;
import com.BrStefan.BrStefan.exceptions.StaffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Reservation add(ReservationDTO reservation){
        String sql = "SELECT * FROM reservations WHERE reservations.owner = ? AND reservations.reservation_date = ?";
        RowMapper<Reservation> rowMapper = buildReservation();
        List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper, reservation.getOwner(), reservation.getReservation_date());
        System.out.println(sql);
        if(reservations.size() > 0){
            throw new AddReservationException("You already have a book for this date");
        }
        else {
            sql = "SELECT * FROM reservations WHERE reservations.table_id = ? AND reservations.reservation_date = ?";
            reservations = jdbcTemplate.query(sql, rowMapper, reservation.getTable(), reservation.getReservation_date());
            if (reservations.size() >0){
                throw new AddReservationException("Table already taken!");
            }
            else {
                sql = "INSERT INTO reservations (owner, reservation_date, number_of_guests, confirmed, honored, table_id) VALUES (?,?,?,0,0,?)";
                jdbcTemplate.update(sql, reservation.getOwner(), reservation.getReservation_date(), reservation.getNumber_of_guests(), reservation.getTable());
                return getLastReservation();
            }
        }
    }

    public List<Reservation> getAll(int user_id){
        String sql = "SELECT * FROM users WHERE users.Id = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .role(resultSet.getInt("Role"))
                .build();
        List<User> users = jdbcTemplate.query(sql, rowMapper, user_id);
        if (users.size() == 0 || users.get(0).getRole() != 2){
            throw new StaffException("You are not a member of staff");
        }
        else{
            sql = "SELECT * FROM reservations";
            RowMapper<Reservation> rowMapper1 = buildReservation();
            List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper1);
            return getForToday(reservations);
        }

    }

    public List<Reservation> getForDay(int user_id){
        System.out.println(user_id);
        String sql = "SELECT * FROM reservations WHERE reservations.owner = ?";
        RowMapper<Reservation> rowMapper = buildReservation();
        List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper, user_id);
        return getForToday(reservations);
    }

    private List<Reservation> getForToday(List<Reservation> reservations){
        List<Reservation> today = new ArrayList<>();
        for (Reservation reservation : reservations){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reservation.getReservation_date());
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            if (day == dayOfMonth){
                today.add(reservation);
            }
        }
        return today;
    }

    public Reservation getLastReservation(){
        String selectSql = "SELECT * FROM reservations ORDER BY reservations.Id DESC LIMIT 1";

        RowMapper<Reservation> rowMapper = buildReservation();

        List<Reservation> reservations = jdbcTemplate.query(selectSql, rowMapper);

        try {
            return reservations.get(0);
        }catch (Exception e) {
            return null;
        }
    }

    private RowMapper<Reservation> buildReservation(){
        RowMapper<Reservation> rowMapper = (resultSet, rowNo) -> Reservation.builder()
                .id(resultSet.getString("Id"))
                .owner(resultSet.getString("owner"))
                .reservation_date(resultSet.getDate("reservation_date"))
                .number_of_guests(resultSet.getInt("number_of_guests"))
                .confirmed(resultSet.getBoolean("confirmed"))
                .honored(resultSet.getBoolean("honored"))
                .table(resultSet.getInt("table_id"))
                .build();
        return rowMapper;
    }

    public void honor(HonorDTO honorDTO){
        String sql = "SELECT * FROM users WHERE users.Id = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .role(resultSet.getInt("Role"))
                .build();
        List<User> users = jdbcTemplate.query(sql, rowMapper, honorDTO.getUser_id());
        if(users.size() == 0)
            throw new HonorException("Staff id not found!");
        else {
            if (users.get(0).getRole() != 2)
                throw new HonorException("You are not a staff member!");
            else {
                sql = "SELECT * FROM reservations WHERE reservations.Id = ?";
                RowMapper<Reservation> rowMapper1 = buildReservation();
                List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper1, honorDTO.getReservation_id());
                if (reservations.size() == 0)
                    throw new HonorException("Reservation not found!");
                else {
                    sql = "UPDATE reservations SET reservations.honored=True WHERE reservations.Id = ?";
                    jdbcTemplate.update(sql, honorDTO.getReservation_id());
                }
            }
        }
    }

    public void confirm(ConfirmReservationDTO reservation){
        String sql = "SELECT * FROM reservations WHERE reservations.owner = ? AND reservations.Id = ?";
        RowMapper<Reservation> rowMapper = buildReservation();
        List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper, reservation.getOwner(), reservation.getReservation_id());
        if (reservations.size() == 0){
            throw new ConfirmReservationException("You don't own this reservation");
        }
        else {
            sql = "UPDATE reservations SET reservations.confirmed=True WHERE reservations.Id = ?";
            jdbcTemplate.update(sql, reservation.getReservation_id());
        }
    }

    public void reject(ConfirmReservationDTO reservation){
        String sql = "SELECT * FROM reservations WHERE reservations.owner = ? AND reservations.Id = ?";
        RowMapper<Reservation> rowMapper = buildReservation();
        List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper, reservation.getOwner(), reservation.getReservation_id());
        if (reservations.size() == 0){
            throw new ConfirmReservationException("You don't own this reservation");
        }
        else {
            sql = "DELETE from reservations WHERE reservations.Id = ?";
            jdbcTemplate.update(sql, reservation.getReservation_id());
        }
    }


}

