package com.example.app02

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)//如果外层是用的surface，里面则用onSurface
            .clickable { }//可点击，可以实现Material的水波纹效果
            .padding(16.dp)//.clickable { }.padding(16.dp)和.padding(16.dp).clickable { }是不同的效果；

    ) {
        Surface(//TODO 会现在它里面的内容大小
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
            modifier = Modifier.size(50.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.mipmap.avatar_rengwuxian),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "alfred Sisley", fontWeight = FontWeight.Bold)
            //TODO 隐式传参数将外层的透明度传入到里面的控件；infix  函数
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3  min ago", style = MaterialTheme.typography.body1)
            }

        }
    }
}