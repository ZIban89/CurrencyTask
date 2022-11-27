package school.rs.ziban.currencytask.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import school.rs.ziban.currencytask.R

enum class Currency(
    @StringRes val currencyFullName: Int,
    @DrawableRes val currencyIcon: Int
) {

    USD(R.string.currency_usd_name, R.drawable.ic_usd_flag),
    EUR(R.string.currency_eur_name, R.drawable.ic_eur_flag),
    CHF(R.string.currency_chf_name, R.drawable.ic_chf_flag),
    HRK(R.string.currency_hrk_name, R.drawable.ic_hrk_flag),
    MXN(R.string.currency_mxn_name, R.drawable.ic_mxn_flag),
    ZAR(R.string.currency_zar_name, R.drawable.ic_zar_flag),
    CNY(R.string.currency_cny_name, R.drawable.ic_cny_flag),
    THB(R.string.currency_thb_name, R.drawable.ic_thb_flag),
    AUD(R.string.currency_aud_name, R.drawable.ic_aud_flag),
    ILS(R.string.currency_ils_name, R.drawable.ic_ils_flag),
    KRW(R.string.currency_krw_name, R.drawable.ic_krw_flag),
    JPY(R.string.currency_jpy_name, R.drawable.ic_jpy_flag),
    PLN(R.string.currency_pln_name, R.drawable.ic_pln_flag),
    GBP(R.string.currency_gbp_name, R.drawable.ic_gbp_flag),
    IDR(R.string.currency_idr_name, R.drawable.ic_idr_flag),
    HUF(R.string.currency_huf_name, R.drawable.ic_huf_flag),
    PHP(R.string.currency_php_name, R.drawable.ic_php_flag),
    TRY(R.string.currency_try_name, R.drawable.ic_try_flag),
    RUB(R.string.currency_rub_name, R.drawable.ic_rub_flag),
    HKD(R.string.currency_hkd_name, R.drawable.ic_hkd_flag),
    ISK(R.string.currency_isk_name, R.drawable.ic_isk_flag),
    DKK(R.string.currency_dkk_name, R.drawable.ic_dkk_flag),
    CAD(R.string.currency_cad_name, R.drawable.ic_cad_flag),
    MYR(R.string.currency_myr_name, R.drawable.ic_myr_flag),
    BGN(R.string.currency_bgn_name, R.drawable.ic_bgn_flag),
    NOK(R.string.currency_nok_name, R.drawable.ic_nok_flag),
    RON(R.string.currency_ron_name, R.drawable.ic_ron_flag),
    SGD(R.string.currency_sgd_name, R.drawable.ic_sgd_flag),
    CZK(R.string.currency_czk_name, R.drawable.ic_czk_flag),
    SEK(R.string.currency_sek_name, R.drawable.ic_sek_flag),
    NZD(R.string.currency_nzd_name, R.drawable.ic_nzd_flag),
    BRL(R.string.currency_brl_name, R.drawable.ic_brl_flag),
    INR(R.string.currency_inr_name, R.drawable.ic_inr_flag),
    UNKNOWN(R.string.currency_not_found, R.drawable.ic_baseline_dangerous_48 )
}