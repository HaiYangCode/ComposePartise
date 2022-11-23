package com.example.app03.todo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.app03.TodoIcon
import com.example.app03.TodoItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextInput(onItemComplete: (TodoItem) -> Unit) {

    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }

    val iconIsVisible = text.isNotBlank()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .background(color = Color.Transparent)

        ) {
            TextField(
                value = text,
                onValueChange = setText,
                maxLines = 1,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {

                    keyboardController?.hide()
                    onItemComplete(TodoItem(task = text, todo = icon))
                    setText("")
                })
            )
            AddButton(
                onClick = {
                    onItemComplete(TodoItem(task = text, todo = icon))
                    setText("")
                },
                text = "Add",
                enabled = text.isNotBlank(),
            )
        }
        if (iconIsVisible) {
            AnimatedIconRow(
                icon,
                setIcon,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(end = 8.dp),
                isVisible = iconIsVisible

            )
        }
    }
}

@Composable
fun AddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    text: String
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = text)
    }
}

@Composable
fun AnimatedIconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true
) {
    val enter = remember {
        fadeIn(animationSpec = TweenSpec(300, easing = FastOutLinearInEasing))
    }
    val exit = remember {
        fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing))
    }

    Box(modifier = Modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(visible = isVisible, enter = enter, exit = exit) {
            IconRow(modifier = modifier, icon = icon, onIconChange = onIconChange)
        }
    }
}

@Composable
fun IconRow(modifier: Modifier = Modifier, icon: TodoIcon, onIconChange: (TodoIcon) -> Unit) {
    Row(modifier = modifier) {
        TodoIcon.values().forEach {
            SelectableIconButton(
                icon = it.imageVector,
                iconContentDescription = it.contentDescription,
                onIconSelected = { onIconChange(it) },
                isSelected = it == icon
            )
        }
    }


}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    iconContentDescription: Int,
    onIconSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {

    val tint = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    }
    TextButton(onClick = { onIconSelected() }, shape = CircleShape, modifier = modifier) {
        Column {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = iconContentDescription),
                tint = tint
            )
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(color = tint)
                ) {

                }
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }


    }
}
