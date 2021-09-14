package com.ekotech.breakingbad.utils

import androidx.recyclerview.widget.DiffUtil
import com.ekotech.breakingbad.viewstate.CharactersModel

//Change this to list extension function
class CharactersModelDiffCallback(private val oldList: MutableList<CharactersModel>, private val newList: MutableList<CharactersModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].name === newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newPosition]
    }
}
