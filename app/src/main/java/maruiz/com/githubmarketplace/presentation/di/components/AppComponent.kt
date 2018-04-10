package maruiz.com.githubmarketplace.presentation.di.components

import dagger.Component
import maruiz.com.githubmarketplace.presentation.di.modules.RetrofitModule
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun provideListenRetrofit(): Retrofit
}