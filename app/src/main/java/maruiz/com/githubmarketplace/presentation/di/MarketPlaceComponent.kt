package maruiz.com.githubmarketplace.presentation.di

import dagger.Component
import maruiz.com.githubmarketplace.presentation.di.scopes.PerActivity
import maruiz.com.githubmarketplace.presentation.view.activity.MainActivity

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [MarketPlaceModule::class])
interface MarketPlaceComponent {
    fun inject(view: MainActivity)
}
