plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "org.demre.certificacion"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.demre.certificacion"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.test.ext:junit-ktx:1.1.5")


    // Retrofit
    val retroVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retroVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retroVersion")
    implementation ("com.squareup.retrofit2:converter-scalars:$retroVersion")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // ciclo de vida
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.1")

    // Coil
    implementation ("io.coil-kt:coil:2.4.0")

    // Romm
    val roomVersion = "2.5.2"
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")

    // Test
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("androidx.test.ext:truth:1.5.0")
    testImplementation ("androidx.test:core-ktx:1.5.0")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("androidx.test.ext:truth:1.5.0")
    testImplementation ("org.robolectric:robolectric:4.4")
    testImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation ("org.robolectric:robolectric:4.4")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-alpha03@jar")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.7.2")

}