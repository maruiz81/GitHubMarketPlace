package maruiz.com.githubmarketplace.presentation.presenter.marketlispresenter

import io.reactivex.observers.DisposableSingleObserver
import maruiz.com.githubmarketplace.data.model.markets.Node
import maruiz.com.githubmarketplace.domain.interactor.GetMarkets
import maruiz.com.githubmarketplace.presentation.presenter.BasePresenter
import maruiz.com.githubmarketplace.presentation.view.fragment.MarketListView

class MarketListPresenter(private val getMarkets: GetMarkets) : BasePresenter<MarketListView>() {
    override fun onAttachView(view: MarketListView) {
        super.onAttachView(view)
        getMarkets.execute(MarketObserver(), GetMarkets.Params())
    }

    override fun onDetachView() {
        super.onDetachView()
        getMarkets.dispose()
    }

    inner class MarketObserver : DisposableSingleObserver<List<Node>>() {
        override fun onSuccess(apps: List<Node>) {
        }

        override fun onError(e: Throwable) {
        }

    }
}