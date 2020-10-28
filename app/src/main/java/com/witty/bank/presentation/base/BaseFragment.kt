package com.witty.bank.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.witty.bank.extension.hideKeyboard
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModel: VM

    @LayoutRes
    protected abstract fun getLayout(): Int


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onStop() {
        activity?.hideKeyboard()
        super.onStop()
    }

    override fun androidInjector() = fragmentInjector

    protected fun addFragment(@NonNull fragment: Fragment, @IdRes containerId: Int, tag: String) {
        childFragmentManager.beginTransaction()
            .add(containerId, fragment, tag)
            .commit()
    }

    protected fun replaceFragment(@NonNull fragment: Fragment,
                                  @IdRes containerId: Int,
                                  tag: String) {
        childFragmentManager
            .beginTransaction()
            .replace(containerId, fragment, tag)
            .commit()
    }

    inline fun <reified VM : ViewModel> inject(): Lazy<VM> = lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(VM::class.java)
    }

    inline fun <reified VM : ViewModel> injectSharedFromActivity(): Lazy<VM> = lazy {
        val act = activity
        val provider = if (act == null) {
            ViewModelProviders.of(this, viewModelFactory)
        } else {
            ViewModelProviders.of(act, viewModelFactory)
        }
        provider.get(VM::class.java)
    }

    inline fun <reified VM : ViewModel> injectSharedFromParentFragment(): Lazy<VM> = lazy {
        ViewModelProviders.of(parentFragment ?: this, viewModelFactory)
            .get(VM::class.java)
    }

    // Private
}