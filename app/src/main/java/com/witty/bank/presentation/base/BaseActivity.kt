package com.witty.bank.presentation.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.witty.bank.android.system.ContextHolder
import com.witty.bank.extension.hideKeyboard
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var contextHolder: ContextHolder

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        contextHolder.insertContext(this)
    }

    override fun onStop() {
        hideKeyboard()
        super.onStop()
    }

    override fun androidInjector() = dispatchingAndroidInjector

    inline fun <reified VM : ViewModel> inject(): Lazy<VM> =
        lazy {
            ViewModelProviders
                .of(this, viewModelFactory)
                .get(VM::class.java)
        }

    protected fun addFragment(@NonNull fragment: Fragment, @IdRes containerId: Int, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(containerId, fragment, tag)
            .commit()
    }

    protected fun replaceFragment(@NonNull fragment: Fragment,
                                  @IdRes containerId: Int,
                                  tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment, tag)
            .commit()
    }
}