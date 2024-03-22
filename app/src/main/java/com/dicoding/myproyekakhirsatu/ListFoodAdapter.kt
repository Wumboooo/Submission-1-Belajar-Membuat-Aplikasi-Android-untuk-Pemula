package com.dicoding.myproyekakhirsatu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListFoodAdapter(private val listFood: ArrayList<Food>, private val listFoodDescription: ArrayList<FoodDescription>): RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_foods, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, descriptionShort, photo) = listFood[position]
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.imgPhoto) // imageView mana yang akan diterapkan
        holder.tvName.text = name
        holder.tvDescriptionShort.text = descriptionShort
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])
        }

//        val (price, descriptionLong, composition) = listFoodDescription[position]
//        holder.tvPrice.text = price
//        holder.tvDescriptionLong.text = descriptionLong
//        holder.tvComposition.text = composition
    }

    override fun getItemCount(): Int = listFood.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescriptionShort: TextView = itemView.findViewById(R.id.tv_item_description)

//        val tvPrice: TextView = itemView.findViewById(R.id.tv_food_price)
//        val tvDescriptionLong: TextView = itemView.findViewById(R.id.tv_food_description)
//        val tvComposition: TextView = itemView.findViewById(R.id.tv_food_composition_description)
//
//        init {
//            // Inisialisasi TextView dengan nilai default atau string kosong di sini
//            tvPrice.text = "" // Contoh inisialisasi dengan string kosong
//            tvDescriptionLong.text = "" // Contoh inisialisasi dengan string kosong
//            tvComposition.text = "" // Contoh inisialisasi dengan string kosong
//        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Food)
    }


}