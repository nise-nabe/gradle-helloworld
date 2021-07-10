package com.nisecoder.helloworld.mybatis

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.cursor.Cursor

@Mapper
interface UserMapper {
    @Select("SELECT * FROM user")
    fun findAll(): Cursor<User>
}
