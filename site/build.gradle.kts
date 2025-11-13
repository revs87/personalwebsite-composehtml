@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.serialization.plugin)
     alias(libs.plugins.kobwebx.markdown)
}

group = "pt.rvcoding.personalwebsitecomposehtml"
version = "1.6"


tasks.register("kobwebBuildOnly") {
    group = "kobweb"
    description = "Runs the full Kobweb build (compilation, generation, etc.) without starting the server, setting DEV environment."

    // 1. Set the Kobweb configuration properties
    // We set these in a 'doFirst' block to ensure they are present right before
    // the dependent task ('kobwebExport') runs.
    doFirst {
        project.ext.set("kobwebEnv", "DEV")
        project.ext.set("kobwebRunLayout", "FULLSTACK")
    }

    // 2. Depend on the core Kobweb build task using the string name
    // This executes all the compilation and generation tasks (like those listed
    // in your original request) but stops short of running the server.
    //dependsOn("kobwebExport")
    dependsOn(
        // Final Backend (JVM) steps
        "jvmJar",
        "kobwebUnpackServerJar",

        // Final Frontend (JS) steps
        "jsBrowserDevelopmentWebpack"
    )
    /**
     * kobwebExport executes kobwebStart at the end - We don't want that! We just want the final jar.
     *
     * All tasks to complete the Kobweb build/export without starting it:
        :kotlinKotlinNpmCachesSetup
        :kotlinNodeJsSetup
        :kotlinRestoreYarnLock
        :kotlinYarnSetup
        :site:jsPackageJson
        :site:jsTestPackageJson
        :site:jsPublicPackageJson
        :site:jsTestPublicPackageJson
        :kotlinPackageJsonUmbrella
        :rootPackageJson
        :kotlinNpmInstall
        :kotlinStoreYarnLock
        :site:kmpPartiallyResolvedDependenciesChecker
        :site:checkKotlinGradlePluginConfigurationErrors SKIPPED
        :site:kobwebxMarkdownProcess
        :site:kobwebxMarkdownConvert
        :site:kspKotlinJs
        :site:kobwebCacheAppFrontendData
        :site:kobwebGenSiteEntry
        :site:compileKotlinJs
        :site:kobwebGenSiteIndex
        :site:kobwebCopySupplementalResources
        :site:kobwebCopyWorkerJsOutput
        :site:jsProcessResources
        :site:jsMainClasses
        :site:compileDevelopmentExecutableKotlinJs
        :site:jsDevelopmentExecutableCompileSync
        :site:jsBrowserDevelopmentWebpack
        :site:kspKotlinJvm
        :site:kobwebCacheAppBackendData
        :site:kobwebGenApisFactory
        :site:compileKotlinJvm
        :site:compileJvmMainJava NO-SOURCE
        :site:jvmProcessResources
        :site:processJvmMainResources SKIPPED
        :site:jvmMainClasses
        :site:jvmJar
        :site:kobwebSyncServerPluginJars NO-SOURCE
        :site:kobwebUnpackServerJar
     */
}

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
    js {
        compilerOptions.target = "es2015"
    }
    jvm()

    configAsKobwebApplication("personalwebsitecomposehtml", includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.jetbrains.compose.animation.core)
        }
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk.core)
            implementation(libs.kobweb.silk.foundation.js)
            implementation(libs.kobweb.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(npm("@docsearch/js", "3.9.0"))
            implementation(libs.compose.html.core)
        }
        jvmMain.dependencies {
            implementation(libs.kobweb.api)
        }
    }
}
