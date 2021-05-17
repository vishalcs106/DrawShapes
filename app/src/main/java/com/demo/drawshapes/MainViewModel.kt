package com.demo.drawshapes

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _selectedShape: MutableLiveData<Shape.ShapeEnum> = MutableLiveData()
    val selectedShape: LiveData<Shape.ShapeEnum> = _selectedShape

    var isCircleSelected  = false
    var isRectangleSelected = false
    var isTriangleSelected = false

    var enteredRadius: MutableLiveData<String> = MutableLiveData("")
    var enteredLength: MutableLiveData<String> = MutableLiveData("")
    var enteredBreadth: MutableLiveData<String> = MutableLiveData("")
    var enteredHeight: MutableLiveData<String> = MutableLiveData("")
    var enteredBase: MutableLiveData<String> = MutableLiveData("")

    var isValidInput: MutableLiveData<Boolean> = MutableLiveData(false)

    val selectionListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            parent?.let {
                if(position > 0) {
                    val shape = Shape.ShapeEnum.valueOf(it.getItemAtPosition(position) as String)
                    _selectedShape.postValue(shape)
                    when(shape){
                        Shape.ShapeEnum.Circle -> performCircleSelected()
                        Shape.ShapeEnum.Rectangle -> performRectangleSelected()
                        Shape.ShapeEnum.Triangle -> performTringleSelected()
                    }
                }
                else {
                    clearSelection()
                }
            }
            System.out.println("")
        }
    }

    private fun performCircleSelected() {
        isCircleSelected = true
        isTriangleSelected = false
        isRectangleSelected = false
    }

    private fun performRectangleSelected() {
        isCircleSelected = false
        isTriangleSelected = false
        isRectangleSelected = true
    }

    private fun performTringleSelected() {
        isCircleSelected = false
        isTriangleSelected = true
        isRectangleSelected = false
    }

    private fun clearSelection(){
        _selectedShape.postValue(null)
        isCircleSelected = false
        isRectangleSelected = false
        isTriangleSelected = false
        enteredBase.postValue("")
        enteredHeight.postValue("")
        enteredBreadth.postValue("")
        enteredLength.postValue("")
        enteredRadius.postValue("")
    }

}