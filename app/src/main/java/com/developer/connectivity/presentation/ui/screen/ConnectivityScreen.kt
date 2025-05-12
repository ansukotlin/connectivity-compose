package com.developer.connectivity.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.developer.connectivity.R
import com.developer.connectivity.presentation.viewmodel.ConnectivityViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Composable que exibe a tela de status de conectividade, mostrando um ícone representando o estado da conexão de rede.
 * A tela exibe uma imagem centralizada que indica se a conexão está ativa (ícone de Wi-Fi ligado) ou inativa (ícone de Wi-Fi desligado).
 *
 * @param viewModel ViewModel que fornece o estado de conectividade. Injetado automaticamente por padrão usando Koin.
 *
 * **Português:**
 * Esta função Composable cria uma interface de usuário que ocupa toda a tela, com fundo branco e uma imagem centralizada
 * de 250dp, alternando entre ícones de Wi-Fi ligado ou desligado com base no estado de conectividade observado pelo ViewModel.
 * O estado de conexão é coletado de forma segura utilizando `collectAsStateWithLifecycle` para gerenciar o ciclo de vida.
 *
 * **English:**
 * This Composable function creates a user interface that fills the entire screen with a white background and a centered
 * 250dp image, toggling between Wi-Fi on or off icons based on the connectivity state observed from the ViewModel.
 * The connection state is safely collected using `collectAsStateWithLifecycle` to handle lifecycle management.
 */
@Composable
fun ConnectivityScreen(
    viewModel: ConnectivityViewModel = koinViewModel()
) {
    val isConnected by viewModel.connected.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), //
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(250.dp),
            painter = painterResource(
                id = if (isConnected) R.drawable.wifi_on
                else R.drawable.wifi_off
            ),
            contentDescription = null
        )
    }
}
























