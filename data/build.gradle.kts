import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {

    namespace = "com.jean.moviesarchitectcoders.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 25

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }
        buildConfigField("String", "TMDB_API_KEY", properties.getProperty("TMDB_API_KEY"))
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
        buildConfig = true
    }

}

dependencies {

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