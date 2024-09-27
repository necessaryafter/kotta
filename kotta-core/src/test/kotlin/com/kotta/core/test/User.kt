package com.kotta.core.test

import com.kotta.core.Document
import com.kotta.core.Index
import com.kotta.core.Property

@Document("users")
@Index("name", unique = true)
@Index("email", unique = true)
@Index("user_age", unique = true)
data class User(
    @Property("name", required = true) val name: String,
    @Property("user_age", required = true) val age: Int,
    @Property("email") val email: String,
)
