package com.example.utahstateparks.ui.parkSelector

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.utahstateparks.R
import com.example.utahstateparks.data.StatePark

@BindingAdapter("favorited")
fun ImageView.setSleepImage(item: StatePark?) {
    item?.let {
        setImageResource(when (item.favorite) {
            true -> R.drawable.filled_star
            false -> R.drawable.empty_star
        })
    }
}