package maruiz.com.githubmarketplace.presentation.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import maruiz.com.githubmarketplace.R
import maruiz.com.githubmarketplace.presentation.application.MarketPlaceApp
import maruiz.com.githubmarketplace.presentation.di.components.DaggerMarketPlaceComponent
import maruiz.com.githubmarketplace.presentation.di.modules.MarketPlaceModule
import maruiz.com.githubmarketplace.presentation.model.Category
import maruiz.com.githubmarketplace.presentation.presenter.marketlispresenter.MarketListPresenter
import javax.inject.Inject

class MarketListFragment : Fragment(), MarketListView {

    @Inject
    protected lateinit var presenter: MarketListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.market_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInjection()
    }

    private fun initInjection() {
        val application = activity?.application
        application?.let {
            DaggerMarketPlaceComponent.builder()
                    .appComponent((application as MarketPlaceApp).appComponent)
                    .marketPlaceModule(MarketPlaceModule())
                    .build().inject(this)
        }
    }

    companion object {
        private const val ARG_CATEGORY = "ARG_CATEGORY"

        fun newInstance(category: Category): MarketListFragment = MarketListFragment().apply {
            arguments?.putParcelable(ARG_CATEGORY, category)
        }
    }
}