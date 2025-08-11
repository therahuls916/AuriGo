plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.rahul.auric.aurigo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rahul.auric.aurigo"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Standard Compose & Android KTX
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Firebase Bill of Materials (BoM) - Manages versions for all Firebase libraries
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    // Individual Firebase Libraries
    implementation("com.google.firebase:firebase-auth-ktx") // For Authentication
    implementation("com.google.firebase:firebase-database-ktx") // For Realtime Database
    implementation("com.google.firebase:firebase-firestore-ktx") // For Firestore (optional but good to have)
    implementation("com.google.firebase:firebase-messaging-ktx") // For Push Notifications

    // Mapbox SDK
    implementation("com.mapbox.maps:android:11.3.1")

    // Lottie for Animations (optional, but good for splash/loading screens)
    // implementation("com.airbnb.android:lottie-compose:6.3.0")

    // Testing Libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.02"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}