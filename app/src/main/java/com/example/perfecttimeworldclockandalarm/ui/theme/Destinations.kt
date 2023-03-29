package com.example.perfecttimeworldclockandalarm.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val route: String
    val icon: ImageVector
    val title: String
}

object Home: Destinations{
    override val route = "Home"
    override val icon = Icons.Filled.Home
    override val title = "Home"
}

object WorldClock: Destinations{
    override val route = "World Clock"
    override val icon = Icons.Filled.LocationOn
    override val title = "World Clock"
}

object Alarm: Destinations{
    override val route = "Alarm"
    override val icon = Icons.Filled.Add
    override val title = "Alarm"
}

object StopWatch: Destinations{
    override val route = "StopWatch"
    override val icon = Icons.Filled.Settings
    override val title = "Stop Watch"
}

object Timer: Destinations{
    override val title = "Timer"
    override val icon = Icons.Filled.Delete
    override val route = "Timer"
}