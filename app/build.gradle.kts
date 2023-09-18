plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = "com.example.weatherapp"

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            buildConfigField("Boolean", "ENABLING_SCREEN_CITY", "true")
        }
        debug {
            isMinifyEnabled = false
//            buildConfigField("Boolean", "ENABLING_SCREEN_CITY", "false")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":features:weather"))
    implementation(project(":features:cities"))
    implementation(project(":features:search"))
    implementation(project(":moduleinjector"))
    implementation(project(":core"))

    implementation(AppDependencies.appLibraries)
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    kapt(AppDependencies.kapt)
}