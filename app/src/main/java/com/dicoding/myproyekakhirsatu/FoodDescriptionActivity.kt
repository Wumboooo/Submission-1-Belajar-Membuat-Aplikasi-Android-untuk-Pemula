package com.dicoding.myproyekakhirsatu

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class FoodDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food_description)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.description_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvName: TextView = findViewById(R.id.tv_food_name)
        val tvPrice: TextView = findViewById(R.id.tv_food_price)
        val tvDescriptionLong: TextView = findViewById(R.id.tv_food_description)
        val tvComposition: TextView = findViewById(R.id.tv_food_composition_description)
        val tvPhoto: ImageView = findViewById(R.id.img_food_photo)

        val tvNameFood = intent.getStringExtra("food_name")
        val tvPriceFood = intent.getStringExtra("food_price")
        val tvDescriptionLongFood = intent.getStringExtra("food_description")
        val tvCompositionFood = intent.getStringExtra("food_composition")
        val tvPhotoFood = intent.getStringExtra("food_photo")

        tvName.text = tvNameFood
        tvPrice.text = tvPriceFood
        tvDescriptionLong.text = tvDescriptionLongFood
        tvComposition.text = tvCompositionFood
//        tvPhoto.img = tvPhotoFood

        Glide.with(this)
            .load(tvPhotoFood)
            .into(tvPhoto)
    }

}
