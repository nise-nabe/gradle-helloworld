package com.nisecoder.helloworld.webmvc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.router

@Configuration
class ZipkinApplicationRouter {
    @Bean
    fun zipkinAppRouter(helloHandler: HelloHandler) = router {
        GET("") { helloHandler.handle() }
    }
}
