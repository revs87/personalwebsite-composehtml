package pt.rvcoding.personalwebsitecomposehtml.presentation.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.userSelect
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@Composable
fun EmptySide(
    breakpoint: Breakpoint = Breakpoint.XL,
    expanded: Boolean = true,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .thenIf(
                condition = breakpoint > Breakpoint.MD,
                other = Modifier
                    .padding(bottom = 24.px)
                    .height(size = (
                            if (expanded) Res.Dimens.MAX_CARD_HEIGHT
                            else Res.Dimens.MAX_CARD_HEIGHT_COLLAPSED
                         ).px
                    )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .styleModifier {
                    userSelect(UserSelect.None)
                },
        )
    }
}