package school.rs.ziban.currencytask.presentation.currency

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.*
import school.rs.ziban.currencytask.databinding.CurrencyItemViewBinding
import school.rs.ziban.currencytask.presentation.currency.model.CurrencyItemModel

class CurrencyAdapter(

    private val onAmountChanged: (String, String) -> Unit
) : ListAdapter<CurrencyItemModel, CurrencyVH>(CurrencyCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        val binding = CurrencyItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrencyVH(binding, onAmountChanged)
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int, payloads: MutableList<Any>) {
        when {
            payloads.isEmpty() -> onBindViewHolder(holder, position)
            payloads.first() == true -> holder.updateRate(getItem(position).currencyRate)
            else -> throw IllegalStateException()
        }
    }
}

class CurrencyVH(

    private val binding: CurrencyItemViewBinding,
    private val onBaseAmountChanged: (String, String) -> Unit
) : ViewHolder(binding.root) {

    private var model: CurrencyItemModel? = null

    private var job: Job? = null

    private val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            job?.cancel()
            job = CoroutineScope(SupervisorJob()).launch {
                delay(700)
                model?.let {
                    it.currencyRate = if(!s.isNullOrBlank()) s.toString() else "0"
                    onBaseAmountChanged(it.currencyCode, it.currencyRate)
                }
            }
        }
    }

    init {
        itemView.setOnClickListener {
            it.requestFocus(binding.editTextNumberDecimal.id)
        }
        binding.editTextNumberDecimal.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                model?.let {
                    onBaseAmountChanged(
                        it.currencyCode, it.currencyRate
                    )
                }
                (v as EditText).addTextChangedListener(watcher)
            } else (v as EditText).removeTextChangedListener(watcher)
        }
    }

    fun bind(model: CurrencyItemModel) {
        this.model = model
        binding.currencyCodeText.text = model.currencyCode
        binding.currencyIconView.setImageResource(model.currencyIcon)
        binding.currencyNameText.text = model.currencyFullName
        binding.editTextNumberDecimal.setText(model.currencyRate)
    }

    fun updateRate(rate: String) {
        model?.currencyRate = rate
        binding.editTextNumberDecimal.setText(rate)
    }
}

class CurrencyCallback : DiffUtil.ItemCallback<CurrencyItemModel>() {

    override fun areItemsTheSame(
        oldItem: CurrencyItemModel,
        newItem: CurrencyItemModel
    ): Boolean =
        oldItem.currencyCode == newItem.currencyCode

    override fun areContentsTheSame(
        oldItem: CurrencyItemModel,
        newItem: CurrencyItemModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: CurrencyItemModel, newItem: CurrencyItemModel): Any? {
        return if (
            oldItem.currencyRate != newItem.currencyRate
        ) true
        else null
    }
}