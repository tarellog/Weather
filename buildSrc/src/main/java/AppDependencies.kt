import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //android ui
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.material}"
    private val gson = "com.google.code.gson:gson:${Versions.gson}"
    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    private val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.frgamentKtx}"
    private val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    private val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
    private val fastAdapter = "com.mikepenz:fastadapter:${Versions.fastAdapter}"
    private val fastAdapterBinding = "com.mikepenz:fastadapter-extensions-binding:${Versions.fastBinding}"
    private val fastAdapterDiff = "com.mikepenz:fastadapter-extensions-diff:${Versions.fastDiff}"
    private val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recycler}"
    private val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    private val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    private val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    private val rxRetrofit = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxRetrofit}"

    //kapt
    private val daggerKapt = "com.google.dagger:dagger-compiler:2.41"

    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val appLibraries = listOf(
        kotlinStdLib,
        coreKtx,
        appcompat,
        constraintLayout,
        material,
        gson,
        retrofit,
        converterGson,
        fragmentKtx,
        viewModelKtx,
        navigation,
        navigationUi,
        fastAdapter,
        fastAdapterBinding,
        fastAdapterDiff,
        recyclerView,
        dagger,
        rxAndroid,
        rxJava,
        rxRetrofit,
    )

    val kapt = listOf (
        daggerKapt
    )

    val androidTestLibraries = listOf(
        extJUnit,
        espressoCore
    )

    val testLibraries = listOf(
        junit
    )
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}