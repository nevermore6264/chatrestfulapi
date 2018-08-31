package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.service.MessageService;
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
public class TestMessage {

    private MockMvc mockMvc;

    @Mock
    private MessageService messageService;

    @Mock
    private UserService userService;

    @InjectMocks
    private MessageController messageController;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks( this );
        mockMvc = MockMvcBuilders
                .standaloneSetup( messageController )
                .build();
    }

    @Test
    public void getMessageByCorrectDateHaveResult() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add( new Message( 1, "hello", "2018-08-08", "datnt", "khoand" ) );

        given( userService.findByName( "datnt" ) ).willReturn( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findByName( "khoand" ) ).willReturn( new User( 2, "khoand", (byte) 0 ) );
        given( messageService.getAll( "datnt", "khoand", "2018-08-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages" ).param( "sender", "datnt" ).param( "receiver", "khoand" ).param( "date", "2018-08-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 1 ) ) ) )
                .andExpect( jsonPath( "$.items[0].sender", is( "datnt" ) ) )
                .andExpect( jsonPath( "$.items[0].receiver", is( "khoand" ) ) )
                .andExpect( jsonPath( "$.items[0].message", is( "hello" ) ) )
                .andExpect( jsonPath( "$.items[0].id", is( 1 ) ) );
    }

    @Test
    public void getMessageByCorrectDateHaveNoResult() throws Exception {
        List<Message> messages = new ArrayList<>();

        given( messageService.getAllByDay( "2018-09-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages/date" ).param( "date", "2018-09-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void getMessageWithOnlyDate() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add( new Message( 1, "hello", "2018-08-08", "datnt", "khoand" ) );

        given( messageService.getAllByDay( "2018-08-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages/date" ).param( "date", "2018-08-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 1 ) ) ) )
                .andExpect( jsonPath( "$.items[0].sender", is( "datnt" ) ) )
                .andExpect( jsonPath( "$.items[0].receiver", is( "khoand" ) ) )
                .andExpect( jsonPath( "$.items[0].message", is( "hello" ) ) )
                .andExpect( jsonPath( "$.items[0].id", is( 1 ) ) );
    }

    @Test
    public void getMessageWithOnlySender() throws Exception {
        List<Message> messages = new ArrayList<>();

        messages.add( new Message( 1, "hello", "2018-08-08", "datnt", "khoand" ) );

        given( userService.findByName( "datnt" ) ).willReturn( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findByName( "" ) ).willReturn( new User( 2, "khoand", (byte) 0 ) );
        given( messageService.getAll( "datnt", "", "" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages/" ).param( "sender", "datnt" ).param( "receiver", "" ).param( "date", "" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 1 ) ) ) )
                .andExpect( jsonPath( "$.items[0].sender", is( "datnt" ) ) )
                .andExpect( jsonPath( "$.items[0].receiver", is( "khoand" ) ) )
                .andExpect( jsonPath( "$.items[0].message", is( "hello" ) ) )
                .andExpect( jsonPath( "$.items[0].id", is( 1 ) ) );
    }

    @Test
    public void getMessageWithOnlyReceiver() throws Exception {
        List<Message> messages = new ArrayList<>();

        given( userService.findByName( "" ) ).willReturn( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findByName( "khoand" ) ).willReturn( new User( 2, "khoand", (byte) 0 ) );

        mockMvc.perform( get( "/api/messages/" ).param( "sender", "" ).param( "receiver", "khoand" ).param( "date", "" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void getMessageWithWrongFormatDate() throws Exception {
        List<Message> messages = new ArrayList<>();

        given( messageService.getAllByDay( "08-08-2018" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages/date" ).param( "date", "08-08-2018" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isBadRequest() );
    }

    @Test
    public void getMessageWithWrongSender() throws Exception {
        List<Message> messages = new ArrayList<>();

        given( userService.findByName( "khoand" ) ).willReturn( new User( 2, "khoand", (byte) 0 ) );
        given( messageService.getAll( "hieutt", "khoand", "2018-08-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages" ).param( "sender", "hieutt" ).param( "receiver", "khoand" ).param( "date", "2018-08-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void getMessageWithWrongReceiver() throws Exception {
        List<Message> messages = new ArrayList<>();

        given( userService.findByName( "datnt" ) ).willReturn( new User( 1, "datnt", (byte) 0 ) );
        given( messageService.getAll( "datnt", "hieutt", "2018-08-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages" ).param( "sender", "datnt" ).param( "receiver", "hieutt" ).param( "date", "2018-08-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect(status().isOk())
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void getMessageWithSenderIsWhiteSpace() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add( new Message( 1, "hello", "2018-08-08", "datnt", "khoand" ) );

        given( messageService.getAll( "", "khoand", "2018-08-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages" ).param( "sender", " " ).param( "receiver", "khoand" ).param( "date", "2018-08-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }
    @Test
    public void getMessageWithReceiverIsWhiteSpace() throws Exception{
        List<Message> messages = new ArrayList<>();

        given( userService.findByName( "datnt" ) ).willReturn( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findByName( "khoand" ) ).willReturn( new User( 2, "khoand", (byte) 0 ) );
        given( messageService.getAll( "datnt", "", "2018-08-08" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages" ).param( "sender", "datnt" ).param( "receiver", " " ).param( "date", "2018-08-08" )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }

    @Test
    public void getMessageWithDateIsWhiteSpace() throws Exception{
        List<Message> messages = new ArrayList<>();

        given( userService.findByName( "datnt" ) ).willReturn( new User( 1, "datnt", (byte) 0 ) );
        given( userService.findByName( "khoand" ) ).willReturn( new User( 2, "khoand", (byte) 0 ) );
        given( messageService.getAll( "datnt", "khoand", "" ) ).willReturn( messages );

        mockMvc.perform( get( "/api/messages" ).param( "datnt", "datnt" ).param( "receiver", "khoand" ).param( "date", " " )
                .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.total", is( Integer.valueOf( 0 ) ) ) );
    }
}
