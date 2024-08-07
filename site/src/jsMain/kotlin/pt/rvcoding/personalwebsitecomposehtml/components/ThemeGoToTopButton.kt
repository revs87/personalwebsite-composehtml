package pt.rvcoding.personalwebsitecomposehtml.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.styles.ImageMobileStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.ImageStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.ThemeIconMobileStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.ThemeIconStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.ICON_SIZE
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.ICON_SIZE_LG

@Composable
fun ThemeGoToTopButton(
    colorMode: ColorMode,
    visible: Boolean,
    onClick: () -> Unit
) {
    if (visible) {
        val breakpoint = rememberBreakpoint()
        val imageComponentStyle = if (breakpoint > Breakpoint.MD) ThemeIconStyle else ThemeIconMobileStyle
        Column(
            modifier = Modifier
                .fillMaxSize()
                .position(Position.Fixed)
                .zIndex(1)
                .styleModifier {
                    property("pointer-events", "none")
                },
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            IconButton(
                modifier = imageComponentStyle.toModifier()
                    .margin(all = if (breakpoint > Breakpoint.SM) 24.px else 16.px)
                    .styleModifier {
                        property("pointer-events", "auto")
                    },
                colorMode = colorMode,
                icon = if (colorMode.isLight) Res.Icon.GOTOTOP else Res.Icon.GOTOTOP_LIGHT,
                iconSize = if (breakpoint > Breakpoint.SM) ICON_SIZE_LG.px else ICON_SIZE.px,
                onClick = onClick
            )
        }
    }
}