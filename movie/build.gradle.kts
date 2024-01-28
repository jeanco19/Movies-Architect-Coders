plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {

    namespace = "com.jean.moviesarchitectcoders.movie"
    compileSdk = 34

    defaultConfig {
        minSdk = 25

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    // ARCHITECTURE
    implementation(project(":domain"))

    // DEFAULT
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    // LIFECYCLE
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)

    // COROUTINES
    implementation(libs.bundles.coroutines)

    // DAGGER HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // COIL
    implementation(libs.coil.kt)

    // NAVIGATION
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // TEST
    testImplementation(libs.junit)

    // ANDROID TEST
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}

kapt {
    correctErrorTypes = true
}