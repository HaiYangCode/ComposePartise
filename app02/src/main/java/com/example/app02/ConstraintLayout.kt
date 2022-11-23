package com.example.app02

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.rememberImagePainter

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        val (button0, button1, text) = createRefs()
        Button(onClick = { }, modifier = Modifier.constrainAs(button0) {
            top.linkTo(parent.top, margin = 8.dp)
            absoluteLeft.linkTo(parent.absoluteLeft)
            absoluteRight.linkTo(button1.absoluteLeft)


        }) {
            Text(text = "Button0")
        }

        Button(onClick = { }, modifier = Modifier.constrainAs(button1) {
            top.linkTo(button0.top)
            absoluteLeft.linkTo(text.absoluteRight)


        }) {
            Text(text = "Button1")
        }
        Text(text = "Text", modifier = Modifier.constrainAs(text) {
            top.linkTo(button1.bottom)
//            absoluteLeft.linkTo(button.absoluteLeft)
//            absoluteRight.linkTo(button.absoluteRight)
            absoluteLeft.linkTo(button0.absoluteRight)
            absoluteRight.linkTo(button0.absoluteRight)
        })


    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        val (button0, button1, text) = createRefs()
        Button(onClick = { }, modifier = Modifier.constrainAs(button0) {
            top.linkTo(parent.top, margin = 8.dp)
            absoluteLeft.linkTo(parent.absoluteLeft)
            absoluteRight.linkTo(button1.absoluteLeft)


        }) {
            Text(text = "Button0")
        }


        Text(text = "Text", modifier = Modifier.constrainAs(text) {
            top.linkTo(button1.bottom)
//            absoluteLeft.linkTo(button.absoluteLeft)
//            absoluteRight.linkTo(button.absoluteRight)
            absoluteLeft.linkTo(button0.absoluteRight)
            absoluteRight.linkTo(button0.absoluteRight)
        })

        //TODO 什么是屏障？
        val createEndBarrier = createEndBarrier(button0, text)

        Button(onClick = { }, modifier = Modifier.constrainAs(button1) {
            top.linkTo(button0.top)
            start.linkTo(createEndBarrier)


        }) {
            Text(text = "Button1")
        }

    }
}


@Composable
fun LargeLayoutContent() {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        val text = createRef()
        val guideline = createGuidelineFromStart(0.5F)
        Text(
            text = "long long long long long long long long long long long long long long long long long long long long long long long long long long long text",
            modifier = Modifier.constrainAs(text) {
                start.linkTo(guideline)
            })


    }
}

@Composable
fun DecoupleContent() {
    val margin = 8.dp
    ConstraintLayout() {
        val (button, text) = createRefs()
        Button(onClick = { }, modifier = Modifier.constrainAs(button) {
            top.linkTo(parent.top, margin = margin)
            absoluteLeft.linkTo(parent.absoluteLeft)


        }) {
            Text(text = "Button0")
        }


        Text(text = "Text", modifier = Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = margin)
//            absoluteLeft.linkTo(button.absoluteLeft)
//            absoluteRight.linkTo(button.absoluteRight)
            absoluteLeft.linkTo(button.absoluteRight)
            absoluteRight.linkTo(button.absoluteRight)
        })

    }
}

@Composable
fun DecoupleContent2() {
    BoxWithConstraints {
        val constraintSet = if (maxWidth < maxHeight) {
            decoupleConstraints(16.dp)
        } else {
            decoupleConstraints(360.dp)
        }
        ConstraintLayout(constraintSet) {
            Button(onClick = { }, modifier = Modifier.layoutId("button")) {
                Text(text = "Button0")
            }


            Text(text = "Text", modifier = Modifier.layoutId("text"))
        }
    }
}

private fun decoupleConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
}