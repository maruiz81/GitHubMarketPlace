package maruiz.com.githubmarketplace.domain.interactor

import io.reactivex.Single
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.data.model.markets.MarketData
import maruiz.com.githubmarketplace.data.model.markets.MarketPlaceQueryModel
import maruiz.com.githubmarketplace.data.model.markets.Node

class GetMarkets(private val marketPlaceApi: GitHubMarketPlaceApi) :
        UseCase<MarketData, List<Node>, GetMarkets.Params>() {
    override fun buildUseCaseObservable(params: Params): Single<MarketData> =
            marketPlaceApi.getMarketPlace(MarketPlaceQueryModel())

    override fun convertData(originalData: MarketData): List<Node> =
            originalData.data.edges.map { it.node }

    class Params
}