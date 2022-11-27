package school.rs.ziban.currencytask.presentation.currency.mapper

import android.content.Context
import school.rs.ziban.currencytask.R
import school.rs.ziban.currencytask.domain.model.CurrencyModel
import school.rs.ziban.currencytask.presentation.currency.model.CurrencyItemModel
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyViewMapper @Inject constructor(private val context: Context) {

    fun map(model: CurrencyModel) = CurrencyItemModel(
            currencyCode = model.currency.name,
            currencyFullName = context.getString(model.currency.currencyFullName),
            currencyIcon = model.currency.currencyIcon,
            currencyRate = model.rate.setScale(5, RoundingMode.CEILING).toPlainString()
        )
}