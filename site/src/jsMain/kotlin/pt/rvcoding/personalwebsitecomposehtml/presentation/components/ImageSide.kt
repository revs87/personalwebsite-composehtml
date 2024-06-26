package pt.rvcoding.personalwebsitecomposehtml.presentation.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@Composable
fun ImageSide(
    breakpoint: Breakpoint = Breakpoint.XL,
    expanded: Boolean = true,
    imageSrc: String = Res.Image.PROFILE_PHOTO
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .thenIf(
                condition = breakpoint > Breakpoint.MD,
                other = Modifier
                    .height(size = ((
                            if (expanded) Res.Dimens.MAX_CARD_HEIGHT
                            else Res.Dimens.MAX_CARD_HEIGHT_COLLAPSED
                         ) - 24).px
                    )
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
                .objectFit(ObjectFit.Cover)
                .boxShadow(
                    color = Colors.Black.copy(alpha = 70),
                    blurRadius = Res.Dimens.BORDER_RADIUS.px,
                    spreadRadius = 3.px,
                    offsetX = 2.px,
                    offsetY = 1.px
                ),
            src = imageSrc
        )
    }
}