package com.witty.bank.navigation

import androidx.navigation.NavController
import com.witty.bank.android.system.ContextHolder

class AppNavComponentsNavigator(private val navController: NavController,
                                private val finishCurrentRoot: () -> Unit,
                                private val contextHolder: ContextHolder) : Navigator {

    override fun navigate(command: NavigationCommand) {
        realNavigation(command)
    }

    private fun realNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.Back -> {
            }
            is NavigationCommand.BackTo -> {
            }
            is NavigationCommand.To -> {
                if (hasCurrentDestinationDirection(command.directions)) {
                    navController.navigate(command.directions)
                }
            }
            is NavigationCommand.ToRoot -> {
            }
            is NavigationCommand.ToDirections -> {
                if (hasCurrentDestinationDirection(command.directions.actionId)) {
                    navController.navigate(command.directions)
                }
            }
            is NavigationCommand.ToNewRoot -> {
                if (hasCurrentDestinationDirection(command.directions)) {
                    navController.navigate(command.directions)
                    finishCurrentRoot()
                }
            }
        }
    }

    private fun hasCurrentDestinationDirection(actionId: Int): Boolean {
        return navController.currentDestination?.getAction(actionId) != null
    }
}