package pt.rvcoding.personalwebsitecomposehtml.presentation.portfolio

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.domain.ImageConfig
import pt.rvcoding.personalwebsitecomposehtml.domain.ImageRowConfig
import pt.rvcoding.personalwebsitecomposehtml.models.content.ContentData
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.ContentAlignment
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.ImageSide
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.TextSide
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.VideoSideWithRowOfImages
import pt.rvcoding.personalwebsitecomposehtml.util.Res
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Image.PORTFOLIO_APP_CVNOTES_LINK_LIST
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Image.PORTFOLIO_APP_CVNOTES_LOGO_LIST
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Image.PORTFOLIO_APP_CVNOTES_VIDEO


@Composable
fun PortfolioCVNotesCard(
    colorMode: ColorMode = ColorMode.LIGHT,
    expanded: Boolean = false,
    onExpand: (Boolean) -> Unit = {},
    gridLinesExpandedHeightList: List<Int> = listOf(
//        Res.Dimens.MAX_CARD_HEIGHT,
//        Res.Dimens.MAX_CARD_HEIGHT,
        Res.Dimens.MAX_CARD_HEIGHT_EXTENDED,
//        Res.Dimens.MAX_CARD_HEIGHT_EXTENDED,
//        Res.Dimens.MAX_CARD_HEIGHT,
    )
) {
    val breakpoint = rememberBreakpoint()
    val cardHeight by animateDpAsState(
        targetValue =
            if (expanded) (gridLinesExpandedHeightList.sum()).dp
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
            .onClick { onExpand.invoke(!expanded) }
    ) {
        TextSide(
            colorMode = colorMode,
            breakpoint = breakpoint,
            headerAlignment = ContentAlignment.Right,
            contentAlignment = ContentAlignment.Right,
            expanded = expanded,
            useSeparator = true,
            title = ContentData.PortfolioCVNotes.main.title,
            subTitle = ContentData.PortfolioCVNotes.main.subTitle,
            subSubTitle = ContentData.PortfolioCVNotes.main.subSubTitle,
            description = ContentData.PortfolioCVNotes.content,
            extra = {}
        )
        if (expanded) {
            VideoSideWithRowOfImages(
                breakpoint = breakpoint,
                expanded = expanded,
                extendedHeight = true,
                imageConfig = ImageConfig(
                    imageSrc = PORTFOLIO_APP_CVNOTES_VIDEO,
                    croppedOnExpanded = false,
                    shadowed = false,
                    sidePadding = 0.px,
                    videoWidth = if (breakpoint > Breakpoint.MD) 590.px else 360.px,
                    videoHeight = if (breakpoint > Breakpoint.MD) 1150.px else 720.px
                ),
                imageRowConfig = ImageRowConfig(
                    imageSrc = PORTFOLIO_APP_CVNOTES_LOGO_LIST,
                    imageLink = PORTFOLIO_APP_CVNOTES_LINK_LIST
                )
            )
        } else {
            ImageSide(
                breakpoint = breakpoint,
                expanded = expanded,
                imageSrc = Res.Image.PORTFOLIO_APP_CVNOTES_LOGO
            )
        }
    }
}