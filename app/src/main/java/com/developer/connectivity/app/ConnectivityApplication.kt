package com.developer.connectivity.app

import android.app.Application
import com.developer.connectivity.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Classe de aplicação personalizada que serve como ponto de entrada para a inicialização global da aplicação.
 * Responsável por configurar o Koin, uma biblioteca de injeção de dependências, durante a criação da aplicação.
 *
 * @see Application Classe base do Android para inicialização global da aplicação.
 * @see startKoin Método do Koin para inicializar o contexto de injeção de dependências.
 *
 * **Português:**
 * Classe que estende `Application` para configurar a aplicação `ConnectivityApplication`. Inicializa o Koin,
 * definindo o contexto do Android e carregando o módulo de dependências `appModule` para uso em toda a aplicação.
 *
 * **English:**
 * Class that extends `Application` to set up the `ConnectivityApplication`. It initializes Koin, setting the
 * Android context and loading the `appModule` dependency module for use throughout the application.
 */
class ConnectivityApplication : Application() {

    /**
     * Método chamado quando a aplicação é criada, responsável por inicializar o Koin para injeção de dependências.
     * Configura o contexto do Android e carrega o módulo de dependências `appModule`.
     *
     * **Português:**
     * Inicializa o Koin ao criar a aplicação, definindo o contexto do Android com `ConnectivityApplication` e
     * carregando o módulo `appModule` para gerenciar as dependências da aplicação.
     *
     * **English:**
     * Initializes Koin upon application creation, setting the Android context with `ConnectivityApplication` and
     * loading the `appModule` module to manage the application's dependencies.
     */
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ConnectivityApplication)
            modules(appModule)
        }
    }
}