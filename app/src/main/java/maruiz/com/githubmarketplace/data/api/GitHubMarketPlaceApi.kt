package maruiz.com.githubmarketplace.data.api

import io.reactivex.Single
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesModel
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesQueryModel
import maruiz.com.githubmarketplace.data.model.markets.MarketData
import maruiz.com.githubmarketplace.data.model.markets.MarketPlaceQueryModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GitHubMarketPlaceApi {
    @Headers("Authorization:Bearer $GITHUB_KEY")
    @POST("graphql")
    fun getMarketPlace(@Body query: MarketPlaceQueryModel): Single<MarketData>

    @Headers("Authorization:Bearer $GITHUB_KEY")
    @POST("graphql")
    fun getMarketCategories(@Body query: MarketCategoriesQueryModel): Single<MarketCategoriesModel>
}