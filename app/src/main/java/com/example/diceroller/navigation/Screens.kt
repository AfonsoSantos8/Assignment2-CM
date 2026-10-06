package com.example.diceroller.navigation

sealed class Screens(val route: String) {
    object Main : Screens("main_screen")
    object Result1 : Screens("result_1")
    object Result2 : Screens("result_2/{result}")
    object Result3 : Screens("result_3/{result}")
    object Result4 : Screens("result_4")
    object Result5 : Screens("result_5/{result}")
    object Result6 : Screens("result_6/{result}")
}