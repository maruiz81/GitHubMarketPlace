package maruiz.com.githubmarketplace.data.api

import io.reactivex.Single
import maruiz.com.githubmarketplace.data.model.MarketData
import maruiz.com.githubmarketplace.data.model.MarketPlaceQueryModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GitHubMarketPlaceApi {
    @Headers("Authorization:Bearer $GITHUB_KEY")
    @POST
    fun getMarketPlace(@Body query: MarketPlaceQueryModel): Single<MarketData>
}