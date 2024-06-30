package pt.rvcoding.personalwebsitecomposehtml.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import pt.rvcoding.personalwebsitecomposehtml.models.content.ProfileData
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
        link = ProfileData.Default.linkedin
    ),
    LinkedInLight(
        icon = Res.Icon.LINKEDIN_LIGHT,
        link = ProfileData.Default.linkedin
    ),
    Github(
        icon = Res.Icon.GITHUB,
        link = ProfileData.Default.github
    ),
    GithubLight(
        icon = Res.Icon.GITHUB_LIGHT,
        link = ProfileData.Default.github
    ),
    StackOverflow(
        icon = Res.Icon.STACKOVERFLOW,
        link = ProfileData.Default.stackoverflow
    ),
    StackOverflowLight(
        icon = Res.Icon.STACKOVERFLOW_LIGHT,
        link = ProfileData.Default.stackoverflow
    ),
    X(
        icon = Res.Icon.X,
        link = ProfileData.Default.twitterX
    ),
    XLight(
        icon = Res.Icon.X_LIGHT,
        link = ProfileData.Default.twitterX
    ),
    Instagram(
        icon = Res.Icon.INSTAGRAM,
        link = ProfileData.Default.instagram
    ),
    InstagramLight(
        icon = Res.Icon.INSTAGRAM_LIGHT,
        link = ProfileData.Default.instagram
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