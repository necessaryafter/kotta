package com.kotta.core

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.UuidRepresentation
import java.util.concurrent.TimeUnit.MILLISECONDS

object Kotta {

    fun createClient(url: String, settings: MongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(url))
        .uuidRepresentation(UuidRepresentation.STANDARD)
        .applyToConnectionPoolSettings {
            it.maxSize(20).minSize(1).maxConnecting(3).maxConnectionIdleTime(0, MILLISECONDS)
        }
        .retryWrites(true)
        .build()) : MongoClient {
        return MongoClients.create(settings)
    }

}