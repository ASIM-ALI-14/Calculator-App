
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

@Composable
fun SingleButton(
    text: String,
    TextColor:Color,
    modifier: Modifier,
    background:Color,
    onClick: () -> Unit,
    width: Dp ,
    height: Dp ,
    shadowColor: Color
) {
    val cornerRadius = height / 2 // makes it oval

    Box(
        modifier = Modifier
            .padding(7.dp)
            .width(width)
            .height(height)

            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        color = android.graphics.Color.TRANSPARENT
                        setShadowLayer(
                            20f, // blur radius
                            5f, 7f, // x and y offset
                            shadowColor.copy(alpha = 0.5f).toArgb()
                        )
                    }

                    val left = 0f
                    val top = 0f
                    val right = size.width
                    val bottom = size.height

                    canvas.nativeCanvas.drawRoundRect(
                        left,
                        top,
                        right,
                        bottom,
                        cornerRadius.toPx(),
                        cornerRadius.toPx(),
                        paint
                    )
                }
            }
            .clip(RoundedCornerShape(cornerRadius))
            .background(background, RoundedCornerShape(cornerRadius))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = TextColor, fontSize = 30.sp,
            fontWeight = FontWeight.Normal)
    }
}





