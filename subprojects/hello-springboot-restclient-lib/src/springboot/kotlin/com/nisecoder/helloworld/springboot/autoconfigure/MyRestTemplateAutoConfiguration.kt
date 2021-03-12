package com.nisecoder.helloworld.springboot.autoconfigure

import com.nisecoder.helloworld.spring.restclient.MyRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyRestTemplateAutoConfiguration {
    @Bean
    fun myRestTemplate(restTemplateBuilder: RestTemplateBuilder): MyRestTemplate {
        return MyRestTemplate(restTemplateBuilder.build())
    }
}
