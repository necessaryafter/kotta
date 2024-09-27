package com.kotta.core.test

import com.kotta.core.Kotta
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object TestKotta {

    @OptIn(DelicateCoroutinesApi::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val mongoClient = Kotta.createClient("127.0.0.1")
        val database = mongoClient.getDatabase("kotta-test")

        val collection = UserCollection(database)

        GlobalScope.launch {
            val user = collection.findByEmail("necessaryafter@email.com") ?: return@launch

            println(user)
        }
    }

}