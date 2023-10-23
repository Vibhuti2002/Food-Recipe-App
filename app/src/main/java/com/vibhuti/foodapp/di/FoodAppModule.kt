package com.vibhuti.foodapp.di

import com.vibhuti.foodapp.common.Constants
import com.vibhuti.foodapp.data.remote.FoodApi
import com.vibhuti.foodapp.data.repository.FoodRepositoryImpl
import com.vibhuti.foodapp.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FoodAppModule {

    @Provides
    @Singleton
    fun foodApi(): FoodApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApi::class.java)
    }

    @Provides
    fun provideFoodRepository(foodApi : FoodApi) : FoodRepository{
        return FoodRepositoryImpl(foodApi)
    }

}