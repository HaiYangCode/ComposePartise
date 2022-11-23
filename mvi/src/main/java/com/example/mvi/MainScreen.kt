package com.example.mvi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val (text, setText) = remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = text, fontWeight = FontWeight.Bold)
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
        )
        TextField(value = text, onValueChange = setText)
    }
}