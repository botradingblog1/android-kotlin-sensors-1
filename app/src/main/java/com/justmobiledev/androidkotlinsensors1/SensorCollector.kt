package com.justmobiledev.androidkotlinsensors1

import android.content.Context
import android.hardware.SensorManager
import android.support.v4.content.ContextCompat.getSystemService

object SensorCollector{

    lateinit var context : Context
    var mySensorManager : SensorManager

    init{
        mySensorManager = getSystemService(cntext, Context.SENSOR_SERVICE) as SensorManager;
    }

    fun addContext(ctx : Context){
        context = ctx
    }


}