package school.rs.ziban.currencytask.domain.model

import java.math.BigDecimal
import java.util.*

class CurrencyModel(

    val currency: Currency,
    val rate: BigDecimal,
    val date: Calendar?
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurrencyModel

        if (currency != other.currency) return false
        if (rate != other.rate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currency.hashCode()
        result = 31 * result + rate.hashCode()
        return result
    }
}