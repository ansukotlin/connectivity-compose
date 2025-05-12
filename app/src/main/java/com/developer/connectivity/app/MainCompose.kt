package com.developer.connectivity.app

import androidx.compose.runtime.Composable
import com.developer.connectivity.presentation.ui.screen.ConnectivityScreen
import com.developer.connectivity.presentation.viewmodel.ConnectivityViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Composable que serve como ponto de entrada para a interface do usuário da aplicação.
 * Renderiza a tela principal `ConnectivityScreen` para exibir o conteúdo da aplicação.
 *
 * @see ConnectivityScreen Composable que define a interface principal da aplicação.
 * @see Composable Anotação do Jetpack Compose para funções que descrevem a UI.
 *
 * **Português:**
 * Função composable que atua como o ponto de entrada da UI, chamando `ConnectivityScreen` para exibir a tela
 * principal da aplicação. É usada como conteúdo inicial em `MainActivity`.
 *
 * **English:**
 * Composable function that serves as the entry point for the UI, invoking `ConnectivityScreen` to display the
 * main application screen. It is used as the initial content in `MainActivity`.
 */
@Composable
fun MainCompose() {
    ConnectivityScreen()
}






























