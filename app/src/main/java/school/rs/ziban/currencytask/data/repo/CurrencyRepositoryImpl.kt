package school.rs.ziban.currencytask.data.repo

import school.rs.ziban.currencytask.data.api.CurrencyApiService
import school.rs.ziban.currencytask.data.model.mapper.map
import school.rs.ziban.currencytask.domain.CurrencyRepository
import school.rs.ziban.currencytask.domain.model.Currency
import school.rs.ziban.currencytask.domain.model.CurrencyModel
import java.util.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService
) : CurrencyRepository {

    override suspend fun getCurrencyList(): List<CurrencyModel> =
        apiService.getCurrencyList().map {
            try {
                it.map()
            } catch (e: Exception) {
                CurrencyModel(Currency.UNKNOWN, it.rate, Calendar.getInstance())
            }
        }


    override suspend fun getCurrency(base: String): CurrencyModel =
        apiService.getCurrency(base).map()
}