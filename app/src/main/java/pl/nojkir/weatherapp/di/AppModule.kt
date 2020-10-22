package pl.nojkir.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.nojkir.weatherapp.ui.api.CurrentWeatherAPI
import pl.nojkir.weatherapp.ui.api.ForecastWeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit) : CurrentWeatherAPI =
        retrofit.create(CurrentWeatherAPI::class.java)

    @Provides
    @Singleton

    fun provideForecastApi(retrofit: Retrofit) : ForecastWeatherAPI =
        retrofit.create(ForecastWeatherAPI:: class.java)


}