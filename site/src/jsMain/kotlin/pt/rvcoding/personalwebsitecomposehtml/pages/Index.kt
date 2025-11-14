package pt.rvcoding.personalwebsitecomposehtml.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.dom.Div
import pt.rvcoding.personalwebsitecomposehtml.presentation.home.HomePage
import pt.rvcoding.personalwebsitecomposehtml.styles.AppStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.backgroundGradient

@Page
@Composable
fun MainPage() {
    val colorMode = ColorMode.currentState.value

    Div(
        AppStyle
            .toModifier()
            .backgroundImage(colorMode.backgroundGradient())
            .toAttrs()
    ) {
        HomePage()
    }
}
