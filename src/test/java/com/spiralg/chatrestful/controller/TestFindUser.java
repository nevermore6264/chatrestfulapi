package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestFindUser {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks( this );
        mockMvc = MockMvcBuilders
                .standaloneSetup( userController )
                .build();
    }

    @Test
    public void findUserAndReturnRecord() throws Exception {

        List<User> users = new ArrayList<>();
        users.add( new User( 1, "dat", (byte) 0 ) );
        given( userService.findAllByName( "dat" ) ).willReturn( users );

        mockMvc.perform( get( "/api/users" ).param( "username", "dat" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 1 ) ) ) )
                .andExpect( jsonPath( "$.items[0].userName", is( "dat" ) ) );
    }

    @Test
    public void findUserAndReturnNoRecord() throws Exception {
        List<User> users = new ArrayList<>();
        given( userService.findAllByName( "khanhtx" ) ).willReturn( users );

        mockMvc.perform( get( "/api/users" ).param( "username", "khanhtx" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void findUserWithWhiteSpace() throws Exception {

        List<User> users = new ArrayList<>();
        users.add( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findAllByName( "" ) ).willReturn( users );

        mockMvc.perform( get( "/api/users" ).param( "username", " " )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 1 ) ) ) )
                .andExpect( jsonPath( "$.items[0].userName", is( "datnt" ) ) );
    }

    @Test
    public void findUserWithALotWhiteSpacce() throws Exception {

        List<User> users = new ArrayList<>();
        users.add( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findAllByName( "" ) ).willReturn( users );

        mockMvc.perform( get( "/api/users" ).param( "username", "      " )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 1 ) ) ) )
                .andExpect( jsonPath( "$.items[0].userName", is( "datnt" ) ) );
    }

    @Test
    public void findUserWithSpecialCharacter() throws Exception {

        List<User> users = new ArrayList<>();
        given( userService.findAllByName( "@#$" ) ).willReturn( users );

        mockMvc.perform( get( "/api/users" ).param( "username", "@#$" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void findUserWithIntegerNumber() throws Exception {

        List<User> users = new ArrayList<>();

        mockMvc.perform( get( "/api/users" ).param( "username", "6" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void findUserWithDoubleNumber() throws Exception {

        List<User> users = new ArrayList<>();

        mockMvc.perform( get( "/api/users" ).param( "username", "6.5" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

//    @Test
//    public void findUserWithNull() throws Exception {
//
//        List<User> users = new ArrayList<>();
//
//        mockMvc.perform( get( "/api/users" ).param( "username", null )
//                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
//                .andExpect( status().isOk() )
//                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
//    }
}