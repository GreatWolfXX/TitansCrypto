package com.gwolfcorp.titanscrypto.ui.splash_layouts.splash_pager.pager_slides

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
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
import com.gwolfcorp.titanscrypto.ui.theme.MainWhiteColor
import com.gwolfcorp.titanscrypto.ui.theme.SecondTextDarkColor
import com.gwolfcorp.titanscrypto.ui.theme.neueMontrealFamily

@Composable
fun PagerLayout(pagerImage: Painter, nameText: String, descText: String) {
    val constraints = decoupledConstraints()

    ConstraintLayout(
        constraintSet = constraints
    ) {

        Image(
            modifier = Modifier.layoutId("pager_img"),
            painter = pagerImage,
            contentDescription = null)

        Image(
            modifier = Modifier
                .layoutId("pager_dark_gradient")
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.pager_dark_gradient_img),
            contentDescription = null,
            contentScale = ContentScale.FillWidth)

        Text(
            modifier = Modifier
                .layoutId("pager_layout_name")
                .padding(top = 24.dp, bottom = 24.dp),
            text = nameText,
            fontSize = 24.sp,
            fontFamily = neueMontrealFamily,
            fontWeight = FontWeight.Normal,
            color = MainWhiteColor
        )

        Text(
            modifier = Modifier
                .layoutId("pager_layout_description")
                .padding(start = 24.dp, end = 24.dp),
            text = descText,
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
fun DefaultPreviewPager() {
    PagerLayout(
        painterResource(id = R.drawable.pager_first_layout_img),
        stringResource(id = R.string.pager_first_layout_name),
        stringResource(id = R.string.pager_first_layout_description))
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val pagerImg = createRefFor(id = "pager_img")
        val pagerDarkGradient = createRefFor(id = "pager_dark_gradient")
        val pagerLayoutName = createRefFor(id = "pager_layout_name")
        val pagerLayoutDesc= createRefFor(id = "pager_layout_description")


        constrain(pagerImg) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(pagerDarkGradient) {
            width = Dimension.preferredWrapContent
            bottom.linkTo(pagerImg.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(pagerLayoutName) {
            linkTo(pagerImg.bottom, pagerLayoutDesc.top, bias = 0.5f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(pagerLayoutDesc) {
            linkTo(pagerLayoutName.bottom, parent.bottom, bias = 0.5f)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

    }
}