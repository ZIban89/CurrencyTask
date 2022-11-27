package school.rs.ziban.currencytask.domain.interactor

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import school.rs.ziban.currencytask.domain.CurrencyRepository
import school.rs.ziban.currencytask.domain.UPDATE_INTERVAL_MILLISECONDS
import school.rs.ziban.currencytask.domain.model.Currency
import school.rs.ziban.currencytask.domain.model.CurrencyModel
import java.math.RoundingMode
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(

    private val currencyRepo: CurrencyRepository
) : CurrencyInteractor {

    override fun getCurrencyList(
        coroutineScope: CoroutineScope,
        editCurrencyFlow: StateFlow<CurrencyModel?>
    ): Flow<List<CurrencyModel>> =
        editCurrencyFlow.flatMapLatest { editModel ->
            getFilteredCurrenciesFlow().flowOn(Dispatchers.IO)
                .distinctUntilChanged { old, new ->
                    (new.first().date?.timeInMillis ?: 0L) <= (old.first().date?.timeInMillis ?: 0L)
                }.transform { value ->
                    if (editModel == null) emit(value)
                    else {
                        val responseModel =
                            value.find { it.currency == editModel.currency } ?: return@transform
                        val coefficient = calculateCoefficient(editModel, responseModel)
                        emit(
                            listOf(editModel) + value.filter { it.currency != editModel.currency }
                                .map {
                                    CurrencyModel(
                                        it.currency,
                                        it.rate.multiply(coefficient)
                                            .setScale(5, RoundingMode.CEILING),
                                        it.date
                                    )
                                }
                        )
                    }
                }
        }

    private suspend fun getFilteredCurrenciesFlow() = flow {
        while (currentCoroutineContext().job.isActive) {
            emit(currencyRepo.getCurrencyList().filter { it.currency != Currency.UNKNOWN })
            delay(UPDATE_INTERVAL_MILLISECONDS)
        }
    }

    private fun calculateCoefficient(editModel: CurrencyModel, responseModel: CurrencyModel) =
        editModel.rate.divide(
            responseModel.rate,
            RoundingMode.CEILING
        )


    override fun getCurrency(base: String): Flow<CurrencyModel> = flow {
        currencyRepo.getCurrency(base)
    }
}