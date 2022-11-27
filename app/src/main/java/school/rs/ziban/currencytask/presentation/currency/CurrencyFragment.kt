package school.rs.ziban.currencytask.presentation.currency

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import school.rs.ziban.currencytask.databinding.FragmentCurrencyBinding
import school.rs.ziban.currencytask.di.CurrencyComponent
import school.rs.ziban.currencytask.domain.interactor.CurrencyInteractor
import school.rs.ziban.currencytask.presentation.currency.mapper.CurrencyViewMapper
import javax.inject.Inject

class CurrencyFragment : Fragment() {

    @Inject
    lateinit var currencyInteractor: CurrencyInteractor

    @Inject
    lateinit var mapper: CurrencyViewMapper

    private val viewModel: CurrencyViewModel by viewModelCreator {
        CurrencyViewModel(currencyInteractor, mapper)
    }

    private var _binding: FragmentCurrencyBinding? = null
    private val binding: FragmentCurrencyBinding get() = _binding!!

    private val onBaseAmountChanged = { currency: String, amount: String ->
        viewModel.updateCurrentCurrencyAmount(currency, amount)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CurrencyComponent.getComponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CurrencyAdapter(onBaseAmountChanged)
        binding.currencyRecycler.recycledViewPool.setMaxRecycledViews(0, 20)
        binding.currencyRecycler.adapter = adapter
        binding.currencyRecycler.addItemDecoration(
            DividerItemDecoration(
                binding.currencyRecycler.context,
                LinearLayoutManager.VERTICAL
            )
        )
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currencyFlow.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}