package maruiz.com.githubmarketplace.presentation.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RetrofitModule::class)])
interface AppComponent

@Singleton
@Module
class AppModule(private val applicationContext: Context) {

}

@Singleton
@Module
internal class RetrofitModule {

    companion object {
        const val REMOTE_SERVICE_URL = "https://api.github.com/graphql"
    }

    @Singleton
    @Provides
    fun provideListenRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(REMOTE_SERVICE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}