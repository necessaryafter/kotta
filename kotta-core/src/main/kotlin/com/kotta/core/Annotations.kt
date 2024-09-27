package com.kotta.core

/**
 * TODO LIST
 *
 * Add support to:
 *     private Integer version;
 *     private Bson weights;
 *     private String defaultLanguage;
 *     private String languageOverride;
 *     private Integer textVersion;
 *     private Integer sphereVersion;
 *     private Integer bits;
 *     private Bson storageEngine;
 *     private Bson partialFilterExpression;
 *     private Collation collation;
 *     private Bson wildcardProjection;
 *
 *  on index annotation.
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Document(val collectionName: String)

@Repeatable
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Index(
    val fieldName: String,
    val ascending: Boolean = true,
    val unique: Boolean = false,
    val sparse: Boolean = true,
    val background: Boolean = true,
    val hidden: Boolean = false,
    val expireAfterSeconds: Long = -1L,
    val min: Double = -1.0,
    val max: Double = -1.0,
    val defaultLanguage: String = "",
    val languageOverride: String = "",
    val textVersion: Int = -1,
    val sphereVersion: Int = -1,
    val bits: Int = -1
)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Property(
    val name: String,
    val required: Boolean = false
)

