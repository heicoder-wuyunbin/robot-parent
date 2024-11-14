package com.wuyunbin.sso.controller;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.common.Result;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.dto.SendSmsDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Resource
    private MockMvc mockMvc;

    private String authToken;

    @BeforeEach
    public void setUp() throws Exception {
        // Attempt to login and retrieve a valid token for authenticated tests
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        loginDTO.setPassword("123456");
        loginDTO.setType("passwordAuthService");

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(20000))
                .andExpect(jsonPath("$.data.token").exists());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result<HashMap<String, String>> result = JSON.parseObject(contentAsString, Result.class);
        this.authToken = result.getData().get("token");  // Store token for later use
        log.info("Retrieved auth token: {}", authToken);
    }

    @Test
    public void loginSuccess() throws Exception {
        // Use real user credentials
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        loginDTO.setPassword("123456");
        loginDTO.setType("passwordAuthService");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(20000))
                .andExpect(jsonPath("$.data.token").exists())
                .andReturn();
    }

    @Test
    public void loginFailure() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("admin");
        loginDTO.setPassword("121212");
        loginDTO.setType("passwordAuthService");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(40001))
                .andExpect(jsonPath("$.message").value("Authentication failed"));
    }

    @Test
    public void getCodeSuccess() throws Exception {
        SendSmsDTO sendSmsDTO = new SendSmsDTO();
        sendSmsDTO.setPhone("13812341234");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/getCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(sendSmsDTO))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(20000))
                .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    public void getCodeFailure() throws Exception {
        SendSmsDTO sendSmsDTO = new SendSmsDTO();
        sendSmsDTO.setPhone("invalid_phone");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/getCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(sendSmsDTO))
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(40002))
                .andExpect(jsonPath("$.message").value("Invalid phone number"));
    }

    @Test
    public void findByTokenSuccess() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/member/findByToken")
                                .header("Authorization", authToken)  // Use the token from setup
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(20000))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    public void findByTokenFailure() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/member/findByToken")
                                .header("Authorization", "invalid_token")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorCode").value(40003))
                .andExpect(jsonPath("$.message").value("Invalid token"));
    }

    @Test
    public void testEndpoint() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/member/test")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(20000))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}