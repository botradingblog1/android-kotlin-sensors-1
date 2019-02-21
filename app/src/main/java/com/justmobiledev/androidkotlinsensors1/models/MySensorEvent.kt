package com.justmobiledev.androidkotlinsensors1.models

enum class SensorType(i: Int) {
    LIGHT(1),
    TEMPERATURE(2),
    GYRO(3)
}

class MySensorEvent {
    var type = SensorType.LIGHT
    var value = ""
}