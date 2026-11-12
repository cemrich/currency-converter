plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)
}

android {
	namespace = "de.christinecoenen.code.currencyconverer"

	compileSdk {
		version = release(37)
	}

	defaultConfig {
		applicationId = "de.christinecoenen.code.currencyconverer"
		minSdk = 30
		targetSdk = 37
		versionCode = 1
		versionName = "0.0.1"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			optimization {
				// TODO: enabling this will trigger a runtime error when passing the baseUrl to the RetrofitBuilder
				enable = false
				packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
			}
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.retrofit)
	implementation(libs.retrofit.gson.converter)
	implementation(libs.koin.androidx.compose)
	testImplementation(libs.junit)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
	debugImplementation(libs.androidx.compose.ui.test.manifest)
	debugImplementation(libs.androidx.compose.ui.tooling)
}
