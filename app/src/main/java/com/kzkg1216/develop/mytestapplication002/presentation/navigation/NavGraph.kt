package com.kzkg1216.develop.mytestapplication002.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kzkg1216.develop.mytestapplication002.presentation.debug.DebugScreenState
import com.kzkg1216.develop.mytestapplication002.presentation.login.LoginScreenState
import com.kzkg1216.develop.mytestapplication002.presentation.register.RegisterScreenState
import com.kzkg1216.develop.mytestapplication002.presentation.settings.SettingsScreenState
import com.kzkg1216.develop.mytestapplication002.presentation.welcome.WelcomeScreenState

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.WELCOME_ROUTE,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(400)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(400))  },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(400)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(400)) }
    ) {

        composable(
            route = Destination.WELCOME_ROUTE
        ) {
            WelcomeScreenState(
                viewModel = hiltViewModel(),
                navigateToDebug = { navController.navigateToDebug(popUpToDestination = Destination.WELCOME_ROUTE, inclusive = false) },
                navigateToSettings = { navController.navigateToSettings(popUpToDestination = Destination.WELCOME_ROUTE, inclusive = false) },
                navigateToRegister = { navController.navigateToRegister(popUpToDestination = Destination.WELCOME_ROUTE, inclusive = false) },
                navigateToLogin = { navController.navigateToLogin(popUpToDestination = Destination.WELCOME_ROUTE, inclusive = false) }
            )
        }

        composable(
            route = Destination.DEBUG_ROUTE
        ) {
            DebugScreenState(
                viewModel = hiltViewModel(),
                navigateToWelcome = { navController.navigateUp() }
            )
        }

        composable(
            route = Destination.SETTINGS_ROUTE
        ) {
            SettingsScreenState(
                viewModel = hiltViewModel(),
                navigateToWelcome = { navController.navigateUp() }
            )
        }

        composable(
            route = Destination.REGISTER_ROUTE
        ) {
            RegisterScreenState(
                viewModel = hiltViewModel(),
                navigateToWelcome = { navController.navigateUp() },
                navigateToLogin = { navController.navigateToLogin(popUpToDestination = Destination.REGISTER_ROUTE) }
            )
        }

        composable(
            route = Destination.LOGIN_ROUTE
        ) {
            LoginScreenState(
                viewModel = hiltViewModel(),
                navigateToWelcome = { navController.navigateUp() },
                navigateToRegister = { navController.navigateToRegister(popUpToDestination = Destination.LOGIN_ROUTE) }
            )
        }
    }
}

fun NavController.navigateToWelcome(
    destination: String = Destination.WELCOME_ROUTE,
    popUpToDestination: String,
    inclusive: Boolean = true
) {
    this.navigate(destination) {
        popUpTo(popUpToDestination) {
            this.inclusive = inclusive
        }
    }
}

fun NavController.navigateToDebug(
    destination: String = Destination.DEBUG_ROUTE,
    popUpToDestination: String,
    inclusive: Boolean = true
) {
    this.navigate(destination) {
        popUpTo(popUpToDestination) {
            this.inclusive = inclusive
        }
    }
}

fun NavController.navigateToSettings(
    destination: String = Destination.SETTINGS_ROUTE,
    popUpToDestination: String,
    inclusive: Boolean = true
) {
    this.navigate(destination) {
        popUpTo(popUpToDestination) {
            this.inclusive = inclusive
        }
    }
}

fun NavController.navigateToRegister(
    destination: String = Destination.REGISTER_ROUTE,
    popUpToDestination: String,
    inclusive: Boolean = true
) {
    this.navigate(destination) {
        popUpTo(popUpToDestination) {
            this.inclusive = inclusive
        }
    }
}

fun NavController.navigateToLogin(
    destination: String = Destination.LOGIN_ROUTE,
    popUpToDestination: String,
    inclusive: Boolean = true
) {
    this.navigate(destination) {
        popUpTo(popUpToDestination) {
            this.inclusive = inclusive
        }
    }
}
