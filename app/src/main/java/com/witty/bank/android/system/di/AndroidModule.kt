package com.witty.bank.android.system.di

import android.app.NotificationManager
import android.content.Context
import com.witty.bank.android.di.qualifier.AppQualifier
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {

    @Provides
    fun provideNotificationManager(@AppQualifier context: Context): NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}