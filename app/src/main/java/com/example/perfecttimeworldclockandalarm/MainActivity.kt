package com.example.perfecttimeworldclockandalarm

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perfecttimeworldclockandalarm.ui.theme.*
import com.example.perfecttimeworldclockandalarm.ui.theme.Timer
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.min

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_title),
                        fontFamily = FontFamily.SansSerif
                    )
                },
                contentColor = Color.White,
                backgroundColor = Color(R.color.topBar)
            )
        },

        content = { paddingValues ->

            Column() {
//TODO- problem with setting surface background colors
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .background(Color.Yellow)
                ) {
                    UpperPanel(paddingValues = paddingValues)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally),
                    color = Color.Green
                )

                Spacer(modifier = Modifier.height(2.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .background(Color.LightGray, shape = RoundedCornerShape(15.dp))
                ) {

                    LowerPanel(paddingValues = paddingValues)
                }
            }
        },

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    )

    Box(
        Modifier
            .padding()
            .fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = Home.route) {
            composable(Home.route) {
                HomeScreen()
            }
            composable(WorldClock.route) {
                WorldClockScreen()
            }
            composable(Alarm.route) {
                AlarmScreen()
            }
            composable(StopWatch.route) {
                StopwatchScreen()
            }
            composable(Timer.route) {
                TimerScreen()
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpperPanel(paddingValues: PaddingValues) {
    val timeRightNow = LocalDateTime.now()
    val formatterSeconds = DateTimeFormatter.ofPattern("ss")
    val formatterMinutes = DateTimeFormatter.ofPattern("mm")
    val formatterHours = DateTimeFormatter.ofPattern("hh")
    val seconds = timeRightNow.format(formatterSeconds).toInt()
    val minutes = timeRightNow.format(formatterMinutes).toInt()
    val hours = timeRightNow.format(formatterHours).toInt()



    Column(modifier = Modifier.padding(vertical = 15.dp)) {
        Text(
            text = "Current Time",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(fraction = 0.7f)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)
                .clip(
                    CircleShape
                )
                .background(Color.Magenta)
        ) {

            //adding innermost circle for dial base
            Card(elevation = 16.dp, modifier = Modifier.clip(CircleShape)) {


                Box(
                    modifier = Modifier
                        .fillMaxSize(fraction = 0.75f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(color = Color.Cyan)
                ) {

                }
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
            ) {
                //expanding onDraw
                //Draw circle, checking depending on screen size which is lower
                val diameter =
                    min(size.width, size.height) * 0.9f //leaving some space around the circle
                val radius = diameter / 2

                //drawing markers, starting from canvas center
                val start = center - Offset(0f, radius) // takes us to top of circle
                val end = start + Offset(0f, radius / 40f)

                //rotating the canvas to draw four markings on circular clock, by 360 degrees & repeated 4 times

                repeat(4) {

                    rotate(it / 4f * 360) {

                        drawLine(
                            color = Color.White,
                            start = start,
                            end = end,
                            strokeWidth = 5.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
                }


                val secondRatio = seconds / 60f
                val minutesRatio = minutes / 60f
                val hoursRatio = hours / 12f

                //hour hand drawing
                rotate(hoursRatio * 360, center) {
                    drawLine(
                        color = Color.Yellow,
                        start = center - Offset(0f, radius * 0.4f), //4% of radius
                        end = center + Offset(
                            0f,
                            radius * 0.1f
                        ), //does not end at center, extends a bit on the other side
                        strokeWidth = 3.8.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                //minute hand drawing
                rotate(minutesRatio * 360, center) {
                    drawLine(
                        color = Color.White,
                        start = center - Offset(0f, radius * 0.7f), //4% of radius
                        end = center + Offset(
                            0f,
                            radius * 0.1f
                        ), //does not end at center, extends a bit on the other side
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                //seconds hand drawing
                rotate(secondRatio * 360, center) {
                    drawLine(
                        color = Color.LightGray,
                        start = center - Offset(0f, radius * 0.9f), //4% of radius
                        end = center + Offset(
                            0f,
                            radius * 0.1f
                        ), //does not end at center, extends a bit on the other side
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                //drawing the innermost circle - spindal
                drawCircle(
                    color = Color.Green,
                    radius = 5.dp.toPx(),
                    center = center
                )

            }
        }


    }
}



@Composable
fun BottomNavigation(navController: NavController) {
    val destinationList = listOf<Destinations>(
        Home,
        WorldClock,
        Alarm,
        StopWatch,
        Timer
    )
    //the variable selectedIndex will remember index for selected screen
    val selectedIndex = rememberSaveable() {
        mutableStateOf(0)
    }
    BottomNavigation(
        modifier = Modifier.shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            clip = true,
            ambientColor = Color.Red,
            spotColor = Color.Blue
        )
    ) {
        destinationList.forEachIndexed { index, destinations ->
            BottomNavigationItem(
                label = { Text(text = destinations.title) },
                icon = {
                    Icon(
                        imageVector = destinations.icon,
                        contentDescription = destinations.title
                    )
                },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(destinationList[index].route) {
                        popUpTo(Home.route)
                        launchSingleTop = true
                    }
                })
        }
    }
}


@Composable
fun LowerPanel(paddingValues: PaddingValues) {

//TODO - seconds should run real time

    val sdf = SimpleDateFormat("hh:mm:ss:aa")
    val currentTime = sdf.format(Date())

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = currentTime,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
        //user Location
        Text(
            text = "Dummy Location, Desh",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color.Gray.copy(alpha = 0.6f),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    HomeScreen()
}