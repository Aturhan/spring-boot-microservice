package com.abdullahturhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaxiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaxiServiceApplication.class , args);
    }
}
