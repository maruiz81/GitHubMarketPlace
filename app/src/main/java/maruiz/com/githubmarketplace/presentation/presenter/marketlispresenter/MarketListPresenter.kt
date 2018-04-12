package maruiz.com.githubmarketplace.presentation.presenter.marketlispresenter

import io.reactivex.observers.DisposableSingleObserver
import maruiz.com.githubmarketplace.data.model.markets.Node
import maruiz.com.githubmarketplace.domain.interactor.GetMarkets
import maruiz.com.githubmarketplace.presentation.presenter.BasePresenter
import maruiz.com.githubmarketplace.presentation.recyclerview.row.EmptyCategoryRow
import maruiz.com.githubmarketplace.presentation.recyclerview.row.MarketRow
import maruiz.com.githubmarketplace.presentation.view.fragment.MarketListView

class MarketListPresenter(private val getMarkets: GetMarkets,
                          private val categorySlug: String) : BasePresenter<MarketListView>() {
    override fun onAttachView(view: MarketListView) {
        super.onAttachView(view)
        getMarkets.execute(MarketObserver(), GetMarkets.Params(categorySlug))
    }

    override fun onDetachView() {
        super.onDetachView()
        getMarkets.dispose()
    }

    private fun painItemsInTheView(nodes: List<Node>) {
        view?.updateRenderables(if (nodes.isEmpty()) listOf(EmptyCategoryRow())
        else nodes.map { MarketRow(it.name, it.shortDescription, it.logoUrl) })
    }

    private fun showError() {
        view?.showError()
    }

    inner class MarketObserver : DisposableSingleObserver<List<Node>>() {
        override fun onSuccess(nodes: List<Node>) {
            painItemsInTheView(nodes)
        }

        override fun onError(e: Throwable) {
            showError()
        }
    }
}