package com.kotta.core.test

import com.kotta.core.KottaCollection
import com.kotta.core.ext.eq
import com.mongodb.client.MongoDatabase

class UserCollection(database: MongoDatabase): KottaCollection<User>(User::class, database) {

    suspend fun findByEmail(email: String) = findBy("email" eq email)

    suspend fun save(user: User) = insert(user)

}
