package com.example.app02

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LayoutStudy() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "thisIsTitle")
        }, actions = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        })
    }) { innerPadding ->
        BodyContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {

    Column(modifier = modifier.padding(8.dp)) {
        Text(text = "文本1")
        Text(text = "文本1文本1文本1文本1文本1文本1文本1文本1文本1文本1")
    }
}