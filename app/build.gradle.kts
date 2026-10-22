plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.yize.miniweather"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.yize.miniweather"
        minSdk = 23
        // Required for Google Play app updates since August 2026.
        // Android 17 (API 37) runtime behavior is covered by compatibility testing.
        targetSdk = 36
        versionCode = 1
        versionName = "1.5"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree("libs") { include("*.jar") })

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy)
    implementation(libs.androidx.recyclerview)
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.glide)
    implementation(libs.material)
    implementation(libs.tencent.location)
    implementation(libs.autobus)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.espresso.core)
}
