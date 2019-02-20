object Dependencies {

    /**
     * Core
     * */
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Core.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Core.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Core.coroutines}"
    val coroutines = arrayOf(
        coroutinesCore,
        coroutinesAndroid
    )

    /**
     * DI (Koin)
     * */
    const val koinAndroid = "org.koin:koin-android:${Versions.DI.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.DI.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.DI.koin}"
    val koinLibs = arrayOf(
        koinAndroid,
        koinScope,
        koinViewModel
    )

    /**
     * JetPack
     * */
    const val appCompat = "androidx.appcompat:appcompat:${Versions.JetPack.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.JetPack.constraintLayout}"
    val archLibs = arrayOf(
        "androidx.lifecycle:lifecycle-runtime:${Versions.JetPack.lifecycle}",
        "androidx.lifecycle:lifecycle-extensions:${Versions.JetPack.lifecycle}",
        "androidx.lifecycle:lifecycle-viewmodel:${Versions.JetPack.lifecycle}",
        "androidx.lifecycle:lifecycle-common:${Versions.JetPack.lifecycle}"
    )
    val archCompilers = arrayOf(
        "androidx.lifecycle:lifecycle-runtime:${Versions.JetPack.lifecycle}",
        "androidx.lifecycle:lifecycle-compiler:${Versions.JetPack.lifecycle}"
    )

    /**
     * Test
     * */
    const val jUnit = "junit:junit:${Versions.Test.jUnit}"
    const val runner = "androidx.test:runner:${Versions.Test.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
    val testDeps = arrayOf(
        jUnit,
        runner,
        espresso
    )

}