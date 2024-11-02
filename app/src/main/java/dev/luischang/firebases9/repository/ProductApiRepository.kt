package dev.luischang.firebases9.repository

import dev.luischang.firebases9.model.ProductApiModel
import dev.luischang.firebases9.service.ProductApiService
import dev.luischang.firebases9.service.RetrofitInstance

class ProductApiRepository {

    private val productApiService: ProductApiService = RetrofitInstance.api

    fun getProducts() = productApiService.getProducts()
    fun getProductById(id: Int) = productApiService.getProductById(id)
    fun createProduct(product: ProductApiModel) = productApiService.createProduct(product)
    fun updateProduct(id: Int, product: ProductApiModel) = productApiService.updateProduct(id, product)
    fun deleteProduct(id: Int) = productApiService.deleteProduct(id)
}