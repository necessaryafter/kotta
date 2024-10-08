package com.kotta.core

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes
import com.mongodb.client.model.InsertOneOptions
import com.mongodb.client.model.UpdateOptions
import com.mongodb.internal.client.model.FindOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.conversions.Bson
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.findAnnotations

/**
 * TODO LIST
 * - Add support to many operations
 * - Add support to replace operations
 * - Add support to delete operations
 */

abstract class KottaCollection<T: Any>(private val clazz: KClass<T>, private val database: MongoDatabase) {

    private val collectionName: String by lazy {
        clazz.findAnnotation<com.kotta.core.Document>()!!.collectionName
    }

    private val collection by lazy {
        database.getCollection(collectionName)
    }

    init {
        clazz.findAnnotations<Index>().forEach { index ->
            val indexBson = if (index.ascending) {
                Indexes.ascending(index.fieldName)
            } else Indexes.descending(index.fieldName)

            val indexOptions = IndexOptions()
                .unique(index.unique)
                .sparse(index.sparse)
                .background(index.background)
                .hidden(index.hidden)

            if (index.expireAfterSeconds != -1L) {
                indexOptions.expireAfter(index.expireAfterSeconds, TimeUnit.SECONDS)
            }

            if (index.min != -1.0) {
                indexOptions.min(index.min)
            }

            if (index.max != -1.0) {
                indexOptions.max(index.max)
            }

            if (index.defaultLanguage != "") {
                indexOptions.defaultLanguage(index.defaultLanguage)
            }

            if (index.languageOverride != "") {
                indexOptions.languageOverride(index.languageOverride)
            }

            if (index.textVersion != -1) {
                indexOptions.textVersion(index.textVersion)
            }

            if (index.sphereVersion != -1) {
                indexOptions.sphereVersion(index.sphereVersion)
            }

            if (index.bits != -1) {
                indexOptions.bits(index.bits)
            }

            collection.createIndex(indexBson, indexOptions)
        }
    }

    protected suspend fun findBy(
        filter: Bson,
        options: FindOptions = FindOptions()
    ): T? = withContext(Dispatchers.IO) {
        collection.find(filter)
            .first()
            ?.let { readDocument(it) }
    }

    protected suspend fun insert(
        entity: T,
        options: InsertOneOptions = InsertOneOptions()
    ) = withContext(Dispatchers.IO) {
        collection.insertOne(writeDocument(entity))
    }

    protected suspend fun updateOne(
        filter: Bson,
        entity: T,
        options: UpdateOptions = UpdateOptions()
    ) = withContext(Dispatchers.IO) {
        collection.updateOne(filter, writeDocument(entity), options)
    }

    private fun readDocument(document: Document): T {
        val constructor = clazz.constructors.first()
        val arguments = mutableListOf<Any?>()

        clazz.members.forEach { property ->
            val fieldName = property.annotations
                .filterIsInstance<Property>()
                .firstOrNull()
                ?.name

            if (fieldName != null) {
                arguments.add(document[fieldName])
            }
        }

        return constructor.call(*arguments.toTypedArray())
    }

    private fun writeDocument(entity: Any): Document {
        val document = Document()

        clazz.members.forEach { property ->
            val fieldName = property.annotations
                .filterIsInstance<Property>()
                .first()
                .name

            document[fieldName] = property.call(entity)
        }

        return document
    }
}
