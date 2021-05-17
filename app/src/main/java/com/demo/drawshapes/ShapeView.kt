package com.demo.drawshapes

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

class ShapeView constructor(context: Context,
                            attrs: AttributeSet? = null,
                            defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr){

    private lateinit var paint: Paint

    private fun setupPaint(colorEnum: Shape.ColorEnum){
        paint = Paint()
        paint.apply {
            color = when(colorEnum.name){
                Shape.ColorEnum.Green.name -> Color.GREEN
                Shape.ColorEnum.Blue.name -> Color.BLUE
                else ->  Color.RED
            }
            style = Paint.Style.FILL

        }
    }

}