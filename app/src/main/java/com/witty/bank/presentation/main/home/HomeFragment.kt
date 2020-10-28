package com.witty.bank.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.witty.bank.R
import com.witty.bank.presentation.CurrencyUtil
import com.witty.bank.presentation.base.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseToolbarFragment<HomeFragmentViewModel>() {

    companion object {
        const val CURRENCY_CODE = "USD"
    }

    override val viewModel: HomeFragmentViewModel by inject()

    override fun getLayout() = R.layout.fragment_home

    override fun getTitleString() = "Home"

    override fun handleViewCreation(view: View, savedInstanceState: Bundle?) {
        super.handleViewCreation(view, savedInstanceState)
        viewModel.getUserBalance().observe(viewLifecycleOwner, Observer {
            tvBalance.text = CurrencyUtil.toCurrencyString(it, CURRENCY_CODE)
        })

        btnAdd.setOnClickListener {
            viewModel.onAddClicked()
        }
    }
}