package school.rs.ziban.currencytask.data.model.mapper

import school.rs.ziban.currencytask.data.model.dto.CurrencyDto
import school.rs.ziban.currencytask.domain.model.Currency
import school.rs.ziban.currencytask.domain.model.CurrencyModel

fun CurrencyDto.map(): CurrencyModel =
    CurrencyModel(
        currency = Currency.valueOf(base),
        rate = rate,
        date = date
    )