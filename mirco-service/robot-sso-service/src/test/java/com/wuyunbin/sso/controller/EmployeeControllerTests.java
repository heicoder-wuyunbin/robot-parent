package com.wuyunbin.sso.controller;
//package com.wuyunbin.controller;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.TypeReference;
//import com.wuyunbin.PageResult;
//import com.wuyunbin.Result;
//import com.wuyunbin.dto.EmployeeInfoPageQueryDTO;
//import com.wuyunbin.dto.EmployeeLoginDTO;
//import com.wuyunbin.entity.EmployeeInfo;
//import com.wuyunbin.exceptions.enumeration.ResponseEnum;
//import com.wuyunbin.vo.EmployeeLoginVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertInstanceOf;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Slf4j
//@SpringBootTest
//@AutoConfigureMockMvc
//public class EmployeeControllerTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    private static String token = "";
//
//    @BeforeEach
//    public void before(TestInfo testInfo) throws Exception {
//        /*
//        如果方法在白名单则跳过
//         */
//        List<String> whileList=List.of(
//                "loginSuccess()",
//                "loginWithWrongPassword()",
//                "loginWithoutPassword()",
//                "loginWithoutUsername()",
//                "loginWithoutRequestBody()"
//        );
//
//        if (whileList.contains(testInfo.getDisplayName())) {
//            log.info("跳过");
//            return;
//        }
//
//        EmployeeLoginDTO employeeLoginDTO = new EmployeeLoginDTO();
//        employeeLoginDTO.setUsername("admin");
//        employeeLoginDTO.setPassword("123456");
//
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .post("/employee/login")
//                        .contentType(MediaType.APPLICATION_JSON)//设置请求头，声明内容为json格式
//                        .content(JSON.toJSONString(employeeLoginDTO))//设置请求体
//        );
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        Result<EmployeeLoginVO> result = JSON.parseObject(contentAsString, new TypeReference<Result<EmployeeLoginVO>>() {
//        });
//        String token = result.getData().getToken();
//        log.info("token:{}", token);
//        EmployeeControllerTests.token = token;
//    }
//
//    @Test
//    public void loginSuccess() throws Exception {
//        EmployeeLoginDTO employeeLoginDTO = new EmployeeLoginDTO();
//        employeeLoginDTO.setUsername("admin");
//        employeeLoginDTO.setPassword("123456");
//
//        ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/employee/login")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JSON.toJSONString(employeeLoginDTO))
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(ResponseEnum.SUCCESS.getCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ResponseEnum.SUCCESS.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
//                ;
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        log.info("contentAsString:{}", contentAsString);
//        Result<EmployeeLoginVO> result = JSON.parseObject(contentAsString, new TypeReference<Result<EmployeeLoginVO>>() {
//        });
//        String token = result.getData().getToken();
//        log.info("token:{}", token);
//    }
//
//    @Test
//    public void loginWithWrongPassword() throws Exception {
//        EmployeeLoginDTO employeeLoginDTO = new EmployeeLoginDTO();
//        employeeLoginDTO.setUsername("admin");
//        employeeLoginDTO.setPassword("1234567");
//
//        ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/employee/login")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JSON.toJSONString(employeeLoginDTO))
//                                .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(ResponseEnum.INVALID_ACCOUNT.getCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ResponseEnum.INVALID_ACCOUNT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        log.info("contentAsString:{}", contentAsString);
//    }
//
//    @Test
//    public void loginWithoutPassword() throws Exception {
//        EmployeeLoginDTO employeeLoginDTO = new EmployeeLoginDTO();
//        employeeLoginDTO.setUsername("admin");
//
//        ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/employee/login")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JSON.toJSONString(employeeLoginDTO))
//                                .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(ResponseEnum.INVALID_ACCOUNT.getCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ResponseEnum.INVALID_ACCOUNT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        log.info("contentAsString:{}", contentAsString);
//    }
//
//    @Test
//    public void loginWithoutUsername() throws Exception {
//        EmployeeLoginDTO employeeLoginDTO = new EmployeeLoginDTO();
//        employeeLoginDTO.setPassword("123456");
//        //断言抛异常 MethodArgumentNotValidException
//        ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/employee/login")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JSON.toJSONString(employeeLoginDTO))
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(ResponseEnum.INVALID_PARAM.getCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ResponseEnum.INVALID_PARAM.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0]").value("用户名不能为空"));
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        log.info("contentAsString:{}", contentAsString);
//    }
//
//    @Test
//    public void loginWithoutRequestBody() throws Exception {
//
//        //断言抛异常 MethodArgumentNotValidException
//        ResultActions resultActions = mockMvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/employee/login")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(result -> assertInstanceOf(HttpMessageNotReadableException.class, result.getResolvedException()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(ResponseEnum.ERROR.getCode()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(ResponseEnum.ERROR.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        log.info("contentAsString:{}", contentAsString);
//    }
//    @Test
//    public void page() throws Exception {
//        EmployeeInfoPageQueryDTO employeePageQueryDTO = new EmployeeInfoPageQueryDTO();
//        employeePageQueryDTO.setCurrent(1L);
//        employeePageQueryDTO.setSize(10L);
//
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post("/employee/page").contentType(MediaType.APPLICATION_JSON)
//                        .content(JSON.toJSONString(employeePageQueryDTO))
//                        .header("Authorization", token)//设置token请求头
//        );
//
//
//        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
//        PageResult<EmployeeInfo> result = JSON.parseObject(
//                contentAsString, new TypeReference<PageResult<EmployeeInfo>>() {
//                }
//        );
//
//        log.info("result:{}", result.getData());
//    }
//
//    @Test
//    public void test() throws Exception {
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .post("/employee/test")
//                        .param("id", "1")
//        );
//    }
//
////    @Test
////    public void test2() {
////        Employee employee = new Employee();
////        employee.setId("10");
////        employee.setUsername("admin");
////
////        Mockito.when(employeeController.test2("10")).thenReturn(Result.success(employee));
////        Result<Employee> e = employeeController.test2("10");
////        log.info("{}",e);
////
////        Mockito.when(employeeController.test2("20")).thenReturn(Result.error(ResponseEnum.DISABLED_ACCOUNT));
////        Result<Employee> e = employeeController.test2("20");
////        log.info("{}",e);
////    }
//}
