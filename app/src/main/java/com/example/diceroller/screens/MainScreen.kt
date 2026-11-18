package com.example.diceroller.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.SystemClock
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diceroller.R
import com.example.diceroller.navigation.Screens
import kotlin.math.sqrt
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity

@Composable
fun MainScreen(navController: NavHostController) {

    var result by remember { mutableStateOf(1) }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var flickDirection by remember { mutableStateOf("") }

    // Function to roll the dice
    val rollDice = {
        result = (1..6).random()
    }

    val currentRollDice by rememberUpdatedState(rollDice)

    // Access the accelerometer
    val context = LocalContext.current

    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    val accelerometer = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    // Detect shaking
    DisposableEffect(sensorManager, accelerometer) {

        var lastShakeTime = 0L

        val sensorListener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent) {

                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val acceleration = sqrt(x * x + y * y + z * z)

                val currentTime = SystemClock.elapsedRealtime()

                if (acceleration > 11f &&
                    currentTime - lastShakeTime > 1000L
                ) {
                    lastShakeTime = currentTime
                    currentRollDice()
                }
            }

            override fun onAccuracyChanged(
                sensor: Sensor?,
                accuracy: Int
            ) {
                // No action needed
            }
        }

        if (accelerometer != null) {
            sensorManager.registerListener(
                sensorListener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }



    // Dice image
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val flickDistance = with(LocalDensity.current) {
        100.dp.toPx()
    }

    var totalDrag by remember { mutableStateOf(Offset.Zero) }
    var dragStartTime by remember { mutableStateOf(0L) }

    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(flickDistance) {
                    detectDragGestures(
                        onDragStart = {
                            totalDrag = Offset.Zero
                            dragStartTime = SystemClock.elapsedRealtime()
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            totalDrag += dragAmount
                        },
                        onDragEnd = {
                            val duration =
                                SystemClock.elapsedRealtime() - dragStartTime

                            if (duration in 1..350 &&
                                totalDrag.getDistance() > flickDistance
                            ) {
                                flickDirection =
                                    if (kotlin.math.abs(totalDrag.x) >
                                        kotlin.math.abs(totalDrag.y)
                                    ) {
                                        if (totalDrag.x > 0) "Right"
                                        else "Left"
                                    } else {
                                        if (totalDrag.y > 0) "Down"
                                        else "Up"
                                    }

                                rollDice()
                            }
                        }
                    )
                }
        )

        // A tua Column original fica aqui.

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
                onClick = { rollDice() },
                modifier = Modifier
                    .offset {
                        IntOffset(
                            offsetX.roundToInt(),
                            offsetY.roundToInt()
                        )
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()

                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
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
            Spacer(modifier = Modifier.height(16.dp))

            if (flickDirection.isNotEmpty()) {
                Text(
                    text = "Flick direction: $flickDirection",
                    fontSize = 18.sp
                )
            }
        }
    }

}
