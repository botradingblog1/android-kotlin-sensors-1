package com.justmobiledev.androidkotlinsensors1.models

enum class SensorType(i: Int) {
    LIGHT(1)
}

class MySensorEvent {
    var type = SensorType.LIGHT
    var value = ""


}