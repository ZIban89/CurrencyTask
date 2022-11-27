package school.rs.ziban.currencytask.presentation.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import school.rs.ziban.currencytask.domain.interactor.CurrencyInteractor
import school.rs.ziban.currencytask.domain.model.Currency
import school.rs.ziban.currencytask.domain.model.CurrencyModel
import school.rs.ziban.currencytask.presentation.currency.mapper.CurrencyViewMapper
import school.rs.ziban.currencytask.presentation.currency.model.CurrencyItemModel
import java.math.BigDecimal
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(

    private val interactor: CurrencyInteractor,
    private val mapper: CurrencyViewMapper
) : ViewModel() {

    private val _currencyFlow = MutableStateFlow<List<CurrencyItemModel>?>(null)
    val currencyFlow get() = _currencyFlow.asStateFlow()

    private val editCurrencyFlow = MutableStateFlow<CurrencyModel?>(null)

    private var interactorScope: CoroutineScope? = null

    init {
        loadCurrency()
    }

    private fun loadCurrency() {
        interactorScope?.cancel()
        val scope =
            CoroutineScope(Dispatchers.Default + SupervisorJob()).also { interactorScope = it }
        viewModelScope.launch {
            interactor.getCurrencyList(
                scope,
                editCurrencyFlow
            ).onEach { list ->
                _currencyFlow.value = list.map { mapper.map(it) }
            }.collect()
        }

    }

    fun updateCurrentCurrencyAmount(currency: String, amount: String) {
        editCurrencyFlow.value = CurrencyModel(
            Currency.valueOf(currency),
            BigDecimal(amount),
            null
        )
    }

    override fun onCleared() {
        super.onCleared()
        interactorScope?.cancel()
    }
}