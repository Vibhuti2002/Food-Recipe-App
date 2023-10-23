package com.vibhuti.foodapp.data.remote

import com.vibhuti.foodapp.data.model.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("/api/json/v1/1/search.php")
    suspend fun getFoodList(@Query("s") s:String):Meals
}