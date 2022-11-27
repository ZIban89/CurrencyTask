package school.rs.ziban.currencytask.domain

import school.rs.ziban.currencytask.domain.model.CurrencyModel

interface CurrencyRepository {

    suspend fun getCurrencyList(): List<CurrencyModel>

    suspend fun getCurrency(base: String): CurrencyModel
}