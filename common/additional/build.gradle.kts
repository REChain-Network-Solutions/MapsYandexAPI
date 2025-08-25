@file:Suppress("LocalVariableName")

plugins {
    alias(libs.plugins.multiplatformModuleConvention)
    alias(libs.plugins.kotlinSerialization)
}

android.namespace = "ru.yandex.maps.workshop.common.additional"

kotlin {
    sourceSets.androidMain.dependencies {
        implementation(libs.ktor.client.okhttp)
    }

    sourceSets.commonMain.dependencies {
        api(libs.kotlinx.coroutines)
        api(libs.multiplatform.settings.coroutines)
        api(libs.multiplatform.settings)
        api(libs.kotlinx.serialization)
        api(libs.multiplatform.settings)
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.json)
        implementation(libs.ktor.client.content.negotiation)
    }
    sourceSets.iosMain.dependencies {
        implementation(libs.ktor.client.darwin)
    }
}
