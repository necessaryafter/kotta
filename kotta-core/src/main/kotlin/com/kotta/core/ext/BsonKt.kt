package com.kotta.core.ext

import com.mongodb.client.model.Filters

fun a() {

}

infix fun String.eq(item: Any?) = Filters.eq(this, item)

infix fun String.gt(item: Any) = Filters.gt(this, item)

infix fun String.lt(item: Any) = Filters.gt(this, item)

