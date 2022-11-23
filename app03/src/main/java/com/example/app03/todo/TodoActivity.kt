package com.example.app03.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app03.TodoIcon
import com.example.app03.TodoItem
import com.example.app03.ui.theme.ComposeDemoTheme

class TodoViewModel : ViewModel() {
    private var _todoItems = MutableLiveData(listOf<TodoItem>())
    val todoItems = _todoItems//制度

    fun addItem(todo: TodoItem) {
        _todoItems.value = _todoItems.value!! + listOf<TodoItem>(todo)//这里是为了赋一个新的集合
    }

    fun removeItem(item: TodoItem) {
        _todoItems.value = _todoItems.value?.toMutableList().also {
            it?.remove(item)
        }
    }

}

class TodoActivity : ComponentActivity() {
    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    //将数据源转成是有状态的，当数据发生改变是意味着其状态发生改变，状态发生改变则其ui进行更新
                    val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
                    Column {
                        TextInput {
                            todoViewModel.addItem(it)
                        }
                        TodoScreen(modifier = Modifier.fillMaxWidth(), items, addItemClick = {
                            val item = generateRandomItem()
                            todoViewModel.addItem(item)
                        }, removeItemClick = {
                            todoViewModel.removeItem(it)
                        })
                    }

                }
            }
        }

    }
}


fun getData(): MutableList<TodoItem> {
    return mutableListOf(
        TodoItem(TodoIcon.Square, "王侯将相宁有种乎？"),
        TodoItem(TodoIcon.Done, "术业有专攻"),
        TodoItem(TodoIcon.Default, "先天下之忧而又，后天下之乐而乐"),
        TodoItem(TodoIcon.Event, "天下事有难易乎?为之，为之 则难者亦易矣"),
        TodoItem(TodoIcon.Trash, "天将降大任于斯人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行佛乱其所为"),
        TodoItem(TodoIcon.Privacy, "飞流直下三千尺，疑是银河落九天"),
    )
}

fun generateRandomItem(): TodoItem {
    return listOf(
        TodoItem(TodoIcon.Square, "王侯将相宁有种乎？"),
        TodoItem(TodoIcon.Done, "术业有专攻"),
        TodoItem(TodoIcon.Default, "先天下之忧而又，后天下之乐而乐"),
        TodoItem(TodoIcon.Event, "天下事有难易乎?为之，为之 则难者亦易矣"),
        TodoItem(TodoIcon.Trash, "天将降大任于斯人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行佛乱其所为"),
        TodoItem(TodoIcon.Privacy, "飞流直下三千尺，疑是银河落九天"),
    ).random()
}