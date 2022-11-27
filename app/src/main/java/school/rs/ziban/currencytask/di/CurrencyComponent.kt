package school.rs.ziban.currencytask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import school.rs.ziban.currencytask.CurrencyApp
import school.rs.ziban.currencytask.presentation.currency.CurrencyFragment
import javax.inject.Singleton

@Component(modules = [DataModule::class, DataBinds::class, DomainBinds::class, PresentationModule::class])
@Singleton
abstract class CurrencyComponent {

    abstract fun inject(fragment: CurrencyFragment)

    companion object {
        private var currencyComponent: CurrencyComponent? = null

        fun createAndGet(application: CurrencyApp) : CurrencyComponent {
            return DaggerCurrencyComponent.builder().context(application).build().also {
                currencyComponent = it
            }
        }

        fun getComponent() = currencyComponent
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): CurrencyComponent
    }
}