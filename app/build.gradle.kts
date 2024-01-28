plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.safe.args)
}

android {
    namespace = "com.jean.moviesarchitectcoders"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jean.moviesarchitectcoders"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    implementation(project(":data"))
    implementation(project(":domain"))

    // DEFAULT
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.fragment.ktx)

    // LIFECYCLE
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)

    // COROUTINES
    implementation(libs.bundles.coroutines)

    // RETROFIT
    implementation(libs.bundles.retrofit)

    // DAGGER HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // ROOM DB
    implementation(libs.bundles.room.database)
    kapt(libs.room.compiler)

    // NAVIGATION
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // COIL
    implementation(libs.coil.kt)

    // PLAY SERVICES
    implementation(libs.play.services.location)

    // TEST
    testImplementation(libs.junit)

    // ANDROID TEST
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}

kapt {
    correctErrorTypes = true
}