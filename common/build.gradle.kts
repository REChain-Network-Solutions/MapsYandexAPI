plugins {
    alias(libs.plugins.multiplatformModuleConvention)
    alias(libs.plugins.kotlinSerialization)
}

android.namespace = "ru.yandex.maps.workshop.common"

kotlin {
    sourceSets.commonMain.dependencies {
        api(projects.common.additional)
        api(projects.mapkitBindings)
    }
}
