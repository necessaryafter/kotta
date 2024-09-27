package com.kotta.core.ext

import com.mongodb.client.model.Filters

infix fun String.eq(item: Any?) = Filters.eq(this, item)

infix fun String.gt(item: Any) = Filters.gt(this, item)

infix fun String.lt(item: Any) = Filters.gt(this, item)

