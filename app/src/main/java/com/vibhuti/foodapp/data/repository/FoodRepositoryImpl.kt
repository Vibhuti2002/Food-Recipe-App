package com.vibhuti.foodapp.data.repository

import com.vibhuti.foodapp.data.model.Meals
import com.vibhuti.foodapp.data.remote.FoodApi
import com.vibhuti.foodapp.domain.repository.FoodRepository

class FoodRepositoryImpl(private val foodApi : FoodApi) : FoodRepository {
    override suspend fun getFoodList(s: String): Meals {
        return foodApi.getFoodList(s)
    }
}