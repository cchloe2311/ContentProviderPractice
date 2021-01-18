package com.example.contentproviderpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_contact.view.*

class MyContactsAdapter : ListAdapter<MyContact, ViewHolder>(DiffCallback()) {
    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contact,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvImage = itemView.ivCharacter
    private val tvName = itemView.tvName
    fun bind(item: MyContact) {
        tvName.text = item.name
        Glide.with(tvImage)
            .load(item.image)
            .into(tvImage)
    }
}

class DiffCallback : DiffUtil.ItemCallback<MyContact>() {
    override fun areItemsTheSame(oldItem: MyContact, newItem: MyContact): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: MyContact, newItem: MyContact): Boolean {
        return oldItem == newItem
    }
}