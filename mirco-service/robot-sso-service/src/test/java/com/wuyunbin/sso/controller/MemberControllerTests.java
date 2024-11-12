package com.wuyunbin.sso.controller;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.common.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void test01() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/member/test")
                ).andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(20000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result result = JSON.parseObject(contentAsString, Result.class);
        log.info("result:{}", result);
    }

    @Test
    public void findByTokenWithoutToken() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/member/findByToken")
                        .header("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjE4NTU4MjYyNzU0ODk2NDA0NDkiLCJpYXQiOjE3MzE0MDg0NjksImV4cCI6MTczMTQ0NDQ2OX0.I-mxqp5LzRc1XFVklhe8iS_cyhMol4N1wOPm4K-Z1iQ")
                        .contentType(MediaType.APPLICATION_JSON)//设置请求头格式
                        //.content(json数据)--请求体
        );
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        Result result = JSON.parseObject(contentAsString, Result.class);
        log.info("result:{}", result);
    }


}
