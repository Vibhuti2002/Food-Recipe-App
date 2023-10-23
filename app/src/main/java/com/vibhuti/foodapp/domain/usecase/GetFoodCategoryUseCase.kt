package com.vibhuti.foodapp.domain.usecase

import android.util.Log
import com.vibhuti.foodapp.domain.model.Meal
import com.vibhuti.foodapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetFoodCategoryUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(s:String):List<Meal>{
        return try {
            val list = repository.getFoodList(s)
            val mealList = list.meals.map {
                it.toMeal()
            }
            return mealList
        }
        catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }
}