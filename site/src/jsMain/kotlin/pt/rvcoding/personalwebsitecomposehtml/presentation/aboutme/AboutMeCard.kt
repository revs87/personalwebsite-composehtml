package pt.rvcoding.personalwebsitecomposehtml.presentation.aboutme

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.models.content.ContentData
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.ContentAlignment
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.TextSide
import pt.rvcoding.personalwebsitecomposehtml.util.Res


@OptIn(DelicateApi::class)
@Composable
fun AboutMeCard(
    colorMode: ColorMode = ColorMode.LIGHT,
    imageSrc: String = Res.Image.ABOUTME_PHOTO
) {
    val breakpoint = rememberBreakpoint()
    var expanded by remember { mutableStateOf(true) }
    val cardHeight by animateDpAsState(
        targetValue =
            if (expanded) Res.Dimens.MAX_CARD_HEIGHT.dp
            else Res.Dimens.MAX_CARD_HEIGHT_COLLAPSED.dp
    )

    SimpleGrid(
        numColumns = numColumns(base = 1, md = 2),
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint <= Breakpoint.MD) 100.percent
                else Res.Dimens.MAX_CARD_WIDTH.px
            )
            .thenIf(
                condition = breakpoint > Breakpoint.MD,
                other = Modifier.height(cardHeight.value.px)
            )
            .boxShadow(
                color = Colors.Black.copy(alpha = 10),
                blurRadius = if (breakpoint <= Breakpoint.MD) 18.px else 24.px,
                spreadRadius = if (breakpoint <= Breakpoint.MD) 12.px else 18.px
            )
            .padding(all = 12.px)
            .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
            .margin(
                top = if (breakpoint <= Breakpoint.MD) 12.px else 24.px,
                bottom = if (breakpoint <= Breakpoint.MD) 12.px else 24.px,
            )
            .background(
                if (colorMode.isLight) Colors.White else Res.Theme.DARK_BLUE.color
            )
            .onClick { expanded = !expanded }
    ) {
//        ImageSide(
//            breakpoint = breakpoint,
//            expanded = expanded,
//            shadowed = false,
//            croppedOnCollapsed = true,
//            croppedOnExpanded = false,
//            imageSrc = imageSrc
//        )
        TextSide(
            colorMode = colorMode,
            breakpoint = breakpoint,
            headerAlignment = ContentAlignment.Left,
            expanded = expanded,
            useSeparator = false,
            title = ContentData.AboutMe.main.title,
            subTitle = "",
            description = ContentData.AboutMe.main.description,
            extra = { }
        )
        TextSide(
            colorMode = colorMode,
            breakpoint = breakpoint,
            headerAlignment = ContentAlignment.Left,
            expanded = expanded,
            useSeparator = false,
            title = "",
            subTitle = "",
            description = ContentData.AboutMe.main.description2,
            extra = { }
        )
    }
}