package com.alerdoci.ultimatepokedex.app.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val BottomCardShape = Shapes(
    large = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
)
val TopCardShape = Shapes(
    large = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
)
val AllCardShape = Shapes(
    large = RoundedCornerShape(
        bottomStart = 40.dp,
        topStart = 40.dp,
        topEnd = 40.dp,
        bottomEnd = 40.dp
    )
)