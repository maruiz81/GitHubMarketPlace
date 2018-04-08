package maruiz.com.githubmarketplace.presentation.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun provideListenRetrofit(): Retrofit
}