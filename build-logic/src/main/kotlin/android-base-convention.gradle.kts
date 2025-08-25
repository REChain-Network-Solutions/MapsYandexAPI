import com.android.build.gradle.BaseExtension
import utils.libs

configure<BaseExtension> {
    compileSdkVersion(project.libs.versions.android.compileSdk.get().toInt())
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdkVersion(libs.versions.android.targetSdk.get().toInt())
    }
    compileOptions {
        val currentJavaVersion = JavaVersion.VERSION_11
        sourceCompatibility = currentJavaVersion
        targetCompatibility = currentJavaVersion
    }
}
