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
     * JetPack
     * */
    const val appCompat = "androidx.appcompat:appcompat:${Versions.JetPack.appCompat}"

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