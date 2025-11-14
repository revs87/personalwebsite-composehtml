package pt.rvcoding.personalwebsitecomposehtml.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.models.content.ContentData
import pt.rvcoding.personalwebsitecomposehtml.util.Res
import pt.rvcoding.personalwebsitecomposehtml.util.Res.Dimens.BORDER_RADIUS

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    colorMode: ColorMode,
    link: String = "",
    icon: String,
    iconSize: CSSSizeValue<CSSUnit.px> = Res.Dimens.ICON_SIZE.px,
    onClick: (() -> Unit)? = null
) {
    Link(path = link) {
        Box(
            modifier = modifier
                .padding(all = 8.px)
                .borderRadius(r = BORDER_RADIUS.px)
                .cursor(Cursor.Pointer)
                .border(
                    width = 1.px,
                    style = LineStyle.Solid,
                    color = if (colorMode.isLight) Res.Theme.SOCIAL_ICON_BACKGROUND_LIGHT.color
                    else Res.Theme.SOCIAL_ICON_BACKGROUND_DARK.color
                )
                .onClick { onClick?.invoke() }
        ){
            Image(
                modifier = Modifier.size(iconSize),
                src = icon
            )
        }
    }
}

enum class SocialIcon(
    val icon: String,
    val link: String? = null
) {
    LinkedIn(
        icon = Res.Icon.LINKEDIN,
        link = ContentData.Profile.main.linkedin
    ),
    LinkedInLight(
        icon = Res.Icon.LINKEDIN_LIGHT,
        link = ContentData.Profile.main.linkedin
    ),
    Github(
        icon = Res.Icon.GITHUB,
        link = ContentData.Profile.main.github
    ),
    GithubLight(
        icon = Res.Icon.GITHUB_LIGHT,
        link = ContentData.Profile.main.github
    ),
    StackOverflow(
        icon = Res.Icon.STACKOVERFLOW,
        link = ContentData.Profile.main.stackoverflow
    ),
    StackOverflowLight(
        icon = Res.Icon.STACKOVERFLOW_LIGHT,
        link = ContentData.Profile.main.stackoverflow
    ),
    X(
        icon = Res.Icon.X,
        link = ContentData.Profile.main.twitterX
    ),
    XLight(
        icon = Res.Icon.X_LIGHT,
        link = ContentData.Profile.main.twitterX
    ),
    Instagram(
        icon = Res.Icon.INSTAGRAM,
        link = ContentData.Profile.main.instagram
    ),
    InstagramLight(
        icon = Res.Icon.INSTAGRAM_LIGHT,
        link = ContentData.Profile.main.instagram
    );

    companion object {
        val Active by lazy { listOf(
            LinkedIn,
            LinkedInLight,
            Github,
            GithubLight,
            StackOverflow,
            StackOverflowLight,
            X,
            XLight
        ) }
    }
}