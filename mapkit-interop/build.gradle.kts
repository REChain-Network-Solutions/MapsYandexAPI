@file:Suppress("LocalVariableName")

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.DefFileTask

plugins {
    alias(libs.plugins.multiplatformModuleConvention)
//    alias(libs.plugins.kotlinCocoapods)
}

android.namespace = "ru.yandex.maps.workshop.mapkit.kmp"


kotlin {
//    cocoapods {
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
//        ios.deploymentTarget = "15.0"
//        podfile = project.file("../iosApp/Podfile")
//        noPodspec()
//
//        pod("YandexMapsMobile") {
//            version = libs.versions.mapkit.get()
//            packageName = "platform.YandexMapsMobile"
//        }
//    }

    sourceSets.androidMain.dependencies {
        api(libs.mapkit)
    }

    sourceSets.all {
        languageSettings {
            progressiveMode = true
        }
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compileTaskProvider {
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        freeCompilerArgs.add("-Xconsistent-data-class-copy-visibility")
    }
}

tasks.withType<DefFileTask>().configureEach {
    if (name.contains("YandexMaps")) {
        val sourceDef = file("src/nativeInterop/cinterop/YandexMapsMobile.def")
        val defFile = defFile.get().asFile
        inputs.file(sourceDef)
        outputs.file(defFile)
        doLast {
            sourceDef.copyTo(defFile, overwrite = true)
        }
    }
}
