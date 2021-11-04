package com.example.twitchtopgames.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.twitchtopgames.ui.templates.VisualObject

class BooksDiffUtils(
    private val oldList: List<VisualObject>,
    private val newList: List<VisualObject>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition] == oldList[oldItemPosition]
    }

}