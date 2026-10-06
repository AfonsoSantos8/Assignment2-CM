package com.example.diceroller.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun ResultScreen6(
    navController: NavHostController,
    currentResult: Int
) {

    var secondResult by remember {
        mutableStateOf<Int?>(null)
    }

    val currentImage = when (currentResult) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val secondImage = when (secondResult) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Die Result: 6 (Dice Game)",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {

                Text("Current Die")

                Image(
                    painter = painterResource(currentImage),
                    contentDescription = "Current dice: $currentResult"
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {

                Text("Second Die")

                if (secondImage != null) {
                    Image(
                        painter = painterResource(secondImage),
                        contentDescription = "Second dice: $secondResult"
                    )
                } else {
                    Text(
                        text = "?",
                        fontSize = 40.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                secondResult = (1..6).random()
            }
        ) {
            Text("Roll Second Die")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (secondResult != null) {

            if (secondResult!! >= currentResult) {

                Text(
                    text = "You Won! ($currentResult ≤ $secondResult)",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {

                        when (secondResult) {

                            1 -> navController.navigate(Screens.Result1.route)

                            2 -> navController.navigate(
                                "result_2/$secondResult"
                            )

                            3 -> navController.navigate(
                                "result_3/$secondResult"
                            )

                            4 -> navController.navigate(
                                Screens.Result4.route
                            )

                            5 -> navController.navigate(
                                "result_5/$secondResult"
                            )

                            6 -> navController.navigate(
                                "result_6/$secondResult"
                            )
                        }
                    }
                ) {
                    Text("Go to Result")
                }

            } else {

                Text(
                    text = "You Lost! ($currentResult > $secondResult)",
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }
    }
}