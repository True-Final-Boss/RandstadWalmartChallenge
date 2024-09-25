plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.walmartchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.walmartchallenge"
        minSdk = 26
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.material)



    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    //RecyclerView
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)

    //OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    //GSON
    implementation(libs.gson)
    implementation(libs.converter.gson)

    //Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v284)
    implementation(libs.androidx.lifecycle.compiler)

    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)

    implementation(libs.androidx.lifecycle.viewmodel.ktx.v250alpha01)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v250alpha01)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v250alpha01)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)




    //test implementation
    testImplementation (libs.junit)

    //test for viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.compose.v251)

    //test coroutine
    testImplementation (libs.kotlinx.coroutines.test)

    //mockito
    testImplementation (libs.mockito.core)
    androidTestImplementation (libs.mockito.android)
    androidTestImplementation (libs.mockito.android)

    testImplementation (libs.androidx.core.testing)

}