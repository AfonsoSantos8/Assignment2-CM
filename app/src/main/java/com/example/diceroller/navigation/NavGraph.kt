package com.example.diceroller.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diceroller.screens.MainScreen
import com.example.diceroller.screens.ResultScreen1
import com.example.diceroller.screens.ResultScreen2
import com.example.diceroller.screens.ResultScreen3
import com.example.diceroller.screens.ResultScreen4
import com.example.diceroller.screens.ResultScreen5
import com.example.diceroller.screens.ResultScreen6

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    ) {

        composable(route = Screens.Main.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screens.Result1.route) {
            ResultScreen1(navController = navController)
        }

        composable(
            route = Screens.Result2.route,
            arguments = listOf(
                navArgument("result") {
                    type = NavType.IntType
                }
            )
        ) { navBackStack ->

            val result = navBackStack.arguments?.getInt("result") ?: 1

            ResultScreen2(
                navController = navController,
                initialResult = result
            )
        }

        composable(
            route = Screens.Result3.route,
            arguments = listOf(
                navArgument("result") {
                    type = NavType.IntType
                }
            )
        ) { navBackStack ->

            val result = navBackStack.arguments?.getInt("result") ?: 1

            ResultScreen3(
                navController = navController,
                initialResult = result
            )
        }

        composable(route = Screens.Result4.route) {
            ResultScreen4(navController = navController)
        }

        composable(
            route = Screens.Result5.route,
            arguments = listOf(
                navArgument("result") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val result = backStackEntry.arguments?.getInt("result") ?: 5

            ResultScreen5(
                navController = navController,
                result = result
            )
        }

        composable(
            route = Screens.Result6.route,
            arguments = listOf(
                navArgument("result") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val result = backStackEntry.arguments?.getInt("result") ?: 6

            ResultScreen6(
                navController = navController,
                currentResult = result
            )
        }
    }
}