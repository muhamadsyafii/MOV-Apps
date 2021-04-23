/*
 * Created by Muhamad Syafii
 * Wednesday, 21/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.model

import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


data class Checkout(
    var kursi: String = "",
    var harga: String = ""
)

fun objectFromData(str: String?): Checkout? {
    return Gson().fromJson<Checkout>(str, Checkout::class.java)
}

fun arrayFromData(str: String?): ArrayList<Checkout> {
    val listType: Type = object : TypeToken<ArrayList<Checkout?>?>() {}.type
    return Gson().fromJson(str, listType)
}