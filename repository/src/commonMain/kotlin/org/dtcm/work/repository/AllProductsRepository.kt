package org.dtcm.work.repository

import Converters
import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.dtcm.work.common.data.AllProductsItem
import org.dtcm.work.common.data.AllProductsList
import org.dtcm.work.common.data.BrandsItem
import org.dtcm.work.common.data.BrandsList
import org.dtcm.work.database.AppDatabase
import org.dtcm.work.database.entities.AllProductsListEntity
import org.dtcm.work.database.entities.BrandsListEntity
import org.dtcm.work.repository.firebasehelper.fetchFromDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AllProductsRepository: KoinComponent {
    private val firebaseDatabase: FirebaseDatabase by inject()
    private val roomDb: AppDatabase by inject()
    suspend fun fetchAndSaveAllProductsFromFirebase() {
        fetchFromDatabase<AllProductsList>(
            "home/allProducts",
            firebaseDatabase
        ).collect { data ->
            withContext(Dispatchers.IO) {
                data?.allProductsList?.map {
                    roomDb.productDao().saveToAllProductsEntity(
                        AllProductsListEntity(
                            address = it.address.orEmpty(),
                            id = it.id ?: 0,
                            imageUrl = Converters().fromImagesList(it.images.orEmpty()),
                            title = it.title.orEmpty(),
                            salePercentage = it.salePercentage ?: 0,
                            saleStartsDate = it.saleStartsDate.orEmpty(),
                            saleEndsDate = it.saleEndsDate.orEmpty(),
                            originalPrice = it.originalPrice ?: 0,
                            priceOnSale = it.priceOnSale ?: 0,
                            sizes = Converters().fromSizesList(it.sizes.orEmpty()),
                            brand = it.brand.toString()
                        )
                    )
                }
            }
        }
    }

    fun getAllProducts() = roomDb.productDao().getAllProductsFlow().map { allProducts ->
        allProducts.map {
            AllProductsItem(
                id = it.id,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
                brand = it.brand
            )
        }
    }

    suspend fun getProductsByBrandName(brandName: String) =
        roomDb.productDao().getAllProductsFlow().map { allProducts ->
            allProducts.filter { it.brand == brandName }
        }.first().map {
            AllProductsItem(
                id = it.id,
                images = Converters().toImagesList(it.imageUrl),
                title = it.title,
                salePercentage = it.salePercentage,
                saleStartsDate = it.saleStartsDate,
                saleEndsDate = it.saleEndsDate,
                priceOnSale = it.priceOnSale,
                originalPrice = it.originalPrice,
                brand = it.brand
            )
        }

    suspend fun fetchAndSaveBrandsFromFirebase() {
        fetchFromDatabase<BrandsList>(
            "home/brands",
            firebaseDatabase
        ).collect { data ->
            withContext(Dispatchers.IO) {
                data?.brandsList?.map {
                    roomDb.productDao().saveToBrandsEntity(
                        BrandsListEntity(
                            brandId = it.brandId ?: 0,
                            brand = it.brand.toString(),
                            imageUrl = it.imageUrl.toString()
                        )
                    )
                }
            }
        }
    }

    fun getBrands() = roomDb.productDao().getAllBrandsFlow().map { allBrands ->
        allBrands.map {
            BrandsItem(
                brandId = it.brandId, imageUrl = it.imageUrl, brand = it.brand
            )
        }
    }
}