package com.bignerdranch.android.ktsapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.ktsapplication.databinding.FragmentMainBinding
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private var feedAdapter: FeedDelegateListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        feedAdapter = FeedDelegateListAdapter()
        with(binding.feed) {
            val orientation = RecyclerView.VERTICAL
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            // Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun getDefaultItems() = List(20) {
        val randomUUID = UUID.randomUUID()
        when ((1..2).random()) {
            1 -> PostImage(
                userName = "@stravaguy",
                uuid = randomUUID,
                likesCount = 0,
                userAvatar = R.drawable.strava,
                picture = R.drawable.bycicler,

                )
            2 -> PostText(
                userName = "@stravaguy",
                uuid = randomUUID,
                userAvatar = R.drawable.strava,
                tweet = "Классно покатался"
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = feedAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        feedAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}