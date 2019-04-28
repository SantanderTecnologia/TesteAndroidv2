package br.com.alex.bankappchallenge

import android.app.Application
import br.com.alex.bankappchallenge.di.*
import com.orhanobut.hawk.Hawk
import org.koin.core.context.startKoin

class BankAppChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupHawk()
    }

    private fun setupKoin() {
        startKoin {
            modules(listOf(
                networkModule,
                viewModelModule,
                androidModule,
                interactorModule,
                reducerModule,
                repositoryModule
            ))

            properties(mapOf(
                PROPERTY_BASE_URL to BuildConfig.API_BASE
            ))
        }
    }

    private fun setupHawk() = Hawk.init(this).build()
}