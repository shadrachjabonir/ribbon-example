package com.metraplasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@RibbonClient(name = "a-server-app", configuration = RibbonConf.class)
public class App {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate template;

    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:6000/test");
        invoker.setServiceInterface(TestInterface.class);
        return invoker;
    }

    @GetMapping("/test")
    public String test(){
        System.out.println(template.getForObject("http://server-app/getTest",Test.class));

        return "hai";
    }


    public static void main(String[] args) {
        TestInterface service = SpringApplication.run(App.class, args).getBean(TestInterface.class);
        System.out.println(service.buildTest("Shadrach", 29) + " result");

    }

}
