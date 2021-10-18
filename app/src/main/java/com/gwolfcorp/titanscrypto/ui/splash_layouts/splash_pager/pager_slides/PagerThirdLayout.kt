package com.gwolfcorp.titanscrypto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.gwolfcorp.titanscrypto.R
import com.gwolfcorp.titanscrypto.ui.theme.*

@Composable
fun PagerThirdLayout() {
    val constraints = decoupledConstraints()

    ConstraintLayout(
        constraintSet = constraints
    ) {

        Image(
            modifier = Modifier
                .layoutId("pager_third_img")
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.pager_third_layout_img),
            contentDescription = null)

        Image(
            modifier = Modifier.layoutId("pager_dark_gradient"),
            painter = painterResource(id = R.drawable.pager_dark_gradient_img),
            contentDescription = null,
            contentScale = ContentScale.FillWidth)
        
        Text(
            modifier = Modifier
                .layoutId("pager_third_layout_name")
                .padding(top = 24.dp, bottom = 24.dp),
            text = stringResource(id = R.string.pager_third_layout_name),
            fontSize = 24.sp,
            fontFamily = neueMontrealFamily,
            fontWeight = FontWeight.Normal,
            color = MainWhiteColor
        )
        
        Text(
            modifier = Modifier
                .layoutId("pager_third_layout_description")
                .padding(start = 24.dp, end = 24.dp),
            text = stringResource(id = R.string.pager_third_layout_description),
            fontSize = 18.sp,
            fontFamily = neueMontrealFamily,
            fontWeight = FontWeight.Normal,
            color = SecondTextDarkColor,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewPagerThird() {
    PagerThirdLayout()
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val pager_third_img = createRefFor(id = "pager_third_img")
        val pager_dark_gradient = createRefFor(id = "pager_dark_gradient")
        val pager_third_layout_name = createRefFor(id = "pager_third_layout_name")
        val pager_third_layout_description = createRefFor(id = "pager_third_layout_description")


        constrain(pager_third_img) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(pager_dark_gradient) {
            width = Dimension.fillToConstraints
            bottom.linkTo(pager_third_img.bottom)
            start.linkTo(pager_third_img.start)
            end.linkTo(pager_third_img.end)
        }

        constrain(pager_third_layout_name) {
            linkTo(pager_third_img.bottom, pager_third_layout_description.top, bias = 0.5f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(pager_third_layout_description) {
            linkTo(pager_third_layout_name.bottom, parent.bottom, bias = 0.5f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

    }
}