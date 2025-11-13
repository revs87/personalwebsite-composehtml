package pt.rvcoding.personalwebsitecomposehtml.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.json.Json
import pt.rvcoding.personalwebsitecomposehtml.models.ApiResponse
import pt.rvcoding.personalwebsitecomposehtml.models.content.ContentData


@Api(routeOverride = "profile")
suspend fun profile(context: ApiContext) {
    try {
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.ProfileSuccess(data = ContentData.Profile.main)
            )
        )
    } catch (e: Exception) {
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Error(errorMessage = e.message.toString())
            )
        )
    }
}