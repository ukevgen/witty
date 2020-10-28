package com.witty.bank.presentation.main.card.amount

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.witty.bank.R
import com.witty.bank.domain.model.CardType
import com.witty.bank.presentation.base.BaseToolbarFragment
import com.witty.bank.presentation.main.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_card_amount.*
import kotlinx.android.synthetic.main.layout_card_amount_bottom_view.*
import java.text.DecimalFormat

class CardAmountFragment : BaseToolbarFragment<CardAmountViewModel>() {

    private val activityViewModel: MainActivityViewModel by injectSharedFromActivity()
    private val decimalFormatter = DecimalFormat("0.00")

    override val viewModel: CardAmountViewModel by inject()
    override fun getLayout() = R.layout.fragment_card_amount

    override fun getTitleString() = "Amount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observeBankCard()
    }

    override fun handleViewCreation(view: View, savedInstanceState: Bundle?) {
        super.handleViewCreation(view, savedInstanceState)

        viewModel.getBankCardLiveData().observe(viewLifecycleOwner, Observer {
            tvCardDescription.text = "${it.getPaymentSystem()}*${it.cardNumber.takeLast(4)}"
            when (it.type) {
                CardType.VISA -> ivBankCard.setBackgroundResource(R.drawable.ic_visa)
                CardType.MASTERCARD -> ivBankCard.setBackgroundResource(R.drawable.ic_master_card)
                CardType.UNSUPPORTED -> ivBankCard.setBackgroundResource(R.drawable.ic_credit_card_24)
            }
        })

        btnChangeCard.setOnClickListener {
            activity?.onBackPressed()
        }

        btnAddMoney.setOnClickListener {
            viewModel.onAddMoneyClicked()
        }

        edtAmount.setRawInputType(Configuration.KEYBOARD_12KEY)
        edtAmount.addTextChangedListener(object : TextWatcher {
            private var currentAmount: String = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // empty
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != currentAmount) {
                    edtAmount.removeTextChangedListener(this)

                    val cleanString = s.toString().replace(".", "")

                    val newValue = cleanString.toDouble() / 100
                    val formattedAmount = decimalFormatter.format((newValue))

                    activityViewModel.amountToCharge = newValue
                    currentAmount = formattedAmount
                    edtAmount.setText(formattedAmount)
                    edtAmount.setSelection(formattedAmount.length)
                    edtAmount.addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // empty
            }
        })
    }
}