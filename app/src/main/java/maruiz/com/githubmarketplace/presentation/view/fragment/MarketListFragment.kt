package maruiz.com.githubmarketplace.presentation.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.market_list_fragment.*
import maruiz.com.githubmarketplace.R
import maruiz.com.githubmarketplace.presentation.application.MarketPlaceApp
import maruiz.com.githubmarketplace.presentation.di.components.DaggerMarketPlaceComponent
import maruiz.com.githubmarketplace.presentation.di.modules.MarketPlaceModule
import maruiz.com.githubmarketplace.presentation.model.Category
import maruiz.com.githubmarketplace.presentation.presenter.marketlispresenter.MarketListPresenter
import maruiz.com.githubmarketplace.presentation.recyclerview.factory.MarketFactory
import me.alexrs.recyclerviewrenderers.adapter.RendererAdapter
import me.alexrs.recyclerviewrenderers.builder.RendererBuilder
import me.alexrs.recyclerviewrenderers.interfaces.Renderable
import javax.inject.Inject

class MarketListFragment : Fragment(), MarketListView {

    @Inject
    protected lateinit var presenter: MarketListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.market_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        initInjection()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        marketRecyclerView.layoutManager = layoutManager
        marketRecyclerView.setHasFixedSize(false)

    }

    override fun onStart() {
        super.onStart()

        presenter.onAttachView(this)
    }

    override fun onDetach() {
        super.onDetach()

        presenter.onDetachView()
    }

    override fun updateRenderables(renderables: List<Renderable>) {
        marketRecyclerView.adapter = RendererAdapter(renderables, RendererBuilder(MarketFactory()))
    }

    private fun initInjection() {
        val application = activity?.application
        application?.let {
            DaggerMarketPlaceComponent.builder()
                    .appComponent((application as MarketPlaceApp).appComponent)
                    .marketPlaceModule(MarketPlaceModule(arguments?.getParcelable<Category>(ARG_CATEGORY)?.slug
                            ?: ""))
                    .build().inject(this)
        }
    }

    override fun showError() {
        Toast.makeText(context, getString(R.string.error_getting_market_list), Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val ARG_CATEGORY = "ARG_CATEGORY"

        fun newInstance(category: Category): MarketListFragment = MarketListFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(ARG_CATEGORY, category)
            arguments = bundle
        }
    }
}