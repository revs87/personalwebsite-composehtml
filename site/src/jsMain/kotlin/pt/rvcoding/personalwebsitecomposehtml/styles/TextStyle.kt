package pt.rvcoding.personalwebsitecomposehtml.styles

import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.userSelect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.util.Res


val MyTextStyle = CssStyle {
    base {
        Modifier
            .fontFamily(Res.String.ROBOTO_REGULAR)
            .fontSize(14.px)
            .color(if (colorMode.isLight) Colors.Black else Colors.White)
            .opacity(60.percent)
            .lineHeight(2)
            .styleModifier {
                userSelect(UserSelect.None)
            }
    }
}

val MyLinkStyle = CssStyle {
    base {
        Modifier
            .fontFamily(Res.String.ROBOTO_REGULAR)
            .fontSize(14.px)
            .opacity(60.percent)
            .lineHeight(2)
            .styleModifier {
                userSelect(UserSelect.None)
            }
            .color(if (colorMode.isLight) Colors.Black else Colors.White)
            .fontWeight(FontWeight.Bold)
            .cursor(Cursor.Pointer)
            .transition(
                Transition.of(property = "color", duration = 300.ms)
            )
    }
    hover {
        Modifier
            .opacity(80.percent)
            .color(
                if (colorMode.isLight) Res.Theme.BLACK.color
                else Res.Theme.WHITE.color
            )
    }
}