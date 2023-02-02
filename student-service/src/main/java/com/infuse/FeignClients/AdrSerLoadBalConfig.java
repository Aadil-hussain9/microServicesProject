//package com.infuse.FeignClients;
//
//import feign.Feign;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//
//@LoadBalancerClient(value = "ADDRESS-SERVICE")
//public class AdrSerLoadBalConfig {
//
//    @LoadBalanced
//    @Bean
//    public Feign.Builder feignBuilder()
//    {
//        return Feign.builder();
//    }
//}
