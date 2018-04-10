package maruiz.com.githubmarketplace.presentation.di.modules

import dagger.Module
import dagger.Provides
import maruiz.com.githubmarketplace.data.api.GitHubMarketPlaceApi
import maruiz.com.githubmarketplace.domain.interactor.GetCategories
import maruiz.com.githubmarketplace.presentation.di.scopes.PerActivity
import maruiz.com.githubmarketplace.presentation.presenter.mainpresenter.MainPresenter
import retrofit2.Retrofit

@Module
class MainActivityModule {

    @PerActivity
    @Provides
    fun providePresenter(getCategories: GetCategories): MainPresenter = MainPresenter(getCategories)

    @PerActivity
    @Provides
    fun provideInteractor(gitHubMarketPlaceApi: GitHubMarketPlaceApi): GetCategories =
            GetCategories(gitHubMarketPlaceApi)

    @PerActivity
    @Provides
    fun provideProductService(retrofit: Retrofit): GitHubMarketPlaceApi =
            retrofit.create(GitHubMarketPlaceApi::class.java)
}