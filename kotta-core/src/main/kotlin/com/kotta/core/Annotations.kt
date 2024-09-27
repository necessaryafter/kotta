package com.kotta.core

/**
 * TODO LIST
 *
 * Add support to:
 *     private Long expireAfterSeconds;
 *     private Integer version;
 *     private Bson weights;
 *     private String defaultLanguage;
 *     private String languageOverride;
 *     private Integer textVersion;
 *     private Integer sphereVersion;
 *     private Integer bits;
 *     private Double min;
 *     private Double max;
 *     private Bson storageEngine;
 *     private Bson partialFilterExpression;
 *     private Collation collation;
 *     private Bson wildcardProjection;
 *     private boolean hidden;
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
    val background: Boolean = true
)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Property(
    val name: String,
    val required: Boolean = false
)

