plugins {
    id("com.android.library")
}

android {
    namespace = "ru.justmaxiz.saransk.network"
    compileSdk = 34 // Или 35, смотря что в основном проекте

    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", project.findProperty("BASE_URL") as String)
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", project.findProperty("BASE_URL") as String)
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit — сердце сети
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Конвертер для JSON (Gson — самый простой и надежный для конкурса)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp и логирование (чтобы ты в Logcat видел, что отправляешь)
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Тесты (пригодятся позже)
    testImplementation("junit:junit:4.13.2")
}