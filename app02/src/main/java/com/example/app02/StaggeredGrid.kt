package com.example.app02

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StaggeredGird(modifier: Modifier = Modifier, row: Int = 3, content: @Composable () -> Unit) {
    Chip(txt = "测试内容文本")
}


@Composable
fun Chip(modifier: Modifier = Modifier, txt: String) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Gray, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp, 4.dp, 8.dp, 4.dp)
        ) {
            Box(modifier = Modifier.size(16.dp)) {
                Image(
                    painter = painterResource(id = R.mipmap.avatar_rengwuxian),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = txt, color = Color.Black)
        }
    }
}