package com.bjelor.sportify.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import com.bjelor.sportify.ui.addentry.AddEntryScreen
import com.bjelor.sportify.ui.resultlist.ResultListScreen
import com.bjelor.sportify.ui.theme.SportifyTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SportifyComposeApp() {
    SportifyTheme {
        val navController = rememberAnimatedNavController()

        AnimatedNavHost(
            navController = navController,
            startDestination = NavRoute.ResultList
        ) {
            composable(
                route = NavRoute.ResultList,
                enterTransition = {
                    slideInHorizontally { fullWidth -> fullWidth }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        NavRoute.AddEntry -> {
                            slideOutOfContainer(AnimatedContentScope.SlideDirection.Start)
                        }
                        else -> {
                            ExitTransition.None
                        }
                    }
                },
                popEnterTransition = {
                    when (initialState.destination.route) {
                        NavRoute.AddEntry -> {
                            slideIntoContainer(AnimatedContentScope.SlideDirection.End)
                        }
                        else -> {
                            EnterTransition.None
                        }
                    }
                }
            ) {
                ResultListScreen {
                    navController.navigate(NavRoute.AddEntry)
                }
            }
            composable(
                route = NavRoute.AddEntry,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Start)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.End)
                },
            ) {
                AddEntryScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}
