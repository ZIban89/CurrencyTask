package school.rs.ziban.currencytask

import android.app.Application
import school.rs.ziban.currencytask.di.CurrencyComponent

class CurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CurrencyComponent.createAndGet(this)
    }
}