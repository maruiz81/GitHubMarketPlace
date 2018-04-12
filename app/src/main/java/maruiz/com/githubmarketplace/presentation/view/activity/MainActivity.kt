package maruiz.com.githubmarketplace.presentation.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import maruiz.com.githubmarketplace.R
import maruiz.com.githubmarketplace.presentation.application.MarketPlaceApp
import maruiz.com.githubmarketplace.presentation.di.components.DaggerMainActivityComponent
import maruiz.com.githubmarketplace.presentation.di.modules.MainActivityModule
import maruiz.com.githubmarketplace.presentation.model.Category
import maruiz.com.githubmarketplace.presentation.presenter.mainpresenter.MainPresenter
import maruiz.com.githubmarketplace.presentation.view.adapter.MarketFragmentCollectionAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {
    @Inject
    protected lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInjection()
    }

    override fun onStart() {
        super.onStart()

        presenter.onAttachView(this)
    }

    override fun onStop() {
        super.onStop()

        presenter.onDetachView()
    }

    override fun setCategories(categories: List<Category>) {
        pager.adapter = MarketFragmentCollectionAdapter(supportFragmentManager, categories)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_getting_categories), Toast.LENGTH_LONG).show()
    }

    private fun initInjection() {
        val application = application
        application?.let {
            DaggerMainActivityComponent.builder()
                    .appComponent((application as MarketPlaceApp).appComponent)
                    .mainActivityModule(MainActivityModule())
                    .build().inject(this)
        }
    }
}
