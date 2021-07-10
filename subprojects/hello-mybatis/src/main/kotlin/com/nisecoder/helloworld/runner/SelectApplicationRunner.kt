package com.nisecoder.helloworld.runner

import com.nisecoder.helloworld.mybatis.UserMapper
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SelectApplicationRunner(
        private val mapper: UserMapper
): ApplicationRunner {
    @Transactional
    override fun run(args: ApplicationArguments) {
        mapper.findAll().forEach { println(it) }
    }
}
