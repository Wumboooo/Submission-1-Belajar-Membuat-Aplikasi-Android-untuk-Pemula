package com.dicoding.myproyekakhirsatu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFoods: RecyclerView
    private val list = ArrayList<Food>()
    private val listDescription = ArrayList<FoodDescription>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvFoods = findViewById(R.id.rv_foods)
        rvFoods.setHasFixedSize(true)

        list.addAll(getListFoods())
        listDescription.addAll(getListFoodsDescription())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListFoods(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescriptionShort = resources.getStringArray(R.array.data_description_short)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescriptionShort[i], dataPhoto[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun getListFoodsDescription(): ArrayList<FoodDescription> {
        val dataPrice = resources.getStringArray(R.array.data_food_price)
        val dataDescriptionLong = resources.getStringArray(R.array.data_description_long)
        val dataComposition = resources.getStringArray(R.array.data_composition)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listFoodDescription = ArrayList<FoodDescription>()
        for (i in dataPrice.indices) {
            val foodDescription = FoodDescription(dataPrice[i], dataDescriptionLong[i], dataComposition[i], dataPhoto[i])
            listFoodDescription.add(foodDescription)
        }
        return listFoodDescription
    }

    private fun showRecyclerList() {
        rvFoods.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list, listDescription)
        rvFoods.adapter = listFoodAdapter

        listFoodAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFoods.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFoods.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)

    }   private fun showSelectedFood(food: Food) {
        Toast.makeText(this, "Kamu memilih " + food.name, Toast.LENGTH_SHORT).show()
        val position = list.indexOf(food)
        val moveIntent = Intent(this@MainActivity, FoodDescriptionActivity::class.java).apply {
            putExtra("food_name", food.name)
            putExtra("food_price", listDescription[position].price)
            putExtra("food_description", listDescription[position].descriptionLong)
            putExtra("food_composition", listDescription[position].composition)
            putExtra("food_photo", listDescription[position].photo)
        }
//        intent.putExtra("food_name", food.name)
        startActivity(moveIntent)
    }


}