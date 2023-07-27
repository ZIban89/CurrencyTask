package school.rs.ziban.currencytask

import android.app.Application
import school.rs.ziban.currencytask.di.CurrencyComponent

class CurrencyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CurrencyComponent.createAndGet(this)
    }

    fun x(){
        if (true)
            println(10)
            else println(1)
    }
}