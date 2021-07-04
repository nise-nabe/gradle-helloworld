package com.nisecoder.helloworld

import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

fun mysql(tag: String = "8.0", opts: JdbcDatabaseContainer<Nothing>.() -> Unit) =
        MySQLContainer<Nothing>(DockerImageName.parse("mysql").withTag(tag)).apply(opts)
