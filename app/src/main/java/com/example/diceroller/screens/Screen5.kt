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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diceroller.R

@Composable
fun ResultScreen5(
    navController: NavHostController,
    result: Int
) {

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

        Text(
            text = "Die Result: $result",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(imageResource),
            contentDescription = "Dice result $result"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Result: $result",
            fontSize = 18.sp
        )

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