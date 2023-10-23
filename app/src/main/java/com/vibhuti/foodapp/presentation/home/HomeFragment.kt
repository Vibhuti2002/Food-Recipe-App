package com.vibhuti.foodapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.vibhuti.foodapp.databinding.FragmentHomeBinding
import com.vibhuti.foodapp.domain.model.Meal
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewmodel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewmodel.getFood("Chicken")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(emptyList())

        viewmodel.showProgress.observe(viewLifecycleOwner) { showProgress ->
            binding.progressBar.visibility = if (showProgress) View.VISIBLE else View.GONE
        }

        viewmodel.getFoodResponseLiveData.observe(viewLifecycleOwner) { list ->
            if (list.isEmpty()) {
                Toast.makeText(requireContext(), "Issue In Fetching Details", Toast.LENGTH_SHORT)
                    .show()
            } else {
                ArrayList(list).let { homeAdapter.updateList(it) }
            }
        }
        binding.tlCategory.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val category = tab.text.toString()
                viewmodel.filterFood(category)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setUpRecyclerView(list: List<Meal>) {
        homeAdapter = HomeAdapter(ArrayList(list), object : ItemClickListener {
            override fun onItemClick(meal: Meal) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(meal)
                findNavController().navigate(action)
            }
        })

        binding.rvFoodList.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}