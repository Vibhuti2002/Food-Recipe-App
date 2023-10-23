package com.vibhuti.foodapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vibhuti.foodapp.databinding.ItemFoodListBinding
import com.vibhuti.foodapp.domain.model.Meal


class HomeAdapter(
    private var mealList: ArrayList<Meal>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    inner class HomeViewHolder(val binding: ItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemFoodListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = mealList.size

    fun updateList(mealList: ArrayList<Meal>) {
        this.mealList.clear()
        this.mealList = mealList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val meal = mealList[position]
        holder.binding.apply {
            tvFoodHead.text = meal.name
            tvFoodType.text = meal.area
            Glide.with(holder.itemView.context).load(meal.image)
                .into(holder.binding.ivFood)
            holder.itemView.setOnClickListener {
                listener.onItemClick(meal)
            }
        }
    }

}

interface ItemClickListener {
    fun onItemClick(meal: Meal)
}