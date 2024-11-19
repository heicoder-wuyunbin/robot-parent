package com.wuyunbin.apply.controller;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.apply.entity.MerchantApply;
import com.wuyunbin.common.Result;
import com.wuyunbin.sso.dto.LoginDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MerchantApplyControllerTest {
    @Resource
    private MockMvc mockMvc;
    private String authToken;

    @BeforeEach
    public void setUp() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPhone("13812341234");
        loginDTO.setPassword("123456");
        loginDTO.setType("passwordAuthService");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginDTO> entity = new HttpEntity<>(loginDTO, headers);

        log.info("Sending request to: http://robot-sso-service/member/login");
        log.info("Request body: {}", JSON.toJSONString(loginDTO));
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/member/login", entity, String.class);
        log.info("Received response: {}", response.getBody());


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        Result<HashMap<String, String>> result = JSON.parseObject(response.getBody(), Result.class);
        this.authToken = result.getData().get("token");  // Store token for later use
        log.info("Retrieved auth token: {}", authToken);
    }


    @Test
    public void applySuccess() throws Exception {
        MerchantApply merchantApply = new MerchantApply();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/merchantApply")
                .header("Authorization", authToken)
                .content(JSON.toJSONString(merchantApply))
                .contentType(MediaType.APPLICATION_JSON)
        );

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result<Object> result = JSON.parseObject(contentAsString, Result.class);
        log.info("Result: {}", result);
    }
}
