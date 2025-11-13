package pt.rvcoding.personalwebsitecomposehtml.presentation.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.userSelect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.components.IconButton
import pt.rvcoding.personalwebsitecomposehtml.components.SocialIcon
import pt.rvcoding.personalwebsitecomposehtml.models.content.ContentData
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.ContentAlignment
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.ImageSide
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.TextSide
import pt.rvcoding.personalwebsitecomposehtml.styles.ButtonStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.SocialIconMobileStyle
import pt.rvcoding.personalwebsitecomposehtml.styles.SocialIconStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res

@OptIn(DelicateApi::class)
@Composable
fun ProfileCard(colorMode: ColorMode = ColorMode.LIGHT) {
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
        TextSide(
            colorMode = colorMode,
            breakpoint = breakpoint,
            headerAlignment = ContentAlignment.Right,
            expanded = expanded,
            title = ContentData.Profile.main.name,
            subTitle = ContentData.Profile.main.profession,
            description = ContentData.Profile.main.description,
            extra = {
                MyEmailButton(colorMode)
                SocialIcons(breakpoint, colorMode)
            }
        )
        ImageSide(
            breakpoint = breakpoint,
            expanded = expanded
        )
    }
}

@Composable
private fun MyEmailButton(colorMode: ColorMode) {
    Button(
        modifier = ButtonStyle
            .toModifier()
            .margin(bottom = 50.px),
        size = ButtonSize.LG,
        onClick = { window.location.href = "mailto:${ContentData.Profile.main.email}" }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.margin(right = 12.px),
                src = if (colorMode.isLight) Res.Icon.EMAIL_LIGHT
                else Res.Icon.EMAIL_DARK
            )
            SpanText(
                modifier = Modifier
                    .fontSize(14.px)
                    .color(
                        if (colorMode.isLight) Colors.White
                        else Res.Theme.GRADIENT_ONE_DARK.color
                    )
                    .fontWeight(FontWeight.Bold)
                    .fontFamily(Res.String.ROBOTO_REGULAR)
                    .styleModifier {
                        userSelect(UserSelect.None)
                    },
                text = Res.String.BUTTON_TEXT
            )
        }
    }
}

@Composable
private fun SocialIcons(
    breakpoint: Breakpoint,
    colorMode: ColorMode
) {
    val imageComponentStyle = if (breakpoint > Breakpoint.MD) SocialIconStyle else SocialIconMobileStyle

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .gap(12.px),
        horizontalArrangement = if (breakpoint <= Breakpoint.SM)
            Arrangement.Center else Arrangement.Start
    ) {
        SocialIcon.Active.filter {
            if (colorMode.isLight) !it.name.contains("Light")
            else it.name.contains("Light")
        }.forEach { social ->
            social.link?.let {
                IconButton(
                    modifier = imageComponentStyle.toModifier(),
                    colorMode = colorMode,
                    icon = social.icon,
                    link = social.link
                )
            }
        }
    }
}
