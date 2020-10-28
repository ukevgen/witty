package com.witty.bank.presentation.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.witty.bank.R
import com.witty.bank.navigation.AppNavProvider
import com.witty.bank.presentation.base.BaseActivity
import com.witty.bank.toolbar.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar_centered_bold.view.*

class MainActivity : BaseActivity<MainActivityViewModel>(), AppNavProvider, ToolbarActivity {

    override val viewModel: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setToolBarTitle(title: String) {
        toolbar.toolbarTitle.text = title
    }

    override fun getNavController(): NavController {
        return findNavController(R.id.nav_graph_add_card_container)
    }
}