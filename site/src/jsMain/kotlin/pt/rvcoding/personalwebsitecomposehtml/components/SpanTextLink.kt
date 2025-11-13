package pt.rvcoding.personalwebsitecomposehtml.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.WordBreak
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.wordBreak
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Span
import pt.rvcoding.personalwebsitecomposehtml.presentation.components.ContentAlignment
import pt.rvcoding.personalwebsitecomposehtml.styles.MyLinkStyle
import pt.rvcoding.personalwebsitecomposehtml.util.Res


@Composable
fun SpanTextLink(
    modifier: Modifier = Modifier,
    contentAlignment: ContentAlignment = ContentAlignment.Left,
    text: String,
    bold: Boolean,
    breakpoint: Breakpoint
) {
    val list by remember { mutableStateOf(extractContentToList(text)) }

    Row(
        modifier = Modifier.fillMaxWidth().wordBreak(WordBreak.KeepAll)
    ) {
        list.forEach { (text, link) ->
            if (link.isEmpty()) {
                SpanText(
                    text = text,
                    modifier = modifier
                        .textAlign(
                            if (breakpoint <= Breakpoint.SM) TextAlign.Justify
                            else when (contentAlignment) {
                                ContentAlignment.Left -> TextAlign.Start
                                ContentAlignment.Right -> TextAlign.End
                            }
                        )
                        .thenIf(
                            condition = bold,
                            other = Modifier.fontFamily(Res.String.ROBOTO_CONDENSED)
                        )
                )
            } else {
                if (link.startsWith("/raw/")) {
                    A(
                        href = link,
                        attrs = { target(ATarget.Blank) }
                    ) {
                        SpanText(
                            text = text,
                            modifier = modifier
                                .textAlign(
                                    if (breakpoint <= Breakpoint.SM) TextAlign.Justify
                                    else when (contentAlignment) {
                                        ContentAlignment.Left -> TextAlign.Start
                                        ContentAlignment.Right -> TextAlign.End
                                    }
                                )
                                .thenIf(
                                    condition = bold,
                                    other = Modifier.fontFamily(Res.String.ROBOTO_CONDENSED)
                                )
                        )
                    }
                } else {
                    Span {
                        Link(
                            path = link,
                            text = text,
                            modifier = MyLinkStyle
                                .toModifier()
                                .textAlign(
                                    if (breakpoint <= Breakpoint.SM) TextAlign.Justify
                                    else when (contentAlignment) {
                                        ContentAlignment.Left -> TextAlign.Start
                                        ContentAlignment.Right -> TextAlign.End
                                    }
                                )
                                .thenIf(
                                    condition = bold,
                                    other = Modifier.fontFamily(Res.String.ROBOTO_CONDENSED)
                                ),
                            openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
                            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
                        )
                    }
                }
            }
        }
    }
}

private fun extractContentToList(input: String): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()
    val text2 = input.split("[[", "]]")
    text2.forEach { text ->
        if (text.contains("][")) {
            val parts = text.split("][")
            result.add(Pair(parts[0], parts[1]))
        } else if (text.isNotBlank()) {
            result.add(Pair(text, ""))
        }
    }
    return result
}