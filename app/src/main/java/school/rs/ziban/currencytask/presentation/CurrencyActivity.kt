package school.rs.ziban.currencytask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.rs.ziban.currencytask.R
import school.rs.ziban.currencytask.presentation.currency.CurrencyFragment

class CurrencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrencyFragment())
                .commitNow()
        }
    }
}