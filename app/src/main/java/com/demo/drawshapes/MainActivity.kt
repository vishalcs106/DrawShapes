package com.demo.drawshapes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demo.drawshapes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextWatcher {

    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory()
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViews()
    }

    private fun init(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.selectedShape.observe(this, Observer {
                shape -> binding.viewModel = viewModel
        })
    }

    private fun initViews(){
        val shapeNames = Shape.ShapeEnum.shapeNames;
        shapeNames.add(0, getString(R.string.shapes))
        val shapesArrayAdapter: ArrayAdapter<String> =  ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, shapeNames)
        binding.shapesSp.adapter = shapesArrayAdapter

        val colorNames = Shape.ColorEnum.colorNames;
        colorNames.add(0, getString(R.string.colors))
        val colorArrayAdapter: ArrayAdapter<String> = ArrayAdapter(this,
        android.R.layout.simple_spinner_dropdown_item, colorNames)
        binding.colorsSp.adapter = colorArrayAdapter

        binding.radiusEt.addTextChangedListener(this)
        binding.rectangleBreadthEt.addTextChangedListener(this)
        binding.rectangleLengthEt.addTextChangedListener(this)
        binding.triangleBaseEt.addTextChangedListener(this)
        binding.triangleHeightEt.addTextChangedListener(this)

    }

    companion object{
        @BindingAdapter("app:enableGenerateButton")
        @JvmStatic
        fun enableGenerateButton(view: Button, viewModel: MainViewModel?){
            viewModel?.let {
                val validInput = when(viewModel.selectedShape.value){
                    Shape.ShapeEnum.Circle -> viewModel.enteredRadius.value != ""
                    Shape.ShapeEnum.Triangle -> viewModel.enteredBase.value != "" && viewModel.enteredHeight.value != ""
                    Shape.ShapeEnum.Rectangle -> viewModel.enteredLength.value != "" && viewModel.enteredBreadth.value != ""

                    else -> false
                }

                if(validInput){
                    view.isEnabled = true
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.white))
                    view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.purple))
                } else{
                    view.isEnabled = false
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.black))
                    view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
                }
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        binding.viewModel = viewModel
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

}