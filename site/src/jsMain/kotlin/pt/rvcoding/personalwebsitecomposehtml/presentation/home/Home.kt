package pt.rvcoding.personalwebsitecomposehtml.presentation.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Text
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeGoToTopButton
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeMenuHorizontalButtons
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeMenuVerticalButtons
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeModeSwitchButton
import pt.rvcoding.personalwebsitecomposehtml.models.Menu
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.*
import pt.rvcoding.personalwebsitecomposehtml.presentation.profile.ProfileCard
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState
    var menuSelected by remember { mutableStateOf(Menu.Default) }
    var goToTop by remember { mutableStateOf(false) }

    LaunchedEffect(colorMode) {
        val savedTheme = localStorage.getItem(Res.String.SAVED_THEME) ?: ColorMode.LIGHT.name
        colorMode = ColorMode.valueOf(savedTheme)
    }
    LaunchedEffect(Unit) {
        window.addEventListener(
            type = "scroll",
            { goToTop = window.scrollY > 100 }
        )
    }

    ThemeModeSwitchButton(
        colorMode = colorMode,
        onClick = {
            colorMode = colorMode.opposite
            localStorage.setItem(Res.String.SAVED_THEME, colorMode.name)
        }
    )
    ThemeGoToTopButton(
        colorMode = colorMode,
        visible = goToTop,
        onClick = { window.scrollTo(x = 0.0, y = 0.0) }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ThemeMenuVerticalButtons(
            colorMode = colorMode,
            onMenuSelect = { menuSelected = it }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .backgroundImage(
                    linearGradient(
                        dir = LinearGradient.Direction.ToRight,
                        from = if (colorMode.isLight) Res.Theme.GRADIENT_ONE.color else Res.Theme.GRADIENT_ONE_DARK.color,
                        to = if (colorMode.isLight) Res.Theme.GRADIENT_TWO.color else Res.Theme.GRADIENT_TWO_DARK.color
                    )
                )
        ) {
            ThemeMenuHorizontalButtons(
                colorMode = colorMode,
                onMenuSelect = { menuSelected = it }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .overflow { y(Overflow.Auto) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (menuSelected) {
                        PROFILE -> ProfileCard(colorMode = colorMode)
                        PORTFOLIO -> Text("Portfolio")
                        HISTORY -> Text("History")
                        ABOUT_ME -> Text("About me")
                    }
                }
            }
        }
    }
}