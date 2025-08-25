plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    api(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    api(libs.plugin.kotlin)
    api(libs.plugin.android)
}
