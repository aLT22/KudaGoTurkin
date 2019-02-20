object Versions {

    /**
     * Default App Configs
     * */
    const val compileSdkVersion = 28
    const val appId = "com.bytebuilding.kudagoturkin"
    const val minSdkVersion = 15
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1.0"
    const val proguardOptimize = "proguard-android-optimize.txt"
    const val proguardRules = "proguard-rules.pro"

    /**
     * Core
     * */
    object Core {
        const val kotlin = "1.3.20"
        const val coroutines = "1.1.1"
    }

    object DI {
        const val koin = "1.0.2"
    }

    /**
     * JetPack
     * */
    object JetPack {
        const val appCompat = "1.1.0-alpha02"
        const val constraintLayout = "1.1.3"
        const val lifecycle = "2.0.0"
    }

    /**
     * Test
     * */
    object Test {
        const val jUnit = "4.12"
        const val runner = "1.1.2-alpha01"
        const val espresso = "3.1.2-alpha01"
    }

}