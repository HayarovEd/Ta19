package olappdengato.rusnewwws.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import olappdengato.rusnewwws.R
import kotlin.random.Random


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CasinoSlot() {
    var spinning by remember {
        mutableStateOf(false)
    }
    var result by remember { mutableStateOf("") }
    val images = listOf(
        R.drawable.cake,
        R.drawable.coin,
        R.drawable.star,
        R.drawable.diamond,
        R.drawable.wild
    )
    var visible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    var index0 by remember { mutableIntStateOf(0) }
    var index1 by remember { mutableIntStateOf(1) }
    var index2 by remember { mutableIntStateOf(2) }

    scope.launch {
        if (spinning) {
            delay(300)
            visible=!visible
            index1 = if (index1<images.size-1) index1+1 else 0
            index2 = if (index2<images.size-1) index2+1 else 0
            index0 = if (index0<images.size-1) index0+1 else 0
        }
    }
    Log.d("test main", "spinning $spinning")
    Log.d("test main", "index1 $index1")
    Log.d("test main", "index2 $index2")
    Log.d("test main", "index0 $index0")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            ),
            exit = slideOutVertically(
                targetOffsetY  = { it },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            )
        ) {
            Image(
                painter = painterResource(images[index0]),
                contentDescription = "Slot Icon",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            ),
            exit = slideOutVertically(
                targetOffsetY  = { it },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            )
        ) {
            Image(
                painter = painterResource(images[index1]),
                contentDescription = "Slot Icon",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            ),
            exit = slideOutVertically(
                targetOffsetY  = { it },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            )
        ) {
            Image(
                painter = painterResource(images[index2]),
                contentDescription = "Slot Icon",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                spinning = !spinning
            },
        ) {
            Text(text = "Spin")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = result,
            color = Color.Green,
            modifier = Modifier.background(Color.LightGray)
        )
    }
}

@Preview
@Composable
fun CasinoSlotPreview() {
    CasinoSlot()
}