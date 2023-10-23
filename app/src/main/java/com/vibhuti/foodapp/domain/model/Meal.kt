package com.vibhuti.foodapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val mealId: String?,
    val name: String?,
    val image: String?,
    val area: String?,
    val description: String?
) : Parcelable
