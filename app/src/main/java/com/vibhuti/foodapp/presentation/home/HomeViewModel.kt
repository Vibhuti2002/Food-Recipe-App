package com.vibhuti.foodapp.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhuti.foodapp.domain.model.Meal
import com.vibhuti.foodapp.domain.usecase.GetFoodCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFoodCategoryUseCase: GetFoodCategoryUseCase
) : ViewModel() {

    private val _getFoodResponseLiveData: MutableLiveData<List<Meal>> = MutableLiveData()
    val showProgress: MutableLiveData<Boolean> = MutableLiveData()
    private var food: List<Meal> = emptyList()

    val getFoodResponseLiveData: MutableLiveData<List<Meal>>
        get() = _getFoodResponseLiveData


    fun getFood(s: String) = viewModelScope.launch(Dispatchers.IO) {
        if (food.isEmpty()) {
            showProgress.postValue(true)
        }
        food = getFoodCategoryUseCase.invoke(s)
        _getFoodResponseLiveData.postValue(food)
        showProgress.postValue(false)
    }

    fun filterFood(category: String) {
        val list = if (category == "All") {
            food
        } else {
            val filteredFood = food.filter {
                it.area == category
            }
            filteredFood
        }
        _getFoodResponseLiveData.postValue(list)
    }

}