package pt.rvcoding.personalwebsitecomposehtml.presentation.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.userSelect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.maxSize
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Video
import pt.rvcoding.personalwebsitecomposehtml.domain.ImageConfig
import pt.rvcoding.personalwebsitecomposehtml.domain.ImageRowConfig
import pt.rvcoding.personalwebsitecomposehtml.styles.ImageMobileStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.ImageStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.MAX_CARD_HEIGHT
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.MAX_CARD_HEIGHT_EXTENDED

@Composable
fun VideoSideWithRowOfImages(
    breakpoint: Breakpoint = Breakpoint.XL,
    expanded: Boolean = true,
    extendedHeight: Boolean = false,
    imageRowConfig: ImageRowConfig = ImageRowConfig.Default,
    imageConfig: ImageConfig = ImageConfig.Default
) {
    val cropped1 = if (expanded) imageRowConfig.croppedOnExpanded else imageRowConfig.croppedOnCollapsed
    val cropped2 = if (expanded) imageConfig.croppedOnExpanded else imageConfig.croppedOnCollapsed
    val imageComponentStyle = if (breakpoint > Breakpoint.MD) ImageStyle else ImageMobileStyle
    val expandedHeight = if (extendedHeight) MAX_CARD_HEIGHT_EXTENDED else MAX_CARD_HEIGHT


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                when {
                    breakpoint > Breakpoint.MD -> {
                        Modifier
                            .padding(bottom = 24.px)
                            .height(size = (
                                    if (expanded) expandedHeight
                                    else Res.Dimens.MAX_CARD_HEIGHT_COLLAPSED
                                    ).px
                            )
                    }
                    !expanded -> {
                        Modifier
                            .height(size = Res.Dimens.MAX_CARD_HEIGHT_COLLAPSED_MOBILE.px)
                    }
                    else -> Modifier
                }
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = imageComponentStyle
                    .toModifier()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .then(
                            if (cropped2) { Modifier.fillMaxSize() }
                            else { Modifier.fillMaxWidth() }
                        )
                        .padding(leftRight = imageConfig.sidePadding)
                        .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
                        .objectFit(if (cropped2) ObjectFit.Cover else ObjectFit.Contain)
                        .thenIf(
                            condition = imageConfig.shadowed,
                            other = Modifier
                                .boxShadow(
                                    color = Colors.Black.copy(alpha = 70),
                                    blurRadius = Res.Dimens.BORDER_RADIUS.px,
                                    spreadRadius = 3.px,
                                    offsetX = 2.px,
                                    offsetY = 1.px
                                )
                        )
                        .classNames(
                            "no-drag",
                            "no-select"
                        )
                        .styleModifier {
                            userSelect(UserSelect.None)
                        }
                ) {
                    Video(attrs = {
                        attr("width", imageConfig.videoWidth.toString())
                        attr("height", imageConfig.videoHeight.toString())
                        //attr("controls", "true")
                        attr("autoplay", "true")
                        attr("loop", "true")
                    }) {
                        Source(attrs = {
                            attr("type", "video/webm")
                            attr("src", imageConfig.imageSrc)
                        })
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(topBottom = 12.px),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                imageRowConfig.imageSrc.forEachIndexed { index, imageSrc ->
                    Box(
                        modifier = imageComponentStyle
                            .toModifier(),
                        contentAlignment = Alignment.Center
                    ) {
                        A(
                            href = imageRowConfig.imageLink[index],
                            attrs = { target(ATarget.Blank) }
                        ) {
                            Image(
                                modifier = Modifier
                                    .then(
                                        if (cropped1) { Modifier.fillMaxSize() }
                                        else { Modifier.fillMaxWidth() }
                                    )
                                    .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
                                    .objectFit(if (cropped1) ObjectFit.Cover else ObjectFit.Contain)
                                    .thenIf(
                                        condition = imageRowConfig.shadowed,
                                        other = Modifier
                                            .boxShadow(
                                                color = Colors.Black.copy(alpha = 70),
                                                blurRadius = Res.Dimens.BORDER_RADIUS.px,
                                                spreadRadius = 3.px,
                                                offsetX = 2.px,
                                                offsetY = 1.px
                                            )
                                    )
                                    .maxSize(Res.Dimens.LOGO_APP_SIZE.px)
                                    .classNames(
                                        "no-drag",
                                        "no-select"
                                    )
                                    .styleModifier {
                                        userSelect(UserSelect.None)
                                    },
                                src = imageSrc
                            )
                        }
                    }
                }
            }
        }
    }
}