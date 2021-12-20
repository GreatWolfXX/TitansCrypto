package com.gwolfcorp.titanscrypto.ui.splash_layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gwolfcorp.titanscrypto.R

@Composable
fun SplashPointLayout(currentPage: Int, layoutId: String) {
    Row(
        modifier = Modifier.layoutId(layoutId)
    ) {
        Image(
            modifier = Modifier.padding(end = 8.dp),
            painter = if(currentPage == 0) painterResource(id = R.drawable.splash_pager_point_active) else painterResource(id = R.drawable.splash_pager_point_inactive),
            contentDescription = null)
        Image(
            modifier = Modifier.padding(end = 8.dp),
            painter =  if(currentPage == 1) painterResource(id = R.drawable.splash_pager_point_active) else painterResource(id = R.drawable.splash_pager_point_inactive),
            contentDescription = null)
        Image(
            painter =  if(currentPage == 2) painterResource(id = R.drawable.splash_pager_point_active) else painterResource(id = R.drawable.splash_pager_point_inactive),
            contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSplashPoint() {
    SplashPointLayout(0, "")
}