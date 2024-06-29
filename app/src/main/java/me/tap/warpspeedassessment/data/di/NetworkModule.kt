package me.tap.warpspeedassessment.data.di

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.tap.warpspeedassessment.BuildConfig
import me.tap.warpspeedassessment.data.api.ApiService
import me.tap.warpspeedassessment.domain.network.ConnectivityRepository
import me.tap.warpspeedassessment.domain.network.ConnectivityRepositoryImpl
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val TIMEOUT_VALUE = 120L
    private const val KEEP_ALIVE_DURATION = 5L

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @[Provides]
    @[Singleton]
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @[Provides]
    @[Singleton]
    fun provideHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .connectionPool(ConnectionPool(0, KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
            .addInterceptor { chain ->
                val original = chain.request()
                val url =
                    original.url.newBuilder().addQueryParameter("apikey", BuildConfig.TEST_API_KEY)
                        .build()
                val request = original.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
        return httpClient.build()
    }

    @[Provides]
    @[Singleton]
    fun provideRetrofit(httpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager

}

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {

    @Binds
    abstract fun bindConnectivityRepository(impl: ConnectivityRepositoryImpl): ConnectivityRepository
}