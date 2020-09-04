package vladyslav.yarokh.bookssearcherv3.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vladyslav.yarokh.bookssearcherv3.Constants
import vladyslav.yarokh.bookssearcherv3.api.ApiServiceInterface

@Module
class NetworkModule {

    @Provides
    fun provideNetworkUtil(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson().newBuilder().create()
    }

    @Provides
    fun provideGsonFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
    }
}