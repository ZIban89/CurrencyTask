package school.rs.ziban.currencytask.domain.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import school.rs.ziban.currencytask.domain.model.CurrencyModel

interface CurrencyInteractor{

    fun getCurrencyList(coroutineScope: CoroutineScope, editCurrencyFlow: StateFlow<CurrencyModel?>): Flow<List<CurrencyModel>>

    fun getCurrency(base: String): Flow<CurrencyModel>
}