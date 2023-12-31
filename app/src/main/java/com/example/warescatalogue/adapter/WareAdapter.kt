package com.example.warescatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warescatalogue.R
import com.example.warescatalogue.entity.Ware

class WareAdapter : RecyclerView.Adapter<WareAdapter.WareViewHolder>() {
    private var dataset = mutableListOf<Ware>()
    private var deleteAction: ((Ware) -> Unit)? = null
    private var updateAction: ((Ware) -> Unit)? = null

    class WareViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val displayName: TextView = itemView.findViewById(R.id.display_name)
        val displayDescription: TextView = itemView.findViewById(R.id.display_description)
        val displayPrice: TextView = itemView.findViewById(R.id.display_price)
        val deleteIcon: ImageView = itemView.findViewById(R.id.delete_icon)
        val updateIcon: ImageView = itemView.findViewById(R.id.update_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WareViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.ware_card, parent, false)

        return WareViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: WareViewHolder, position: Int) {
        val ware = dataset[position]

        holder.displayName.text = ware.name
        holder.displayDescription.text = ware.description
        holder.displayPrice.text = String.format("%.2f", (ware.price.toFloat() / 100))
        holder.deleteIcon.setOnClickListener { deleteAction?.invoke(ware) }
        holder.updateIcon.setOnClickListener { updateAction?.invoke(ware) }
    }

    fun setDataset(wares: List<Ware>) {
        dataset.apply {
            clear()

            addAll(wares)
        }
    }

    fun setOnDeleteListener(callback: (Ware) -> Unit) {
        this.deleteAction = callback
    }
    fun setOnUpdateListener(callback: (Ware) -> Unit) {
        this.updateAction = callback
    }
}