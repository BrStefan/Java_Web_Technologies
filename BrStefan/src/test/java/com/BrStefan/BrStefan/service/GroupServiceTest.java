package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Group;
import com.BrStefan.BrStefan.domain.UserGroup;
import com.BrStefan.BrStefan.domain.dto.*;
import com.BrStefan.BrStefan.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GroupServiceTest {

    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;


    @Test
    void testCreate(){

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(1,"Grupa");
        Group group = new Group(1,1,"Grupa");

        groupRepository.create(createGroupDTO);
        assertThat(group.getName()).isEqualTo(createGroupDTO.getName());
    }

    @Test
    void testAdd(){

        AddUserGroupDTO addUserGroupDTO = new AddUserGroupDTO(1,2,1);
        UserGroup userGroup = new UserGroup(1, 2,1);

        groupRepository.addToGroup(addUserGroupDTO);
        assertThat(userGroup.getGroup_id()).isEqualTo(addUserGroupDTO.getGroup_id());
    }



}