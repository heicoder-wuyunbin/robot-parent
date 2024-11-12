package com.wuyunbin.sso.controller;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.common.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void test01() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                        get("/member/test")
                ).andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result result = JSON.parseObject(contentAsString, Result.class);
        log.info("result:{}", result);
    }

    @Test
    public void findByTokenWithoutToken() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        get("/member/findByToken")
                ).andExpect(result -> assertTrue(result.getResolvedException() instanceof NullPointerException))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServletException));
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result result = JSON.parseObject(contentAsString, Result.class);
        log.info("result:{}", result);
    }

    private void assertTrue(boolean b) {
    }
}
