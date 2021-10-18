package com.gwolfcorp.titanscrypto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.gwolfcorp.titanscrypto.R
import com.gwolfcorp.titanscrypto.ui.theme.MainDarkColor

@Composable
fun SignInOrUpLayout() {
    val constraints = decoupledConstraints()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MainDarkColor),
        constraintSet = constraints
    ) {
        Image(
            modifier = Modifier
                .layoutId("sign_in_or_up_bg")
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.sign_in_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds)

        SwitchSignInOrUpLayout("switch_sign_in_or_up")

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSignIn() {
    SignInOrUpLayout()
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val signInOrUpBG = createRefFor("sign_in_or_up_bg")
//        val signInOrUpClose = createRefFor("signInBG")
        val signInOrUpSwitchMenu = createRefFor("switch_sign_in_or_up")
        val guidelineStartForSwitchMenu = createGuidelineFromStart(0.04f)
        val guidelineEndForSwitchMenu = createGuidelineFromEnd(0.04f)
        val guidelineTopForSwitchMenu = createGuidelineFromTop(0.1f)


        constrain(signInOrUpBG) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(signInOrUpSwitchMenu) {
            width = Dimension.fillToConstraints
            top.linkTo(guidelineTopForSwitchMenu)
            start.linkTo(guidelineStartForSwitchMenu)
            end.linkTo(guidelineEndForSwitchMenu)
        }
    }
}