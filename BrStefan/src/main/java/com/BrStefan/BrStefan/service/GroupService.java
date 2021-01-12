package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.dto.AddUserGroupDTO;
import com.BrStefan.BrStefan.domain.dto.CreateGroupDTO;
import com.BrStefan.BrStefan.repository.GroupRepository;
import com.BrStefan.BrStefan.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public void createGroup(CreateGroupDTO createGroupDTO){
        groupRepository.create(createGroupDTO);
    }

    public void addToGroup(AddUserGroupDTO addUserGroupDTO){
        groupRepository.addToGroup(addUserGroupDTO);
    }
}
