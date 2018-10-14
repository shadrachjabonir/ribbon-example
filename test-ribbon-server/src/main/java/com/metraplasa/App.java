package com.metraplasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class App {
    @Bean(name = "/test")
    HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService( new TestImpl() );
        exporter.setServiceInterface( TestInterface.class );
        return exporter;
    }

    @GetMapping("/getTest")
    public Test getTest(){
        Test test = new Test();
        test.setName("hai");
        test.setAge(1);
        return test;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
