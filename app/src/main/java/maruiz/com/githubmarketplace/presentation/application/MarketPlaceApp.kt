package maruiz.com.githubmarketplace.presentation.application

import android.app.Application
import maruiz.com.githubmarketplace.presentation.di.components.AppComponent
import maruiz.com.githubmarketplace.presentation.di.components.DaggerAppComponent
import maruiz.com.githubmarketplace.presentation.di.modules.RetrofitModule

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