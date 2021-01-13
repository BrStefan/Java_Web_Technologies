package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.UserRegisterDTO;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void testCreate(){
        UserRegisterDTO user = UserRegisterDTO.builder().build();
        User user2 = User.builder().build();
        when(userRepository.register(user)).thenReturn(user2);
        User result = userService.register(user);

        assertThat(result).isNotNull();
    }

}