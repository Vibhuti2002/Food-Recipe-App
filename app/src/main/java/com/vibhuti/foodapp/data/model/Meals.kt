package com.vibhuti.foodapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meals(
    val meals: List<MealItem>
) : Parcelable