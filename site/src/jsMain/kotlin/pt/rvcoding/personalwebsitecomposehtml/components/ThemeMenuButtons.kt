package pt.rvcoding.personalwebsitecomposehtml.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.models.Menu
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.ABOUT_ME
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.HISTORY
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.PORTFOLIO
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.PROFILE
import pt.rvcoding.personalwebsitecomposehtml.styles.ThemeIconMobileStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.ThemeIconStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.ICON_SIZE
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.ICON_SIZE_LG

@Composable
fun ThemeMenuVerticalButtons(
    colorMode: ColorMode,
    onMenuSelect: (Menu) -> Unit,
) {
    val breakpoint = rememberBreakpoint()
    if (breakpoint > Breakpoint.SM) {
        Column(
            modifier = Modifier
                .position(Position.Fixed)
                .zIndex(1),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Menu.Companion.Active.forEach { iconButton(breakpoint, colorMode, it, onMenuSelect) }
        }
    }
}

@Composable
fun ThemeMenuHorizontalButtons(
    colorMode: ColorMode,
    onMenuSelect: (Menu) -> Unit,
) {
    val breakpoint = rememberBreakpoint()
    if (breakpoint <= Breakpoint.SM) {
        Row {
            Menu.Companion.Active.forEach { iconButton(breakpoint, colorMode, it, onMenuSelect) }
        }
    }
}

@Composable
private fun iconButton(
    breakpoint: Breakpoint,
    colorMode: ColorMode,
    it: Menu,
    onMenuSelect: (Menu) -> Unit
) {
    val imageComponentStyle = if (breakpoint > Breakpoint.MD) ThemeIconStyle else ThemeIconMobileStyle
    IconButton(
        modifier = imageComponentStyle.toModifier()
            .margin(
                top = if (breakpoint > Breakpoint.SM) 24.px else 16.px,
                bottom = if (breakpoint > Breakpoint.SM) 0.px else 16.px,
                left = if (breakpoint > Breakpoint.SM) 24.px else 16.px,
                right = if (breakpoint > Breakpoint.SM) 24.px else 16.px,
            )
            .styleModifier {
                property("pointer-events", "auto")
            },
        colorMode = colorMode,
        icon = menuIcon(colorMode, it),
        iconSize = if (breakpoint > Breakpoint.SM) ICON_SIZE_LG.px else ICON_SIZE.px,
        onClick = {
            onMenuSelect.invoke(it)
        }
    )
}

private fun menuIcon(colorMode: ColorMode, menu: Menu): String =
    when (menu) {
        PROFILE -> if (colorMode.isLight) Res.Icon.HOME else Res.Icon.HOME_LIGHT
        PORTFOLIO -> if (colorMode.isLight) Res.Icon.PORTFOLIO else Res.Icon.PORTFOLIO_LIGHT
        HISTORY -> if (colorMode.isLight) Res.Icon.HISTORY else Res.Icon.HISTORY_LIGHT
        ABOUT_ME -> if (colorMode.isLight) Res.Icon.ABOUTME else Res.Icon.ABOUTME_LIGHT
    }
