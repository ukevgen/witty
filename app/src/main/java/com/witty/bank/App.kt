package com.witty.bank

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.witty.bank.android.di.component.app.DaggerApplicationComponent
import com.witty.bank.android.di.module.ApplicationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class App : Application(), HasAndroidInjector, LifecycleObserver {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .create(this)
            .inject(this)

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        JodaTimeAndroid.init(this)

    }
}

