plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.compose.androidremind"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.compose.androidremind"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // 导航库
    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha07")

    // room数据库
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    // See Add the KSP plugin to your project
    ksp("androidx.room:room-compiler:$room_version")
//    annotationProcessor("androidx.room:room-compiler:$room_version")
    // If this project only uses Java source, use the Java annotationProcessor
    // No additional plugins are necessary
    annotationProcessor("androidx.room:room-compiler:$room_version")

//    添加kotlin拓展
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // 网络请求
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    val retrofit="2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
//    implementation("com.squareup.retrofit2:converter-gson:$retrofit") // 二选一即可
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // 使用okhttp 请求及日志
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // hilt
    val hilt = "2.50"
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-compiler:$hilt")
}
