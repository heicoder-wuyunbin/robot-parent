package com.wuyunbin.sso.api;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.common.Result;
import com.wuyunbin.sso.dto.LoginDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static java.util.function.Predicate.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberApiTests {
    @Resource
    private MockMvc mockMvc;

    private String authToken;

    @BeforeEach
    public void setUp() throws Exception {
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
    public void getMemberIdByToken() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/memberApi/getMemberIdByToken")
                                .header("Authorization", authToken)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1857723147531677698")); // 检查响应体的具体内容

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        log.info("contentAsString:{}", contentAsString);
    }


}
