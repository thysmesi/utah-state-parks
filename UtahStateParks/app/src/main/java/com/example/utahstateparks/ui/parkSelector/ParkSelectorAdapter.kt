package com.example.utahstateparks.ui.parkSelector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.utahstateparks.data.StatePark
import com.example.utahstateparks.databinding.StateParkListItemBinding

class ParkSelectorAdapter(val parkClickListener: ParkSelectorListener, val starClickListener: StarClickListener) : ListAdapter<StatePark, ParkSelectorAdapter.ViewHolder>(ParkSelectorDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(parkClickListener, starClickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: StateParkListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(parkClickListener: ParkSelectorListener, starClickListener: StarClickListener, item: StatePark) {
            binding.park = item
            binding.parkClickListener = parkClickListener
            binding.starClickListener = starClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StateParkListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ParkSelectorDiffCallback: DiffUtil.ItemCallback<StatePark>() {
    override fun areItemsTheSame(oldItem: StatePark, newItem: StatePark): Boolean {
        return oldItem.parkId == newItem.parkId
    }
    override fun areContentsTheSame(oldItem: StatePark, newItem: StatePark): Boolean {
        return oldItem == newItem
    }
}

class ParkSelectorListener(val clickListener: (parkId: Long) -> Unit) {
    fun onClick(park: StatePark) = clickListener(park.parkId)
}

class StarClickListener(val clickListener: (park: StatePark) -> Unit) {
    fun onClick(park: StatePark) = clickListener(park)
}