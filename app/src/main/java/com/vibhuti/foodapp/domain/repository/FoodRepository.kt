package com.vibhuti.foodapp.domain.repository

import com.vibhuti.foodapp.data.model.Meals

interface FoodRepository {
    suspend fun getFoodList(s:String):Meals
}