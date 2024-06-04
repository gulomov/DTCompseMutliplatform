package org.dtcm.work

import android.app.Application
import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import di.initKoin
import org.koin.dsl.module

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        initKoin(appModule = module { single<Context> { this@AndroidApp } })
    }
}