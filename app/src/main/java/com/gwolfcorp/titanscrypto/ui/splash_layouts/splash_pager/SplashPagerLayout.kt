package com.gwolfcorp.titanscrypto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.gwolfcorp.titanscrypto.R
import com.gwolfcorp.titanscrypto.ui.theme.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun SplashPagerLayout() {
    val constraints = decoupledConstraints()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MainDarkColor),
        constraintSet = constraints
    ) {
        Image(
            modifier = Modifier.layoutId("splash_pager_triangle"),
            painter = painterResource(id = R.drawable.splash_pager_triangle),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("splash_pager_star"),
            painter = painterResource(id = R.drawable.splash_pager_star),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("splash_pager_left_light"),
            painter = painterResource(id = R.drawable.splash_pager_left_light),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("splash_pager_right_light"),
            painter = painterResource(id = R.drawable.splash_pager_right_light),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("splash_pager_bottom_gradient"),
            painter = painterResource(id = R.drawable.splash_pager_bottom_gradient),
            contentDescription = null,
            contentScale = ContentScale.FillBounds)

        val pagerState = rememberPagerState()

        HorizontalPager(
            modifier = Modifier.layoutId("splash_pager"),
            count = 3,
            state = pagerState) { page ->
            when(page) {
                0 -> {
                    PagerFirstLayout()
                }
                1 -> {
                    PagerSecondLayout()
                }
                2 -> {
                    PagerThirdLayout()
                }
            }
        }

        SplashPointLayout(currentPage = pagerState.targetPage, "splash_pager_point")

        val scope = rememberCoroutineScope()

        Button(
            modifier = Modifier
                .layoutId("splash_pager_btn")
                .padding(top = 40.dp, bottom = 55.dp),
            onClick = {
                if(pagerState.currentPage != 2) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            shape = RoundedCornerShape(24),
            colors = ButtonDefaults.buttonColors(backgroundColor = MainGreenColor),
            content = @Composable{
                Text(
                    text = stringResource(id = R.string.splash_pager_next),
                    fontSize = 18.sp,
                    fontFamily = neueMontrealFamily,
                    fontWeight = FontWeight.Normal,
                    color = MainTextDarkColor,
                    style = MaterialTheme.typography.h4.copy(
                        shadow = Shadow(
                            color = MainTextShadowDarkColor,
                            offset = Offset(-3f, 4f),
                            blurRadius = 8f
                        )
                    ))
            })
    }
}


@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreviewSplashPager() {
    SplashPagerLayout()
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val splashPager = createRefFor("splash_pager")
        val imgSplashPagerTriangle = createRefFor("splash_pager_triangle")
        val imgSplashPagerStar = createRefFor("splash_pager_star")
        val imgSplashPagerLeftLight = createRefFor("splash_pager_left_light")
        val imgSplashPagerRightLight = createRefFor("splash_pager_right_light")
        val imgSplashPagerBottomGradient = createRefFor("splash_pager_bottom_gradient")
        val btnSplashPager = createRefFor("splash_pager_btn")
        val splashPagerPoint = createRefFor("splash_pager_point")
        val leftGuidelineForButton = createGuidelineFromStart(0.3f)
        val rightGuidelineForButton = createGuidelineFromEnd(0.3f)
        val topGuidelineForButton = createGuidelineFromTop(0.785f)
        val topGuidelineForGradient = createGuidelineFromBottom(0.44f)

        constrain(splashPager) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            bottom.linkTo(splashPagerPoint.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(imgSplashPagerTriangle) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(imgSplashPagerStar) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(imgSplashPagerLeftLight) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }

        constrain(imgSplashPagerRightLight) {
            width = Dimension.fillToConstraints
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        }

        constrain(imgSplashPagerBottomGradient) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            linkTo(topGuidelineForGradient, parent.bottom,  bias = 1f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(splashPagerPoint) {
            linkTo(parent.top, topGuidelineForButton, bias = 1f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(btnSplashPager) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(topGuidelineForButton)
            bottom.linkTo(parent.bottom)
            start.linkTo(leftGuidelineForButton)
            end.linkTo(rightGuidelineForButton)
        }
    }
}