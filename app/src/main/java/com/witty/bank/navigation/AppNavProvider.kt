package com.witty.bank.navigation

import androidx.navigation.NavController

interface AppNavProvider {
    fun getNavController(): NavController
    fun finish()
}