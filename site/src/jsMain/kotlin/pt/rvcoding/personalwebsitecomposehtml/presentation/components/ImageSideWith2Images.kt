package pt.rvcoding.personalwebsitecomposehtml.presentation.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.userSelect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.domain.ImageConfig
import pt.rvcoding.personalwebsitecomposehtml.styles.ImageMobileStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.ImageStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@Composable
fun ImageSideWith2Images(
    breakpoint: Breakpoint = Breakpoint.XL,
    expanded: Boolean = true,
    image1Config: ImageConfig = ImageConfig.Default,
    image2Config: ImageConfig = ImageConfig.Default
) {
    val cropped1 = if (expanded) image1Config.croppedOnExpanded else image1Config.croppedOnCollapsed
    val cropped2 = if (expanded) image2Config.croppedOnExpanded else image2Config.croppedOnCollapsed
    val imageComponentStyle = if (breakpoint > Breakpoint.MD) ImageStyle else ImageMobileStyle

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                when {
                    breakpoint > Breakpoint.MD -> {
                        Modifier
                            .padding(bottom = 24.px)
                            .height(size = (
                                    if (expanded) Res.Dimens.MAX_CARD_HEIGHT
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
                Image(
                    modifier = Modifier
                        .then(
                            if (cropped1) { Modifier.fillMaxSize() }
                            else { Modifier.fillMaxWidth() }
                        )
                        .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
                        .objectFit(if (cropped1) ObjectFit.Cover else ObjectFit.Contain)
                        .thenIf(
                            condition = image1Config.shadowed,
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
                        },
                    src = image1Config.imageSrc
                )
            }
            Box(
                modifier = imageComponentStyle
                    .toModifier()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .then(
                            if (cropped2) { Modifier.fillMaxSize() }
                            else { Modifier.fillMaxWidth() }
                        )
                        .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
                        .objectFit(if (cropped2) ObjectFit.Cover else ObjectFit.Contain)
                        .thenIf(
                            condition = image2Config.shadowed,
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
                        },
                    src = image2Config.imageSrc
                )
            }
        }
    }
}