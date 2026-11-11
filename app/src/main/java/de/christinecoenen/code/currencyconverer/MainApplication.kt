package de.christinecoenen.code.currencyconverer

import android.app.Application
import de.christinecoenen.code.currencyconverer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@MainApplication)
			modules(appModule)
		}
	}
}
