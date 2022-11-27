package school.rs.ziban.currencytask.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import school.rs.ziban.currencytask.data.model.dto.CurrencyDto

interface CurrencyApiService {

    @GET("currency")
    suspend fun getCurrencyList(): List<CurrencyDto>

    @GET("currency/{base}")
    suspend fun getCurrency(@Path("base") base: String): CurrencyDto
}