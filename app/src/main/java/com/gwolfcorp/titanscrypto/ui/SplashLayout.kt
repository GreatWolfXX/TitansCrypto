package com.gwolfcorp.titanscrypto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.gwolfcorp.titanscrypto.R
import com.gwolfcorp.titanscrypto.ui.theme.MainDarkColor
import com.gwolfcorp.titanscrypto.ui.theme.MainGreenColor
import com.gwolfcorp.titanscrypto.ui.theme.poppinsFamily

@Composable
fun SplashLayout() {
    val constraints = decoupledConstraints()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MainDarkColor),
        constraintSet = constraints
    ) {

        Image(
            modifier = Modifier.layoutId("splash_icon"),
            painter = painterResource(id = R.drawable.splash_icon),
            contentDescription = null)
        
        Text(
            modifier = Modifier.layoutId("splash_name"),
            text = stringResource(id = R.string.app_name),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = MainGreenColor
        )

        Image(
            modifier = Modifier.layoutId("splash_triangle"),
            painter = painterResource(id = R.drawable.splash_triangle),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("splash_star"),
            painter = painterResource(id = R.drawable.splash_star),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("splash_light"),
            painter = painterResource(id = R.drawable.splash_light),
            contentDescription = null)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewSplash() {
    SplashLayout()
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val imgSplashIcon = createRefFor("splash_icon")
        val txtSplashName = createRefFor("splash_name")
        val imgSplashTriangle = createRefFor("splash_triangle")
        val imgSplashStar = createRefFor("splash_star")
        val imgSplashLight = createRefFor("splash_light")

        constrain(imgSplashIcon) {
            width = Dimension.fillToConstraints
            linkTo(parent.top, parent.bottom, bias = 0.4f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(txtSplashName) {
            linkTo(imgSplashIcon.top, parent.bottom, bias = 0.2f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(imgSplashTriangle) {
            width = Dimension.fillToConstraints
            linkTo(parent.top, parent.bottom, bias = 0.4f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(imgSplashStar) {
            width = Dimension.fillToConstraints
            linkTo(parent.top, parent.bottom, bias = 0.1f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(imgSplashLight) {
            width = Dimension.fillToConstraints
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}