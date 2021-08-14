package com.franzandel.moviesubmission.core.external.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Franz Andel on 14/08/21.
 * Android Engineer
 */

val gson = Gson()

inline fun <reified Map> String.convertStringToMap(): Map {
    return gson.fromJson(this, object : TypeToken<Map>() {}.type)
}

inline fun <reified DataClass> Map<String, Any>.toDataClass(): DataClass {
    return convert()
}

inline fun <Input, reified Output> Input.convert(): Output {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<Output>() {}.type)
}