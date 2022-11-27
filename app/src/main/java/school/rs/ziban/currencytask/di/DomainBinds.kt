package school.rs.ziban.currencytask.di

import dagger.Binds
import dagger.Module
import school.rs.ziban.currencytask.domain.interactor.CurrencyInteractor
import school.rs.ziban.currencytask.domain.interactor.CurrencyInteractorImpl

@Module
interface DomainBinds {

    @Binds
    fun provideCurrencyInteractor(interactor: CurrencyInteractorImpl): CurrencyInteractor
}