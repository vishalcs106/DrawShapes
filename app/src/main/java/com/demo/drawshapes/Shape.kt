package com.demo.drawshapes

open class Shape(var name: ShapeEnum? = null, var color: ColorEnum? = null) {

    enum class ShapeEnum {
        Circle, Rectangle, Triangle;
        companion object{
            val shapeNames: MutableList<String> = mutableListOf()
            init{
                for(shape in values()){
                    shapeNames.add(shape.name)
                }
            }
        }
    }
    enum class ColorEnum {
        Red, Green, Blue;
        companion object{
            val colorNames: MutableList<String> = mutableListOf()
            init{
                for(color in values()){
                    colorNames.add(color.name)
                }
            }
        }
    }

}