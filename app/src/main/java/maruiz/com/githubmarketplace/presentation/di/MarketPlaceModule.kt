package maruiz.com.githubmarketplace.presentation.di

import dagger.Module
import dagger.Provides
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.domain.interactor.GetMarkets
import maruiz.com.githubmarketplace.presentation.di.scopes.PerActivity
import maruiz.com.githubmarketplace.presentation.presenter.MainPresenter
import retrofit2.Retrofit

@Module
class MarketPlaceModule {

    @PerActivity
    @Provides
    fun providePresenter(getMarkets: GetMarkets): MainPresenter = MainPresenter(getMarkets)

    @PerActivity
    @Provides
    fun provideInteractor(gitHubMarketPlaceApi: GitHubMarketPlaceApi): GetMarkets =
            GetMarkets(gitHubMarketPlaceApi)

    @PerActivity
    @Provides
    fun provideProductService(retrofit: Retrofit): GitHubMarketPlaceApi =
            retrofit.create(GitHubMarketPlaceApi::class.java)
}