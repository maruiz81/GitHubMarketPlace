package maruiz.com.githubmarketplace.domain.interactor

import io.reactivex.Single
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.data.model.categories.Category
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesModel
import maruiz.com.githubmarketplace.data.model.categories.MarketCategoriesQueryModel

class GetCategories(private val marketPlaceApi: GitHubMarketPlaceApi) :
        UseCase<MarketCategoriesModel, List<Category>, GetCategories.Params>() {
    override fun buildUseCaseObservable(params: Params): Single<MarketCategoriesModel> =
            marketPlaceApi.getMarketCategories(MarketCategoriesQueryModel())

    override fun convertData(originalData: MarketCategoriesModel): List<Category> =
            originalData.data.marketplaceCategories

    class Params
}