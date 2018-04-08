package maruiz.com.githubmarketplace.presentation.presenter

import io.reactivex.observers.DisposableSingleObserver
import maruiz.com.githubmarketplace.data.model.Node
import maruiz.com.githubmarketplace.domain.interactor.GetMarkets
import maruiz.com.githubmarketplace.presentation.view.activity.MainActivityView

class MainPresenter(private val getMarkets: GetMarkets) {

    private var view: MainActivityView? = null

    fun onAttachView(view: MainActivityView) {
        this.view = view
        getMarkets.execute(MarketObserver(), GetMarkets.Params())
    }

    fun onDettachView() {
        this.view = null
    }

    inner class MarketObserver : DisposableSingleObserver<List<Node>>() {
        override fun onSuccess(apps: List<Node>) {
        }

        override fun onError(e: Throwable) {
        }

    }
}