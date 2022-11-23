package com.example.app03.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.app03.TodoItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

typealias RemoveItemClick = (TodoItem) -> Unit
typealias OnClick = () -> Unit


@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    items: List<TodoItem>,
    addItemClick: OnClick,
    removeItemClick: RemoveItemClick
) {
    //添加该状态是为了让LazyColumn 实现上下滚动的效果

    val rememberLazyListState = rememberLazyListState()
    Column {
        LazyColumn(
            state = rememberLazyListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items) {

                ItemView(todo = it, modifier = modifier) {
                    removeItemClick(it)
                }
            }
        }
        Button(
            onClick = {
                addItemClick()
            },
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Add random Item")
        }
    }
}

@Composable
fun showDialog() {
    Dialog(onDismissRequest = { }) {

    }
}

@Composable
fun ItemView(modifier: Modifier = Modifier, todo: TodoItem, removeItemClick: RemoveItemClick) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .clickable {
                removeItemClick(todo)
            },
        horizontalArrangement = Arrangement.SpaceBetween// 文本内容水平

    ) {
        Text(text = todo.task)
        val tintAlpha: Float = remember(todo.task) { randomTint() }
        Icon(
            imageVector = todo.todo.imageVector,
            contentDescription = stringResource(id = todo.todo.contentDescription),
            tint = LocalContentColor.current.copy(alpha = tintAlpha)
        )
    }
}

fun randomTint(): Float {
    return Random.nextFloat().coerceIn(0.3f, 0.9f)
}