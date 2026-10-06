package com.example.diceroller.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diceroller.R
import com.example.diceroller.navigation.Screens

@Composable
fun MainScreen(navController: NavHostController) {

    var result by remember {
        mutableStateOf(1)
    }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = (1..6).random()
            }
        ) {
            Text(
                text = "Roll",
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                when (result) {
                    1 -> navController.navigate(Screens.Result1.route)
                    2 -> navController.navigate("result_2/$result")
                    3 -> navController.navigate("result_3/$result")
                    4 -> navController.navigate(Screens.Result4.route)
                    5 -> navController.navigate("result_5/$result")
                    6 -> navController.navigate("result_6/$result")
                }
            }
        ) {
            Text(
                text = "Go to Result",
                fontSize = 20.sp
            )
        }
    }
}