package pt.rvcoding.personalwebsitecomposehtml.presentation.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.dom.Text
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeButton
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeSwitchButton
import pt.rvcoding.personalwebsitecomposehtml.models.Menu
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.*
import pt.rvcoding.personalwebsitecomposehtml.presentation.profile.ProfileCard
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState
    var menuSelected by remember { mutableStateOf(Menu.Default) }
    val breakpoint = rememberBreakpoint()

    LaunchedEffect(colorMode) {
        val savedTheme = localStorage.getItem(Res.String.SAVED_THEME) ?: ColorMode.LIGHT.name
        colorMode = ColorMode.valueOf(savedTheme)
    }

    ThemeSwitchButton(
        colorMode = colorMode,
        onClick = {
            colorMode = colorMode.opposite
            localStorage.setItem(Res.String.SAVED_THEME, colorMode.name)
        }
    )

    Box(
        Modifier
            .fillMaxSize()
            .backgroundImage(
                linearGradient(
                    dir = LinearGradient.Direction.ToRight,
                    from = if (colorMode.isLight) Res.Theme.GRADIENT_ONE.color
                    else Res.Theme.GRADIENT_ONE_DARK.color,
                    to = if (colorMode.isLight) Res.Theme.GRADIENT_TWO.color
                    else Res.Theme.GRADIENT_TWO_DARK.color
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .overflow { y(Overflow.Auto) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (breakpoint > Breakpoint.SM) {
                Row {
                    Menu.entries.forEach {
                        ThemeButton(
                            text = it.title.uppercase(),
                            colorMode = colorMode,
                            selected = it == menuSelected,
                            onClick = {
                                menuSelected = it
                            }
                        )
                    }
                }
            }
            when (menuSelected) {
                PROFILE -> ProfileCard(colorMode = colorMode)
                PORTFOLIO -> Text("Portfolio")
                HISTORY -> Text("History")
            }
        }
    }
}