package com.witty.bank.presentation.main.account

import android.os.Bundle
import android.view.View
import com.witty.bank.R
import com.witty.bank.presentation.base.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseToolbarFragment<AccountFragmentViewModel>() {

    override val viewModel: AccountFragmentViewModel by inject()

    override fun getLayout() = R.layout.fragment_account

    override fun getTitleString() = "Add money with"

    override fun handleViewCreation(view: View, savedInstanceState: Bundle?) {
        super.handleViewCreation(view, savedInstanceState)

        lCardItem.setOnClickListener {
            viewModel.onMethodChosen()
        }
    }
}