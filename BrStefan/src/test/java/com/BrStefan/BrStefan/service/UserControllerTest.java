package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.controller.UserController;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.UserRegisterDTO;
import com.BrStefan.BrStefan.exceptions.UserExistsException;
import com.BrStefan.BrStefan.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserRepository userRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void register() throws Exception{
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("Fane", "pass", "teste");
        User user = new User(1,"Fane", "pass", "teste", 1, null,null);
        when(userService.register(any())).thenReturn(user);

        MvcResult result = mockMvc.perform(post("/users/register")
                .content(objectMapper.writeValueAsString(userRegisterDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(objectMapper.writeValueAsString(userRegisterDTO));

        assertThat(result.getResponse().getContentAsString()).contains(objectMapper.writeValueAsString(user));
    }

    @Test
    public void registerNameTaken() throws Exception{
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("Fane", "pass", "teste");

        when(userRepository.register(any())).thenThrow(new UserExistsException("Fane"));
        when(userController.register(userRegisterDTO)).thenReturn(null);

        MvcResult result = mockMvc.perform(post("/users/register")
                .content(objectMapper.writeValueAsString(userRegisterDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

}
