package school.rs.ziban.currencytask.data.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

class CalendarSerializer : KSerializer<Calendar> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("CalendarSerializer", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Calendar =
         Calendar.getInstance().apply { timeInMillis = decoder.decodeLong() }


    override fun serialize(encoder: Encoder, value: Calendar) =
        encoder.encodeLong(value.timeInMillis)

}