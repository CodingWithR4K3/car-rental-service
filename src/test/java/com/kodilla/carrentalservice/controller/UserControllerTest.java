package com.kodilla.carrentalservice.controller;

import com.kodilla.carrentalservice.dto.UserDto;
import com.kodilla.carrentalservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldFetchUserById() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userService.getUserById(1L)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Konrad")));
    }

    @Test
    public void shouldFetchUserByEmail() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userService.getUserByEmail("sampleEmail")).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/users/by_email/sampleEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("email", "sampleEmail"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.email", is("sampleEmail")));
    }

    @Test
    public void shouldFetchUserByPhoneNumber() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userService.getUserByPhoneNumber(1234)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/users/by_phone/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("number", String.valueOf(1234)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.phoneNumber", is(1234)));
    }

    @Test
    public void shouldFetchAllUsers() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        List<UserDto> userDtoList = Collections.singletonList(userDto);
        when(userService.getAllUsers()).thenReturn(userDtoList);

        //When & Then
        mockMvc.perform(get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Konrad")))
                .andExpect(jsonPath("$[0].phoneNumber", is(1234)));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200));
    }

    private UserDto createSampleUser() {
        return new UserDto(
                1L,
                "Konrad",
                "Gorgol",
                "sampleEmail",
                "password",
                1234,
                LocalDate.now());
    }
}