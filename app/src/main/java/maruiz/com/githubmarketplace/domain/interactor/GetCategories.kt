package maruiz.com.githubmarketplace.domain.interactor

import android.util.Log
import com.google.gson.Gson
import io.reactivex.Single
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.data.model.categories.CategoryModel
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesModel
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesQueryModel

class GetCategories(private val marketPlaceApi: GitHubMarketPlaceApi) :
        UseCase<MarketCategoriesModel, List<CategoryModel>, GetCategories.Params>() {
    override fun buildUseCaseObservable(params: Params): Single<MarketCategoriesModel> {
        Log.d("GetCategories", Gson().toJson(MarketCategoriesQueryModel()))

        return marketPlaceApi.getMarketCategories(MarketCategoriesQueryModel())
    }

    override fun convertData(originalData: MarketCategoriesModel): List<CategoryModel> =
            originalData.data.marketplaceCategories

    class Params
}