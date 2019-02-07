package com.justmobiledev.androidkotlinsensors1

import android.content.Context

object SensorCollector{

    lateinit var context : Context

    init{

    }

    fun addContext(ctx : Context){
        context = ctx
    }
}