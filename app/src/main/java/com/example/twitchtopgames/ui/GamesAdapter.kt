package com.example.twitchtopgames.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twitchtopgames.databinding.ListItemGamesBinding
import com.example.twitchtopgames.ui.templates.GamesVO
import com.example.twitchtopgames.ui.templates.VisualObject


class GamesAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<VisualObject> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is GamesVO -> (holder as GamesViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: List<VisualObject>) {
        if (this.items.isEmpty()) {
            this.items = items
            notifyDataSetChanged()
        } else {
            val callback = BooksDiffUtils(
                oldList = this.items,
                newList = items
            )

            this.items = items
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        }
    }

    class GamesViewHolder(
        private val binding: ListItemGamesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(games: GamesVO) {

            with(binding) {
                name.text = games.name
                vieiwers.text = "number of viewers " + games.viewers
                channels.text = "number of channels " + games.channels
                Glide.with(imageView).load(games.box).into(imageView)
            }
        }
    }

}