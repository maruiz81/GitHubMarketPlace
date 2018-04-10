package maruiz.com.githubmarketplace.presentation.di.components

import dagger.Component
import maruiz.com.githubmarketplace.presentation.di.modules.MainActivityModule
import maruiz.com.githubmarketplace.presentation.di.scopes.PerActivity
import maruiz.com.githubmarketplace.presentation.view.activity.MainActivity

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(view: MainActivity)
}
