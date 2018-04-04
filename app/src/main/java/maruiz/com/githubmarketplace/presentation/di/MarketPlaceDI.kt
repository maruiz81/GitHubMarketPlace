package maruiz.com.githubmarketplace.presentation.di

import maruiz.com.githubmarketplace.presentation.di.scopes.PerActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import retrofit2.Retrofit

@PerActivity
@Component(modules = [(MarketPlaceModule::class)])
class MarketPlaceComponent {
}

@Module
class MarketPlaceModule {
    @Provides
    @PerActivity
    fun provideProductService(retrofit: Retrofit): GitHubMarketPlaceApi = retrofit.create(GitHubMarketPlaceApi::class.java)
}