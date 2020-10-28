package com.witty.bank.toolbar

interface ToolbarActivity {
    fun setToolBarTitle(title: String)
    fun renderToolbarNavigation(visible: Boolean,
                                startButtonClickListener: (() -> Unit?)?,
                                endButtonClickListener: (() -> Unit)?) {
    }

    fun activateToolbarNavigation(isActive: Boolean) {}
    fun setToolbarVisibility(isVisible: Boolean) {}
}