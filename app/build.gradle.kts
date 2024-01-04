plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.speerandroidassessment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.speerandroidassessment"
        minSdk = 24
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit with Scalar Converter
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    //Retrofit with xml convertor
    implementation ("com.squareup.retrofit2:converter-simplexml:2.9.0")

    // Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.9.3")

    // Retrofit with Moshi Converter
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.6")

    //couroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    //coil
    implementation ("io.coil-kt:coil:2.2.2")

    //swipe refresh
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}