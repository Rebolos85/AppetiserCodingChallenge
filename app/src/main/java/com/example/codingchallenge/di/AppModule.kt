package com.example.codingchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.codingchallenge.BuildConfig
import com.example.codingchallenge.data.local.db.ItunesItemDatabase
import com.example.codingchallenge.data.remote.api.AppleApi
import com.example.codingchallenge.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This class is used to provide all depedency needed to be consumed by consumer class
 * via @Inject
 *
 */

// this one will live as long the application is does this
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    /**
     * This method provide instantiating the database and providing it's instance
     * @return ItunesItemDatabase- this is a local persistence database
     */

    @Provides
    @Singleton
    fun providesItemsItunesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ItunesItemDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    /**
     * This method is to provide the httpLoggingInterceptor and to be used
     * to see all logs of request and response
     * @return HttpLoggingInterceptor- responsible to log our info request and response
     */
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    /**
     * This method is responsible to instantiate the dao interface
     * @param -ItunesItemDatabase - which is a transitive dependency that needed
     *  to by the itunesdao
     *  @return ItunesDao- performing sql statement to be used by the repo
     */
    @Provides
    @Singleton
    fun providesItunesItemDao(itunesItemDatabase: ItunesItemDatabase) =
        itunesItemDatabase.itunesDao()

    /**
     *This method is responsible to instantiate the retrofit
     * attaching the baseURL to connect with the webservices
     * @return AppleApi -which used to perform the @GET request in the
     * Itunes Web Service
     */

    @Provides
    @Singleton
    fun providesAppleApi(okHttpClient: OkHttpClient): AppleApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)

            .build()
            .create(AppleApi::class.java)

    }

    @Singleton
    @Provides
    fun providesNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .addHeader("Content-Type", "application/json")
                .build())
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        networkInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
}