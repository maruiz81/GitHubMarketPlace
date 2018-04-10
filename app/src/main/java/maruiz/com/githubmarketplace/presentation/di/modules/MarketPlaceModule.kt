package maruiz.com.githubmarketplace.presentation.di.modules

import dagger.Module
import dagger.Provides
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.domain.interactor.GetMarkets
import maruiz.com.githubmarketplace.presentation.di.scopes.PerFragment
import maruiz.com.githubmarketplace.presentation.presenter.marketlispresenter.MarketListPresenter
import retrofit2.Retrofit

@Module
class MarketPlaceModule {

    @PerFragment
    @Provides
    fun providePresenter(getMarkets: GetMarkets): MarketListPresenter = MarketListPresenter(getMarkets)

    @PerFragment
    @Provides
    fun provideInteractor(gitHubMarketPlaceApi: GitHubMarketPlaceApi): GetMarkets =
            GetMarkets(gitHubMarketPlaceApi)

    @PerFragment
    @Provides
    fun provideProductService(retrofit: Retrofit): GitHubMarketPlaceApi =
            retrofit.create(GitHubMarketPlaceApi::class.java)
}