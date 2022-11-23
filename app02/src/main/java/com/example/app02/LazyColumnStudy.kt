package com.example.app02

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun LazyColumnStudy() {
    Scaffold(topBar = {
        TopAppBar() {
            Text(text = "Title")
        }
    }) {

    }
}

@Composable
fun SimpleColumn(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text(text = "content  = $it")
        }
    }
}

@Composable
fun SimpleLazyColumn(modifier: Modifier = Modifier) {

    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(50) {
            Text(text = "content  = $it")
        }
    }
}

//手动修改状态调整滚动位置
@Composable
fun SimpleList() {
    val scrollState = rememberLazyListState()
    val listSize = 100
    val rememberCoroutineScope = rememberCoroutineScope()
    Column {
        Row {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    rememberCoroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }

                }) {
                Text(text = "Scrolling  to the top")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    rememberCoroutineScope.launch {
                        scrollState.animateScrollToItem(listSize - 1)
                    }
                }) {
                Text(text = "Scrolling  to the end")
            }

        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }
}


@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(data = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fd295ea151517ceb4f6ba6e15ccea80d2ccebaa924ea9e-6aoop6_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670142339&t=88989a82a781f1358eaaeee6772b3a03"),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "content == " + index, style = MaterialTheme.typography.subtitle1)
    }
}