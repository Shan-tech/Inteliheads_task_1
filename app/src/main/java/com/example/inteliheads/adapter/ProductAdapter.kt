package com.example.inteliheads.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inteliheads.R
import com.example.inteliheads.data.ItemInfo
import com.squareup.picasso.Picasso


class ProductAdapter( val list: ArrayList<ItemInfo>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.name)
        val itemPrice: TextView = view.findViewById(R.id.mrp)
        val itemWeight: TextView = view.findViewById(R.id.quantity)
        val itemImage: ImageView = view.findViewById(R.id.display)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_recycler_single_row, parent, false)
        return ProductViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val pdt = list[position]
        holder.itemName.text = "Name  : " + pdt.item_name
        holder.itemPrice.text = "Price : " + pdt.item_price
        holder.itemWeight.text = "Qty   : " + pdt.item_qty +" "+ pdt.item_qty_type
        Picasso.get().load(pdt.item_img_url).error(R.drawable.ic_launcher_background)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


