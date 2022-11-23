package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp

import com.example.composedemo.ui.theme.ComposeDemoTheme


import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    conversation(messageList())
//                    MessageCard(Message("标题", "文本内容"))
                }
            }
        }
    }
}


@Composable
fun MessageCard(msg: Message) {
    Row {
        androidx.compose.material.Surface(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
            modifier = Modifier
                .size(60.dp)
                .padding(16.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.mipmap.avatar_rengwuxian),
                contentDescription = "这是字符串测试",
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        CircleShape
                    )
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        //TODO compose函数中的私有控件，避免在多个compose函数中值不一致的问题。
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
        Column(
            modifier = Modifier
                .padding(10.dp, 20.dp, 10.dp, 20.dp)
                .background(MaterialTheme.colors.background)
                .clickable { isExpanded = !isExpanded }
        ) {
            Text(text = msg.title, color = MaterialTheme.colors.secondaryVariant)
            Spacer(modifier = Modifier.height(4.dp))
            //TODO 什么是surface？
            Surface(
                color = surfaceColor,
                shape = MaterialTheme.shapes.medium,
                elevation = 2.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Black,
                    text = "Hello ${msg.content}!",

                    style = MaterialTheme.typography.body1,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.padding(all = 4.dp)
                )
            }
        }
    }

}

@Composable
fun conversation(messages: List<Message>) {
    LazyColumn {

        items(messages) { msg ->
            MessageCard(msg = msg)
        }

    }
}

fun messageList(): List<Message> {
    val data = mutableListOf<Message>()
    (1..30).forEach {
        data.add(Message("title = $it", content = "content == ${getContent()}"))
    }
    return data
}

fun getContent(): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
    return Random(100).nextInt().toString()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        MessageCard(Message("标题", "文本内容"))
    }
}