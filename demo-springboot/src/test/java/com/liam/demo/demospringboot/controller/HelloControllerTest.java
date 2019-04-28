package com.liam.demo.demospringboot.controller;

import com.liam.demo.demospringboot.DemoSpringbootApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author Liam
 * @date 2019/4/25 下午8:35
 */
public class HelloControllerTest extends DemoSpringbootApplicationTests {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void hello() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name=哈哈")
        .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }
}