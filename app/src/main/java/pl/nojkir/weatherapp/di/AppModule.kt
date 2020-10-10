package pl.nojkir.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.ui.api.WeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("${R.string.BASE_URL}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit) : WeatherAPI =
        retrofit.create(WeatherAPI::class.java)
}