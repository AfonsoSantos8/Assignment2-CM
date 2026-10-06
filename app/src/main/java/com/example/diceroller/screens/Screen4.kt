package com.example.diceroller.screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diceroller.navigation.Screens

@Composable
fun ResultScreen4(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Die Result: 4",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select a screen to navigate to:",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Screens.Result1.route)
            }
        ) {
            Text("Go to Screen 1")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("result_2/2")
            }
        ) {
            Text("Go to Screen 2")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("result_3/3")
            }
        ) {
            Text("Go to Screen 3")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate(Screens.Result4.route)
            }
        ) {
            Text("Go to Screen 4")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("result_5/5")
            }
        ) {
            Text("Go to Screen 5")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("result_6/6")
            }
        ) {
            Text("Go to Screen 6")
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