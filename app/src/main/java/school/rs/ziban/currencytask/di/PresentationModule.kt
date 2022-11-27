package school.rs.ziban.currencytask.di

import android.content.Context
import dagger.Module
import dagger.Provides
import school.rs.ziban.currencytask.presentation.currency.mapper.CurrencyViewMapper

@Module
class PresentationModule {

    @Provides
    fun provideCurrencyViewMapper(context: Context): CurrencyViewMapper =
        CurrencyViewMapper(context)
}