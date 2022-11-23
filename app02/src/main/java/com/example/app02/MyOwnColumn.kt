package com.example.app02

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun MyOwnColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        //1. 得到一个所有的placeable集合
        val placeables = measurables.map { measurables ->
            measurables.measure(constraints)
        }
        var yPosition = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            //在layout方法中计算placeable的具体的某一个控件的位置
            placeables.map { placeable ->
                placeable.placeRelative(0, y = yPosition)
                yPosition += placeable.height

            }
        }
    }
}

@Composable
fun MyOwnColumnSimple(modifier: Modifier = Modifier) {
    MaterialTheme() {
        MyOwnColumn {
            Text("文本内容1")
            Text("文本内容1文本内容1文本内容1")
            Text("文本内容1文本内容1")
            Text("文本内容1文本内容1文本内容1文本内容1")
        }

    }
}