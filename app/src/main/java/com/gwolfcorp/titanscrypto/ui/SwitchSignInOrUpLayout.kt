package com.gwolfcorp.titanscrypto.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.MutableLiveData
import com.gwolfcorp.titanscrypto.R
import com.gwolfcorp.titanscrypto.ui.theme.*

private val isSignInOrUp = MutableLiveData(false)

@Composable
fun SwitchSignInOrUpLayout(layoutId: String) {
    val state =  isSignInOrUp.observeAsState()

    val constraints = decoupledConstraints(state)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .layoutId(layoutId),
        constraintSet = constraints
    ) {

        Card(
            modifier = Modifier
                .layoutId("sign_menu_bg")
                .size(366.dp, 46.dp),
            shape = RoundedCornerShape(24),
            backgroundColor = SwitchMenuSignInOrUpColor
        ) {

        }

        Card(
            modifier = Modifier
                .layoutId("sign_menu_choose_bg")
                .size(179.dp, 38.dp),
            shape = RoundedCornerShape(24),
            backgroundColor = MainDarkColor
        ) {

        }

        Box(
            modifier = Modifier
                .layoutId("sign_menu_btn_in")
                .clickable {
                    isSignInOrUp.value = false
                },
            contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.sign_in_text),
                fontSize = 14.sp,
                fontFamily = neueMontrealFamily,
                fontWeight = FontWeight.Normal,
                color = if(state.value == true) GrayTextMenuInactiveColor else GrayTextMenuActiveColor)
        }

        Box(
            modifier = Modifier
                .layoutId("sign_menu_btn_up")
                .clickable {
                    isSignInOrUp.value = true
                },
            contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.sign_up_text),
                fontSize = 14.sp,
                fontFamily = neueMontrealFamily,
                fontWeight = FontWeight.Normal,
                color = if(state.value == false) GrayTextMenuInactiveColor else GrayTextMenuActiveColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSwitchSignInOrUp() {
    SwitchSignInOrUpLayout("switch_sign_in_or_up")
}

@Composable
private fun decoupledConstraints(state: State<Boolean?>): ConstraintSet {
    val state = state

    val bias: Float by animateFloatAsState(
        targetValue = if(state.value == false) 0.04f else 0.96f,
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        )
    )

    return ConstraintSet {
        val signMenuBG = createRefFor("sign_menu_bg")
        val signMenuChooseBG = createRefFor("sign_menu_choose_bg")
        val signMenuBtnIn = createRefFor("sign_menu_btn_in")
        val signMenuBtnUp = createRefFor("sign_menu_btn_up")
        val guidelineStartHalf = createGuidelineFromStart(0.5f)

        constrain(signMenuBG) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(signMenuChooseBG) {
            top.linkTo(signMenuBG.top)
            bottom.linkTo(signMenuBG.bottom)
            linkTo(signMenuBG.start, signMenuBG.end, bias = bias)
        }

        constrain(signMenuBtnIn) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(signMenuBG.top)
            bottom.linkTo(signMenuBG.bottom)
            linkTo(signMenuBG.start, guidelineStartHalf, bias = 0.02f)
        }

        constrain(signMenuBtnUp) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(signMenuBG.top)
            bottom.linkTo(signMenuBG.bottom)
            linkTo(guidelineStartHalf, signMenuBG.end, bias = 0.98f)
        }
    }
}