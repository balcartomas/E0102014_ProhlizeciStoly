plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.veba.e0102014_prohlizecistoly"
    compileSdk = 33

    defaultConfig {
//        archivesBaseName = "ProhlizeciStoly"
        applicationId = "com.veba.e0102014_prohlizecistoly"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("long", "TIMESTAMP", System.currentTimeMillis().toString() + "L")
        buildConfigField("String", "DOWNLOAD_NAME", "\"ProhlizeciStoly\"")
        buildConfigField("String", "VEBA_ID", "\"05-01-02-014\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = "aplikaceVeba_key"
            keyPassword = "androidaplikaceVEBA123"
            storeFile = file("C:\\Users\\balcarto\\Documents\\ANDROID_keystore\\aplikaceVeba.jks")
            storePassword = "androidaplikaceVEBA123"
        }
        getByName("debug") {
            keyAlias = "aplikaceVeba_key"
            keyPassword = "androidaplikaceVEBA123"
            storeFile = file("C:\\Users\\balcarto\\Documents\\ANDROID_keystore\\aplikaceVeba.jks")
            storePassword = "androidaplikaceVEBA123"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

}

dependencies {
    implementation("androidx.compose:compose-bom:2023.05.01")
    androidTestImplementation("androidx.compose:compose-bom:2023.05.01")

    implementation("androidx.core:core-ktx:1.10.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // *** START Compose *** \\
    val composeVersion = "1.4.3"
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.navigation:navigation-compose:2.6.0-rc01")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    // Compose UI tests
    implementation("androidx.compose.ui:ui:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    // Compose viewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Compose Preview support
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // Compose Material
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.material3:material3:1.1.0")
    // *** END Compose *** \\

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt("com.google.dagger:hilt-compiler:2.46.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Compose Nav Destinations
    val navDestinations = "1.9.42-beta"
    implementation("io.github.raamcosta.compose-destinations:core:$navDestinations")
    ksp("io.github.raamcosta.compose-destinations:ksp:$navDestinations")

    // Retrofit, OkHTTP & Moshi
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    val moshiVersion = "1.14.0"
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
}