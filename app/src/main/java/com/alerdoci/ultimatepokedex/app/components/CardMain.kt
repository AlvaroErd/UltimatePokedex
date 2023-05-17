package com.alerdoci.ultimatepokedex.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alerdoci.ultimatepokedex.app.theme.ColorBlue
import com.alerdoci.ultimatepokedex.app.theme.spacing

@Composable
fun CardMain(
    modifier: Modifier = Modifier,
    imgModifier: Modifier = Modifier,
    text: String,
    img: Int,
    textStyle: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    ),
    backgroundColor: Color = ColorBlue,
    shape: Shape = RoundedCornerShape(16.dp),
    imageSize: Dp = 80.dp,
    imageAlpha: Float = 0.2f,
    colorImgTint: ColorFilter,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor.copy(alpha = 0.9f))
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = text,
                style = textStyle,
                color = Color.White
            )
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = imgModifier
                    .size(imageSize)
                    .alpha(imageAlpha),
                contentScale = ContentScale.Crop,
                colorFilter = colorImgTint
            )
        }
    }
}