package com.wuyunbin.sso.controller;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.common.Result;
import com.wuyunbin.common.exceptions.enums.RespEnum;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.dto.SendSmsDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Duration;
import java.util.HashMap;
import java.util.stream.IntStream;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Resource
    private MockMvc mockMvc;

    private String authToken;

    @BeforeEach
    public void setUp(TestInfo testInfo) throws Exception {

        if (testInfo.getTestMethod().isPresent() &&
            testInfo.getTestMethod().get().getName().equals("loginSuccess")) {
            // Skip setUp for loginSuccess
            return;
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        loginDTO.setPassword("123456");
        loginDTO.setType("passwordAuthService");

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.token").exists());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result<HashMap<String, String>> result = JSON.parseObject(contentAsString, Result.class);
        this.authToken = result.getData().get("token");  // Store token for later use
        log.info("Retrieved auth token: {}", authToken);
    }



    @Test
    public void loginSuccess() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        loginDTO.setPassword("123456");
        loginDTO.setType("passwordAuthService");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.SUCCESS.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.SUCCESS.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.token").exists())
                .andReturn();
    }

    @Test
    public void loginFailureWithWrongPassword() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("admin");
        loginDTO.setPassword("121212");
        loginDTO.setType("passwordAuthService");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.INVALID_ACCOUNT.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.INVALID_ACCOUNT.getMessage()));
    }

    @Test
    public void loginFailureWithoutRequestData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/member/login"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 400 response status is expected here for missing data
                .andExpect(result -> Assertions.assertInstanceOf(HttpMessageNotReadableException.class, result.getResolvedException()));

    }

    @Test
    public void getCodeSuccess() throws Exception {
        SendSmsDTO sendSmsDTO = new SendSmsDTO();
        sendSmsDTO.setPhone("13812341234");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/getCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(sendSmsDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.SUCCESS.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.SUCCESS.getMessage()));
    }

    @Test
    public void getCodeFailureWithInvalidPhone() throws Exception {
        SendSmsDTO sendSmsDTO = new SendSmsDTO();
        sendSmsDTO.setPhone("1381234123");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/getCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(sendSmsDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.INVALID_PHONE.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.INVALID_PHONE.getMessage()));
    }

    @Test
    public void findByTokenFailure() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/member/findByToken")
                                .header("Authorization", "invalid_token")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.TOKEN_MALFORMED.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.TOKEN_MALFORMED.getMessage()));
    }


    @Test
    public void findByTokenSuccess() throws Exception {
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(200), () -> {
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/member/findByToken")
                                    .header("Authorization", authToken)  // Use the token from setup
                                    .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.SUCCESS.getCode()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
        });
    }

    // 简易压力测试：重复10次登录请求，模拟压力测试
    @RepeatedTest(10)
    public void repeatedLoginSuccessTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        loginDTO.setPassword("123456");
        loginDTO.setType("passwordAuthService");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.token").exists());
    }

    @Test
    public void getCodeTooFrequently() throws Exception {
        SendSmsDTO sendSmsDTO = new SendSmsDTO();
        sendSmsDTO.setPhone("13812341234");

        for (int i = 0; i < 10; i++) {
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/member/getCode")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(sendSmsDTO))
            ).andExpect(MockMvcResultMatchers.status().isOk());
        }

        // Expect an error indicating rate limiting on further attempts
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/getCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(sendSmsDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.TOO_MANY_REQUESTS.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.TOO_MANY_REQUESTS.getMessage()));
    }

    @Test
    public void loginFailureMissingFields() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        // Missing password and type fields

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.INVALID_PARAMETERS.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.INVALID_PARAMETERS.getMessage()));
    }

    @Test
    public void loginFailureWithEmptyFields() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("");
        loginDTO.setPassword("");
        loginDTO.setType("passwordAuthService");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/member/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.INVALID_ACCOUNT.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.INVALID_ACCOUNT.getMessage()));
    }


    @Test
    public void findByTokenWithoutAuthToken() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/member/findByToken")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(RespEnum.TOKEN_MISSING.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(RespEnum.TOKEN_MISSING.getMessage()));
    }

    //并发测试
    @RepeatedTest(5)
    public void concurrentLoginTest() {
        IntStream.range(0, 10).parallel().forEach(i -> {
            try {
                loginSuccess();
            } catch (Exception e) {
                Assertions.fail("Concurrent login failed", e);
            }
        });
    }
}