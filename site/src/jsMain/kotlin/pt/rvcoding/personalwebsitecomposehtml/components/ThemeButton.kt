package pt.rvcoding.personalwebsitecomposehtml.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.styles.ButtonStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@Composable
fun ThemeButton(
    text: String = "",
    colorMode: ColorMode = ColorMode.LIGHT,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = ButtonStyle
            .toModifier()
            .background(
                if (colorMode.isLight) {
                    if (selected) { Res.Theme.DARK_BLUE.color }
                    else { Res.Theme.BLUE.color }
                } else {
                    if (selected) { Res.Theme.GRADIENT_TWO.color }
                    else { Res.Theme.LIGHT_BLUE.color }
                }
            )
            .margin(all = 12.px),
        size = ButtonSize.LG,
        onClick = { onClick.invoke() }
    ) {
        SpanText(
            modifier = Modifier
                .fillMaxSize()
                .fontSize(14.px)
                .color(
                    if (colorMode.isLight) Colors.White
                    else Res.Theme.GRADIENT_ONE_DARK.color
                )
                .fontWeight(FontWeight.Bold)
                .fontFamily(Res.String.ROBOTO_CONDENSED),
            text = text
        )
    }
}