package school.rs.ziban.currencytask.di

import dagger.Binds
import dagger.Module
import school.rs.ziban.currencytask.data.repo.CurrencyRepositoryImpl
import school.rs.ziban.currencytask.domain.CurrencyRepository

@Module
interface DataBinds {

    @Binds
    fun bindCurrencyRepository(repo: CurrencyRepositoryImpl): CurrencyRepository
}