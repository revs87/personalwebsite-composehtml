package pt.rvcoding.personalwebsitecomposehtml.styles

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val ImageStyle = CssStyle {
    base {
        Modifier
            .transition(Transition.of(property = TransitionProperty.All, duration = 300.ms))
    }
    hover {
        Modifier
            .padding(all = 4.px)
    }
    active {
        Modifier
            .padding(all = 8.px)
    }
}
val ImageMobileStyle = CssStyle {
    base {
        Modifier
            .transition(Transition.of(property = TransitionProperty.All, duration = 300.ms))
    }
    active {
        Modifier
            .padding(all = 8.px)
    }
}