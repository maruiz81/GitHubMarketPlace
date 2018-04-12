package maruiz.com.githubmarketplace.domain.interactor

import io.reactivex.Single
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.data.model.categories.CategoryModel
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesModel
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesQueryModel

class GetCategories(private val marketPlaceApi: GitHubMarketPlaceApi) :
        UseCase<MarketCategoriesModel, List<CategoryModel>, GetCategories.Params>() {
    override fun buildUseCaseObservable(params: Params): Single<MarketCategoriesModel> {
        return marketPlaceApi.getMarketCategories(MarketCategoriesQueryModel())
    }

    override fun convertData(originalData: MarketCategoriesModel): List<CategoryModel> =
            originalData.data.marketplaceCategories

    class Params
}