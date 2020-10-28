package com.witty.bank.presentation.main.card.verification.verify

import android.os.Bundle
import android.view.View
import com.witty.bank.R
import com.witty.bank.presentation.base.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_card_verification.*

class CardVerificationFragment : BaseToolbarFragment<CardVerificationViewModel>() {

    override val viewModel: CardVerificationViewModel by inject()

    override fun getLayout() = R.layout.fragment_card_verification

    override fun getTitleString() = "3DS Verification"

    override fun handleViewCreation(view: View, savedInstanceState: Bundle?) {
        super.handleViewCreation(view, savedInstanceState)

        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        btnNext.setOnClickListener {
            viewModel.onNextClicked()
        }
    }

}