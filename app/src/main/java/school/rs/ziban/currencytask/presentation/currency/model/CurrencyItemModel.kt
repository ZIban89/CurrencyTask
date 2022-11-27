package school.rs.ziban.currencytask.presentation.currency.model

import androidx.annotation.DrawableRes

data class CurrencyItemModel(

    val currencyCode: String,
    val currencyFullName: String,
    @DrawableRes val currencyIcon: Int,
    var currencyRate: String
)