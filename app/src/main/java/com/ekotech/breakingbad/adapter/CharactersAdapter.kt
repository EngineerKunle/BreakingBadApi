package com.ekotech.breakingbad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ekotech.breakingbad.R
import com.ekotech.breakingbad.utils.CharactersModelDiffCallback
import com.ekotech.breakingbad.viewstate.CharactersModel

class CharactersAdapter(val action: (CharactersModel) -> Unit) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    var characters: MutableList<CharactersModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_characters, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(characters[position])

    override fun getItemCount(): Int = characters.size

    fun setData(newCharacters: MutableList<CharactersModel>) {
        val diffCallback = CharactersModelDiffCallback(characters, newCharacters)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characters.clear()
        characters.addAll(newCharacters)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.item_characters_title)
        private val image: ImageView = view.findViewById(R.id.item_characters_image)
        private val container: ConstraintLayout = view.findViewById(R.id.item_characters_container)

        fun bind(character: CharactersModel) {
            title.text = character.name
            Glide
                .with(view)
                .load(character.imageURl)
                .centerCrop()
                .into(image)

            container.setOnClickListener {
                action(character)
            }
        }
    }
}
