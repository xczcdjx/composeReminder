package com.compose.androidremind.components.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.androidremind.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageTest() {
   Column {
       Column(modifier = Modifier.size(200.dp, 150.dp)) {
           Image(
               painter = painterResource(R.drawable.code),
               modifier = Modifier.size(250.dp,200.dp),
               contentDescription = null,
               // contentScale 设置图片的伸展方式：ContentScale.Inside、ContentScale.Crop 等
               contentScale = ContentScale.Inside,
               // colorFilter 设置颜色滤镜
               colorFilter = ColorFilter.tint(Color.Yellow, blendMode = BlendMode.Color)
           )
       }
       Column(modifier = Modifier.size(200.dp, 150.dp)) {
           Image(
               painter = painterResource(R.drawable.code),
//            modifier = Modifier.size(250.dp,200.dp),
               // 如果指定的大小不满足父布局的约束，则尺寸将会无效。如果强制设置请使用而不考虑父控件约束，可以使用 requiredSize
               modifier = Modifier.requiredSize(300.dp,250.dp),
               contentDescription = null,
               // contentScale 设置图片的伸展方式：ContentScale.Inside、ContentScale.Crop 等
               contentScale = ContentScale.Inside,
               // colorFilter 设置颜色滤镜
               colorFilter = ColorFilter.tint(Color.Yellow, blendMode = BlendMode.Color)
           )
       }
   }
}