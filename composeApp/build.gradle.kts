@file:Suppress("LocalVariableName")

import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatformAppConvention)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildkonfig)
}

"ru.yandex.maps.workshop".let { appId ->
    android.namespace = appId
    android.defaultConfig.applicationId = appId
    buildkonfig.packageName = appId
}

buildkonfig {
    val folderId: String by rootProject.extra
    val gptToken: String by rootProject.extra
    val mapkitToken: String by rootProject.extra
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "folderId", folderId)
        buildConfigField(FieldSpec.Type.STRING, "gptToken", gptToken)
        buildConfigField(FieldSpec.Type.STRING, "mapkitToken", mapkitToken)
    }
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets.androidMain.dependencies {
        implementation(compose.preview)
        implementation(libs.androidx.activity.compose)
        
        // Ktor Android клиент
        implementation(libs.ktor.client.okhttp)
    }
    sourceSets.commonMain.dependencies {
        api(projects.common)

        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.ui)
        implementation(compose.components.resources)
        implementation(compose.components.uiToolingPreview)
        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.runtimeCompose)
        
        // Ktor для HTTP запросов
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.client.json)
        implementation(libs.ktor.client.logging)
        implementation(libs.kotlinx.serialization)

    }
    sourceSets.commonTest.dependencies {
        implementation(libs.kotlin.test)
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}


