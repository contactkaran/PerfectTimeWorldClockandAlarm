package com.example.perfecttimeworldclockandalarm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun WorldClockScreen(){
    Box(modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "World Clock Screen",
            fontSize = 48.sp)
    }
}