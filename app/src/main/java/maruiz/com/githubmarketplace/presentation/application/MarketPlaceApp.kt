package maruiz.com.githubmarketplace.presentation.application

import android.app.Application
import maruiz.com.githubmarketplace.presentation.di.AppComponent
import maruiz.com.githubmarketplace.presentation.di.DaggerAppComponent
import maruiz.com.githubmarketplace.presentation.di.RetrofitModule

class MarketPlaceApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initializeInjector()
    }

    private fun initializeInjector() {
        appComponent = DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build()
    }
}