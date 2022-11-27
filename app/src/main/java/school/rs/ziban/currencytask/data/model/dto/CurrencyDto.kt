package school.rs.ziban.currencytask.data.model.dto

import kotlinx.serialization.Serializable
import school.rs.ziban.currencytask.data.model.serializer.BigDecimalSerializer
import school.rs.ziban.currencytask.data.model.serializer.CalendarSerializer
import java.math.BigDecimal
import java.util.Calendar

@Serializable
class CurrencyDto(

    @Serializable(with = CalendarSerializer::class)
    val date: Calendar,
    @Serializable(with = BigDecimalSerializer::class)
    val rate: BigDecimal,
    val base: String
)