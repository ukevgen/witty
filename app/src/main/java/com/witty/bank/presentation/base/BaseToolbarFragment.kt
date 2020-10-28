package com.witty.bank.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.witty.bank.toolbar.ToolbarActivity

abstract class BaseToolbarFragment<VM : BaseViewModel> : BaseFragment<VM>() {

    protected var toolbarActivity: ToolbarActivity? = null

    abstract fun getTitleString(): String?

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        toolbarActivity = context as? ToolbarActivity
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarActivity?.setToolBarTitle(getTitleString() ?: "-")
        handleViewCreation(view, savedInstanceState)
    }

    open fun handleViewCreation(view: View, savedInstanceState: Bundle?) {
        //Child can override it
    }
}