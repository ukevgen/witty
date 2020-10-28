package com.witty.bank.presentation.main.card.verification.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.witty.bank.R
import com.witty.bank.databinding.FragmentPaymentResultBinding
import com.witty.bank.presentation.base.BaseToolbarFragment
import com.witty.bank.presentation.main.MainActivityViewModel
import java.text.DecimalFormat

class PaymentResultFragment : BaseToolbarFragment<PaymentResultViewModel>() {

    private val decimalFormatter = DecimalFormat("0.00")
    private val activityViewModel: MainActivityViewModel by injectSharedFromActivity()

    lateinit var binding: FragmentPaymentResultBinding

    override val viewModel: PaymentResultViewModel by inject()

    override fun getLayout() = R.layout.fragment_payment_result

    override fun getTitleString() = "Payment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBankCard()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun handleViewCreation(view: View, savedInstanceState: Bundle?) {
        super.handleViewCreation(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.amount = decimalFormatter.format(activityViewModel.amountToCharge)

    }
}