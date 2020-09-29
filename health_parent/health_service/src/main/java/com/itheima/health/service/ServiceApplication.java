package com.itheima.health.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author：qiuchenyang
 * @Date：2020/9/19 20:04
 */
public class ServiceApplication {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:applicationContext-service.xml");
        System.in.read();
    }
}
