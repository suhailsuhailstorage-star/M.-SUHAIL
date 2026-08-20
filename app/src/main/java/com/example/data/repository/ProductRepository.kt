package com.example.data.repository

import com.example.data.local.InitialCatalogData
import com.example.data.local.ProductDao
import com.example.data.model.ProductItem
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: Flow<List<ProductItem>> = productDao.getAllProducts()

    fun getProductsByCategory(category: String): Flow<List<ProductItem>> {
        return productDao.getProductsByCategory(category)
    }

    fun getProductById(id: Int): Flow<ProductItem?> {
        return productDao.getProductById(id)
    }

    suspend fun insertProduct(product: ProductItem): Long {
        return productDao.insertProduct(product)
    }

    suspend fun updateProduct(product: ProductItem) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: ProductItem) {
        productDao.deleteProduct(product)
    }

    suspend fun resetToInitialCatalog() {
        productDao.deleteAll()
        productDao.insertAll(InitialCatalogData.items)
    }

    suspend fun ensureInitialData() {
        if (productDao.getCount() == 0) {
            productDao.insertAll(InitialCatalogData.items)
        }
    }
}
