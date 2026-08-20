package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String, // "Saree", "Handicraft", "Sandal Products", "Wood Articles"
    val description: String,
    val price: Double,
    val imageUrl: String,
    val material: String = "Pure Authentic Craft",
    val craftOrigin: String = "Mysore, Karnataka",
    val inStock: Boolean = true,
    val featured: Boolean = false,
    val specs: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)
