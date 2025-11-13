package pt.rvcoding.personalwebsitecomposehtml.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext


@Api(routeOverride = "config")
suspend fun config(context: ApiContext) {
//    try {
//        context.res.setBodyText(
//            Json.encodeToString<ApiResponse>(
//                ApiResponse.ConfigSuccess(data = getFigConfig())
//            )
//        )
//    } catch (e: Exception) {
//        context.res.setBodyText(
//            Json.encodeToString<ApiResponse>(
//                ApiResponse.Error(errorMessage = e.message.toString())
//            )
//        )
//    }
}

//suspend fun getFigConfig(): FigConfig {
//    val fig = Fig()
//    fig.init(sheetUrl = "https://docs.google.com/spreadsheets/d/1csA2uKp2_8DyjpnCr0ehK7RQHWGLGFTITHOAozK9zZ0/edit?usp=sharing")
//
//    val config = FigConfig(
//        msgHelloWorld = fig.getValue(key = "msg_hello_world", defaultValue = "") ?: "",
//        menuHistoryWorkEnabled = fig.getValue(key = "menu_history_work_enabled", defaultValue = null).asBoolean(),
//        menuHistoryProjectsEnabled = fig.getValue(key = "menu_history_projects_enabled", defaultValue = null).asBoolean(),
//        menuAboutMeEnabled = fig.getValue(key = "menu_about_me_enabled", defaultValue = null).asBoolean()
//    )
//    println(config)
//    return config
//}
//
//private fun String?.asBoolean(): Boolean {
//    return when {
//        this == null -> false
//        this.lowercase() == "true" -> true
//        else -> false
//    }
//}
