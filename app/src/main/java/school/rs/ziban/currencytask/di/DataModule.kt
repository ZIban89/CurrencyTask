package school.rs.ziban.currencytask.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import school.rs.ziban.currencytask.data.api.CurrencyApiService
import school.rs.ziban.currencytask.data.repo.CurrencyRepositoryImpl
import school.rs.ziban.currencytask.domain.BASE_URL
import school.rs.ziban.currencytask.domain.CALL_TIME_LIMIT
import java.util.concurrent.TimeUnit

@Module
class DataModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(CALL_TIME_LIMIT, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    fun provideJson() =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }

    @ExperimentalSerializationApi
    @Provides
    fun provideJsonConverter(json: Json) =
        json.asConverterFactory("application/json".toMediaType())


    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCurrencyNetworkApi(retrofit: Retrofit): CurrencyApiService =
        retrofit.create(CurrencyApiService::class.java)
}