import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.serialization.plugin)
    // alias(libs.plugins.kobwebx.markdown)
}

group = "pt.rvcoding.personalwebsitecomposehtml"
version = "1.6"


//tasks.create("kobweb") {
//    println("GRADLE: Schedule task :kobweb")
//    doLast {
//        println("GRADLE: Entering task :kobweb")
//        // Jar method
//        Runtime.getRuntime().exec("java -Dkobweb.version=0.9.13 -jar site/src/jsMain/libs/kobweb-0.9.13-all.jar run -p site/")
//
//        // Kobweb binary method
//        //if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) {
//        //    Runtime.getRuntime().exec(".\\kobweb-0.9.13\\bin\\kobweb.bat run -p site/")
//        //} else {
//        //    Runtime.getRuntime().exec("./kobweb-0.9.13/bin/kobweb run -p site/")
//        //}
//    }
//}
//
//tasks.create("stage") {
//    println("GRADLE: Entering task :stage")
//    dependsOn("kobweb")
//}

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")

            head.add {
                link {
                    rel = "stylesheet"
                    href = "/styles/styles.css"
                    type = "text/css"
                }
            }
        }
    }
}

kotlin {
    // https://kotlinlang.org/docs/multiplatform-dsl-reference.html#targets
    js()
    jvm()

    configAsKobwebApplication("personalwebsitecomposehtml", includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.jetbrains.compose.animation.core)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            // implementation(libs.kobwebx.markdown)
        }
        jvmMain.dependencies {
//            implementation(libs.ktor.server.core)
//            implementation(libs.ktor.server.netty)
//            implementation(libs.ktor.client.okhttp)
//            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kobweb.api)
            implementation(libs.config.fig)
        }
    }
}
