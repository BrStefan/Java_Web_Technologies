package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.RankUpDTO;
import com.BrStefan.BrStefan.domain.dto.UserLoginDTO;
import com.BrStefan.BrStefan.domain.dto.UserRegisterDTO;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void testRegisterGood(){

        UserRegisterDTO userDTO = new UserRegisterDTO("Fane", "passs", "Nume");
        User user = new User(1, "Fane", "passs", "Nume", 1, null, null);
        when(userRepository.getUser(userDTO.getUsername())).thenReturn(null);
        when(userRepository.register(userDTO)).thenReturn(user);

        User result = userService.register(userDTO);
        assertThat(result.getFull_name()).isEqualTo(user.getFull_name());
    }

    @Test
    void testRegisterTakenUsername(){

        UserRegisterDTO userDTO = new UserRegisterDTO("Fane", "passs", "Nume");
        User user = new User(1, "Fane", "passs1", "Nume1", 1, null, null);
        when(userRepository.register(userDTO)).thenReturn(user);
        when(userRepository.getUser(userDTO.getUsername())).thenReturn(user);

        User result = userService.register(userDTO);
        assertThat(result.getPass()).isEqualTo(user.getPass());
    }

    @Test
    void testLogin(){
        UserLoginDTO userDTO = new UserLoginDTO("Fane", "passs");
        User user = new User(1, "Fane", "passs", "Nume", 1, null, null);
        when(userRepository.login(userDTO)).thenReturn(user);

        User res = userService.login(userDTO);
        assertThat(res.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void testLoginFailed(){
        UserLoginDTO userDTO = new UserLoginDTO("Fane", "passs");
        when(userRepository.login(userDTO)).thenReturn(null);

        User res = userService.login(userDTO);
        assertThat(res).isEqualTo(null);
    }

    @Test
    void testRankUp(){
        RankUpDTO rankUpDTO = new RankUpDTO("1", "2");
        User user = new User(2, "Fane", "passs", "Nume", 2, null, null);

        userService.rankUp(rankUpDTO);
        assertThat(user.getRole()).isEqualTo(2);

    }

    @Test
    void testRankUpFail(){
        RankUpDTO rankUpDTO = new RankUpDTO("1", "2");
        User user = new User(2, "Fane", "passs", "Nume", 1, null, null);

        userService.rankUp(rankUpDTO);
        assertThat(user.getRole()).isEqualTo(1);

    }

}