package maruiz.com.githubmarketplace.presentation.di.components

import dagger.Component
import maruiz.com.githubmarketplace.presentation.di.modules.MarketPlaceModule
import maruiz.com.githubmarketplace.presentation.di.scopes.PerFragment
import maruiz.com.githubmarketplace.presentation.view.fragment.MarketListFragment

@PerFragment
@Component(dependencies = [AppComponent::class], modules = [MarketPlaceModule::class])
interface MarketPlaceComponent {
    fun inject(view: MarketListFragment)
}
