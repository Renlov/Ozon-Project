package com.pimenov.feature_cart_impl.presentation.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class SwipeController : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipe = ItemTouchHelper.LEFT
        return makeMovementFlags(0, swipe)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        return
    }
}