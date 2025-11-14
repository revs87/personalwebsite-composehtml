package pt.rvcoding.personalwebsitecomposehtml.styles

import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import pt.rvcoding.personalwebsitecomposehtml.util.Res

fun ColorMode.backgroundGradient() = linearGradient(
    dir = LinearGradient.Direction.ToRight,
    from = if (isLight) Res.Theme.GRADIENT_ONE.color else Res.Theme.GRADIENT_ONE_DARK.color,
    to = if (isLight) Res.Theme.GRADIENT_TWO.color else Res.Theme.GRADIENT_TWO_DARK.color
)