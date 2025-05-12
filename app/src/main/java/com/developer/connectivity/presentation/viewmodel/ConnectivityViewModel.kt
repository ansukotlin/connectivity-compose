package com.developer.connectivity.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.connectivity.core.connectivity.ConnectivityObserver
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel responsável por gerenciar o estado de conectividade de rede da aplicação.
 * Observa mudanças na conectividade utilizando um [ConnectivityObserver] e expõe o estado
 * de conexão como um StateFlow para ser consumido por composables.
 *
 * @param connectivityObserver Instância de [ConnectivityObserver] usada para monitorar o estado
 *                             de conectividade de rede.
 *
 * **Português:**
 * Esta classe gerencia o estado de conectividade de rede, fornecendo um StateFlow que indica
 * se a aplicação está conectada ou não. Utiliza o [ConnectivityObserver] para observar mudanças
 * no estado da rede e atualiza o estado de forma reativa, sendo otimizada para uso em interfaces
 * baseadas em Jetpack Compose.
 *
 * **English:**
 * This class manages the network connectivity state, providing a StateFlow that indicates
 * whether the application is connected or not. It uses the [ConnectivityObserver] to observe
 * changes in the network state and updates the state reactively, optimized for use in Jetpack
 * Compose-based interfaces.
 */
class ConnectivityViewModel(
    connectivityObserver: ConnectivityObserver
) : ViewModel() {

    /**
     * StateFlow que emite o estado de conectividade de rede (verdadeiro se conectado, falso caso contrário).
     * O estado é coletado no [viewModelScope] e configurado para compartilhar atualizações enquanto
     * houver assinantes, com um atraso de 5 segundos para parar a emissão quando não houver mais
     * assinantes.
     *
     * **English:**
     * StateFlow that emits the network connectivity state (true if connected, false otherwise).
     * The state is collected in the [viewModelScope] and configured to share updates while there
     * are subscribers, with a 5-second delay to stop emission when there are no more subscribers.
     */
    val connected = connectivityObserver.connected.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )
}

























