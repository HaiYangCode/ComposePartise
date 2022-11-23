package com.example.app02

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.firstBaselineToTop(firstBaselineToTopDp: Dp): Modifier {

    return this.then(layout { measurable, constraints ->
        val measure = measurable.measure(constraints)
        val firstBaseline = measure[FirstBaseline]
        //得到的是坐标y的值
        val y = firstBaselineToTopDp.roundToPx() - firstBaseline
        val height = y + measure.height
        layout(measure.width, height) {
            measure.placeRelative(0, y)
        }
    })
}

@Composable
fun TextWithPaddingToBaseline() {
    MaterialTheme() {
        Text(
            text = "Hi there", modifier = Modifier
                .firstBaselineToTop(24.dp)
                .background(color = Color.Red)
        )

    }
}