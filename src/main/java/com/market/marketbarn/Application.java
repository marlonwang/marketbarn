package com.market.marketbarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * market
 * @author wangwei
 * @version 0.0.1
 * @date 2015.4.24
 */
@EnableAutoConfiguration
@ImportResource("classpath:spring.xml")
public class Application 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(Application.class, args);
    }
}
