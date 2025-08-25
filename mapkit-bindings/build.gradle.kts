plugins {
    alias(libs.plugins.multiplatformModuleConvention)
}

android.namespace = "com.yandex.mapkit.bindings.bindings"
kotlin {
    sourceSets.commonMain.dependencies {
        api(projects.mapkitInterop)
    }
}
