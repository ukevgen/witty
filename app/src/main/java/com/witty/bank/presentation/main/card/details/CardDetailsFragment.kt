package com.witty.bank.presentation.main.card.details

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.witty.bank.R
import com.witty.bank.databinding.FragmentCardDetailsBinding
import com.witty.bank.domain.model.CardType
import com.witty.bank.presentation.base.BaseToolbarFragment
import com.witty.bank.presentation.main.card.CardExpirationTextWatcher
import com.witty.bank.presentation.main.card.CardNumberTextWatcher
import kotlinx.android.synthetic.main.fragment_card_details.*
import kotlinx.android.synthetic.main.layout_card_details_bottom_view.*


class CardDetailsFragment : BaseToolbarFragment<CardDetailsViewModel>() {

    lateinit var binding: FragmentCardDetailsBinding

    override val viewModel: CardDetailsViewModel by inject()

    override fun getTitleString() = "Card details"

    override fun getLayout() = R.layout.fragment_card_details

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

        edtCardNumber.addTextChangedListener(CardNumberTextWatcher())
        edtCardExpiration.addTextChangedListener(CardExpirationTextWatcher())

        edtCardExpiration.inputType = InputType.TYPE_CLASS_DATETIME
        edtCardExpiration.keyListener = DigitsKeyListener.getInstance("0123456789/")
        edtCardExpiration.filters = arrayOf<InputFilter>(LengthFilter(5))

        observeErrors()
        viewModel.getCardType().observe(viewLifecycleOwner, Observer {
            when (it) {
                CardType.VISA -> ivBankCard.setBackgroundResource(R.drawable.ic_visa)
                CardType.MASTERCARD -> ivBankCard.setBackgroundResource(R.drawable.ic_master_card)
                CardType.UNSUPPORTED -> ivBankCard.setBackgroundResource(R.drawable.ic_credit_card_24)
            }
        })

        btnContinue.postDelayed({
            lBottomView.visibility = View.VISIBLE
        }, 500L)

        btnContinue.setOnClickListener {
            viewModel.onContinueClicked(
                edtCardNumber.text?.toString(),
                edtCardCVV.text?.toString(),
                edtCardExpiration.text?.toString()
            )
        }
    }

    private fun observeErrors() {
        viewModel.getCarCVVError().observe(viewLifecycleOwner, Observer {
            tvCardCVVError.text = it
        })

        viewModel.getCarExpirationError().observe(viewLifecycleOwner, Observer {
            tvCardDateError.text = it
        })

        viewModel.getCarNumberError().observe(viewLifecycleOwner, Observer {
            tvCardNumberError.text = it
        })
    }
}