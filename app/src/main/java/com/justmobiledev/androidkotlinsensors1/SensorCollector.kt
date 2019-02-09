package com.justmobiledev.androidkotlinsensors1

import android.app.Service
import android.content.Context
import android.hardware.SensorManager


object SensorCollector{

    lateinit var context : Context
    var mySensorManager : SensorManager

    init{
        mySensorManager = (context.getSystemService(Service.SENSOR_SERVICE)) as SensorManager

    }

    fun addContext(ctx : Context){
        context = ctx
    }


}