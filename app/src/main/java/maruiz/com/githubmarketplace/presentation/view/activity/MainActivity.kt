package maruiz.com.githubmarketplace.presentation.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import maruiz.com.githubmarketplace.R
import maruiz.com.githubmarketplace.presentation.application.MarketPlaceApp
import maruiz.com.githubmarketplace.presentation.di.DaggerMarketPlaceComponent
import maruiz.com.githubmarketplace.presentation.di.MarketPlaceModule
import maruiz.com.githubmarketplace.presentation.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    protected lateinit var presenter: MainPresenter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        initInjection()
    }

    private fun initInjection() {
        val application = application
        application?.let {
            DaggerMarketPlaceComponent.builder()
                    .appComponent((application as MarketPlaceApp).appComponent)
                    .marketPlaceModule(MarketPlaceModule())
                    .build().inject(this)
        }
    }
}
