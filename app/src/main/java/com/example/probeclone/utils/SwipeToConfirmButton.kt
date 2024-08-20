package com.example.probeclone.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probeclone.BidType
import com.example.probeclone.ui.theme.UiColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeToConfirmButton(
    modifier: Modifier = Modifier,
    selectedBid: BidType,
    onConfirm: () -> Unit = {}
) {
    val width = 400.dp
    val dragSize = 50.dp
    var progress by remember { mutableFloatStateOf(0f) }
    val sizePx = with(LocalDensity.current) { (width - dragSize).toPx() }
    var isSwiped by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .width(width)
            .background(
                color = if(progress<1f) {
                    if (selectedBid == BidType.YES) UiColors.Blue else UiColors.Red
                } else{
                    Color(0xff4BB543)
                },
                RoundedCornerShape(dragSize)
            )
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        Log.e("Progress",progress.toString())

                        coroutineScope.launch {
                            if (progress < 0.6f) {
                                progress = 0f
                            } else {
                                isSwiped = true
                                progress = 1f

                                //Delay before navigating
                                delay(1200)
                                onConfirm()
                            }
                        }
                    },
                    onHorizontalDrag =  { change, dragAmount ->
                        change.consume()
                        val newOffset = (progress * sizePx + dragAmount).coerceIn(0f, sizePx)
                        progress = newOffset / sizePx
                        Log.e("Progress",progress.toString())
                    }
                )
            }
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .alpha(if (isSwiped) 1f else 1f - progress) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isSwiped) {
                Text(
                    text = "Swipe for ${if (selectedBid == BidType.YES) "Yes" else "No"}",
                    color = Color.White,
                    fontSize = 12.sp
                )
            } else {
                Text(
                    text = "Order Confirmed",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }

        if (!isSwiped) {
            DraggableControl(
                modifier = Modifier
                    .offset { IntOffset((progress * sizePx).roundToInt(), 0) }
                    .size(dragSize),
                progress = progress,
                selectedBid = selectedBid
            )
        }
    }
}

@Composable
private fun DraggableControl(
    modifier: Modifier,
    selectedBid: BidType,
    progress: Float
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 2.dp,
                shape = CircleShape,
                clip = false
            )
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val isConfirmed by remember{ derivedStateOf { progress >= 1f } }

        if(!isConfirmed){
            Icon(
                imageVector = Icons.Filled.KeyboardDoubleArrowRight,
                contentDescription = "Confirm Icon",
                tint = if(selectedBid == BidType.YES) UiColors.Blue else UiColors.Red
            )
        }
    }
}
