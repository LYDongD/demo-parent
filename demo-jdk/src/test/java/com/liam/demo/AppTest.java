package com.liam.demo;


import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

//    //添加tag后，使用maven插件执行测试，可根据tag过滤,例如 mvn test -Dgroups=group3,group2
//    //支持-DincludeGroups，-DexcludeGroups
//    @Test
//    @Tags({@Tag("tagDemo"), @Tag("test-tag")})
//    @DisplayName("测试1")
//    void test() {
//        fail("not yet implemented");
//    }
//
//    //@DisplayName用于定义测试用例的名称，在idea中调试时可读性较强
//    @Test
//    @Tags({@Tag("tagDemo2"), @Tag("test-tag")})
//    @DisplayName("测试2")
//    void test2() {
//        System.out.println("hello");
//        Assert.assertTrue(true);
//    }
//
//
//    //参数化测试，从csv文件读取参数, 逐行读取并进行测试
//    @ParameterizedTest
//    @CsvFileSource(resources = "/testcase/test.csv")
//    void testCSV(long id, String name) {
//        System.out.println(id);
//        System.out.println(name);
//    }


}
