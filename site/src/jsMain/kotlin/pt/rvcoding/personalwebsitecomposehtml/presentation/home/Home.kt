package pt.rvcoding.personalwebsitecomposehtml.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onContextMenu
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeGoToTopButton
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeMenuHorizontalButtons
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeMenuVerticalButtons
import pt.rvcoding.personalwebsitecomposehtml.components.ThemeModeSwitchButton
import pt.rvcoding.personalwebsitecomposehtml.models.Menu
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.ABOUT_ME
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.HISTORY
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.PORTFOLIO
import pt.rvcoding.personalwebsitecomposehtml.models.Menu.PROFILE
import pt.rvcoding.personalwebsitecomposehtml.presentation.aboutme.AboutMeCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.history.HistoryFCUPCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.history.HistoryINESCCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.history.HistoryITSectorCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.history.HistorySensormaticCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.history.HistoryTheFloowCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.portfolio.PortfolioCVNotesCard
import pt.rvcoding.personalwebsitecomposehtml.presentation.profile.ProfileCard
import pt.rvcoding.personalwebsitecomposehtml.styles.backgroundGradient
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@OptIn(DelicateApi::class)
@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState
    var menuSelected by remember { mutableStateOf(Menu.Default) }
    var goToTop by remember { mutableStateOf(false) }
    val breakpoint = rememberBreakpoint()

    /**
     * It's a nag not being able to put this in a mutableMap:
     *     Its composition is skipped upon click...
     * */
    var expandedPortfolioCVNotes by remember { mutableStateOf(false) }
    var expandedHistorySensormatic by remember { mutableStateOf(false) }
    var expandedHistoryTheFloow by remember { mutableStateOf(false) }
    var expandedHistoryITsector by remember { mutableStateOf(false) }
    var expandedHistoryINESC by remember { mutableStateOf(false) }
    var expandedHistoryFCUP by remember { mutableStateOf(false) }

    fun collapseExpandables() {
        expandedPortfolioCVNotes = false
        expandedHistorySensormatic = false
        expandedHistoryTheFloow = false
        expandedHistoryITsector = false
        expandedHistoryINESC = false
        expandedHistoryFCUP = false
    }

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
        onClick = {
            collapseExpandables()
            window.scrollTo(x = 0.0, y = 0.0)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onContextMenu {
                it.preventDefault()
            }
    ) {
        ThemeMenuVerticalButtons(
            colorMode = colorMode,
            onMenuSelect = {
                collapseExpandables()
                menuSelected = it
            }
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ThemeMenuHorizontalButtons(
                colorMode = colorMode,
                onMenuSelect = { menuSelected = it }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment =
                    if (breakpoint <= Breakpoint.MD) { Alignment.TopCenter }
                    else when (menuSelected) {
                        HISTORY, PORTFOLIO -> Alignment.TopCenter
                        else -> Alignment.Center
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .backgroundImage(colorMode.backgroundGradient())
                        .overflow { y(Overflow.Auto) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val scope = rememberCoroutineScope()
                    when (menuSelected) {
                        PROFILE -> ProfileCard(colorMode = colorMode)
                        PORTFOLIO -> {
                            Div(
                                attrs = { id("PortfolioCVNotesCard") }
                            ) {
                                PortfolioCVNotesCard(
                                    colorMode = colorMode,
                                    expanded = expandedPortfolioCVNotes,
                                    onExpand = {
                                        collapseExpandables()
                                        expandedPortfolioCVNotes = it

                                        scope.launch { scrollTo("PortfolioCVNotesCard") }
                                    }
                                )
                            }
                        }
                        HISTORY -> {
                            Div(
                                attrs = { id("HistorySensormaticCard") }
                            ) {
                                HistorySensormaticCard(
                                    colorMode = colorMode,
                                    expanded = expandedHistorySensormatic,
                                    onExpand = {
                                        collapseExpandables()
                                        expandedHistorySensormatic = it

                                        scope.launch { scrollTo("HistorySensormaticCard") }
                                    }
                                )
                            }
                            Div(
                                attrs = { id("HistoryTheFloowCard") }
                            ) {
                                HistoryTheFloowCard(
                                    colorMode = colorMode,
                                    expanded = expandedHistoryTheFloow,
                                    onExpand = {
                                        collapseExpandables()
                                        expandedHistoryTheFloow = it

                                        scope.launch { scrollTo("HistoryTheFloowCard") }
                                    }
                                )
                            }
                            Div(
                                attrs = { id("HistoryITSectorCard") }
                            ) {
                                HistoryITSectorCard(
                                    colorMode = colorMode,
                                    expanded = expandedHistoryITsector,
                                    onExpand = {
                                        collapseExpandables()
                                        expandedHistoryITsector = it

                                        scope.launch { scrollTo("HistoryITSectorCard") }
                                    }
                                )
                            }
                            Div(
                                attrs = { id("HistoryINESCCard") }
                            ) {
                                HistoryINESCCard(
                                    colorMode = colorMode,
                                    expanded = expandedHistoryINESC,
                                    onExpand = {
                                        collapseExpandables()
                                        expandedHistoryINESC = it

                                        scope.launch { scrollTo("HistoryINESCCard") }
                                    }
                                )
                            }
                            Div(
                                attrs = { id("HistoryFCUPCard") }
                            ) {
                                HistoryFCUPCard(
                                    colorMode = colorMode,
                                    expanded = expandedHistoryFCUP,
                                    onExpand = {
                                        collapseExpandables()
                                        expandedHistoryFCUP = it

                                        scope.launch { scrollTo("HistoryFCUPCard") }
                                    }
                                )
                            }
                        }
                        ABOUT_ME -> {
                            AboutMeCard(colorMode = colorMode)
                        }
                    }
                }
            }
        }
    }
}

private suspend fun scrollTo(divId: String) {
    delay(200)
    val itemElement = document.getElementById(divId) as? HTMLElement
    itemElement?.scrollIntoView()
}