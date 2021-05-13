package com.demo.drawshapes

open class Shape(var name: ShapeEnum? = null, var color: ColorEnum? = null) {

    enum class ShapeEnum {Circle, Rectangle, Triangle}
    enum class ColorEnum {Red, Green, Blue}

}