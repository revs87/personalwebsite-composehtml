package pt.rvcoding.personalwebsitecomposehtml.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.silk.style.CssStyle
import org.jetbrains.compose.web.css.vh

val AppStyle = CssStyle {
    base {
        Modifier.height(100.vh)
    }
}