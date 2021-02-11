package com.example.utahstateparks.parksSelector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.utahstateparks.database.StatePark
import com.example.utahstateparks.databinding.ListItemStateParkBinding

class StateParkAdapter() : ListAdapter<StatePark, StateParkAdapter.ViewHolder>(StateParkDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemStateParkBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StatePark) {
            binding.park = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStateParkBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class StateParkDiffCallback : DiffUtil.ItemCallback<StatePark>() {
    override fun areItemsTheSame(oldItem: StatePark, newItem: StatePark): Boolean {
        return oldItem.parkId == newItem.parkId
    }

    override fun areContentsTheSame(oldItem: StatePark, newItem: StatePark): Boolean {
        return oldItem == newItem
    }
}
