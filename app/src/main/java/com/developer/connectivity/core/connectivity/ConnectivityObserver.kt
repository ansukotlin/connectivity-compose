package com.developer.connectivity.core.connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.net.HttpURLConnection
import java.net.URL

/**
 * Observador de conectividade que monitora a disponibilidade e funcionalidade da rede.
 *
 * @property manager Instância do [ConnectivityManager] usada para gerenciar a conectividade de rede.
 *
 * **Português:**
 * Esta classe fornece um fluxo ([Flow]) que emite atualizações sobre o estado da conectividade de
 * rede, verificando tanto a disponibilidade da rede quanto a capacidade de acessar recursos online
 * (via requisição HTTP ao Google). Ela utiliza callbacks do [ConnectivityManager] para detectar
 * mudanças na rede e valida a conexão com uma requisição HTTP.
 *
 * **English:**
 * This class provides a [Flow] that emits updates on the network connectivity status, checking both
 * network availability and the ability to access online resources (via an HTTP request to Google). It
 * uses [ConnectivityManager] callbacks to detect network changes and validates the connection with an
 * HTTP request.
 */
class ConnectivityObserver(
    manager: ConnectivityManager
) {

    /**
     * Fluxo que emite o estado da conectividade de rede.
     *
     * @return [Flow] de [Boolean] que emite `true` se a rede estiver disponível e a conexão HTTP for
     *         bem-sucedida, ou `false` caso contrário.
     *
     * **Português:**
     * Este fluxo utiliza [callbackFlow] para monitorar mudanças na conectividade de rede, emitindo
     * valores booleanos com base na disponibilidade da rede e no sucesso de uma requisição HTTP ao
     * Google. A verificação considera a capacidade validada da rede ([NetworkCapabilities.NET_CAPABILITY_VALIDATED])
     * e o código de resposta HTTP. O fluxo é executado no dispatcher de IO ([Dispatchers.IO]) para
     * evitar bloqueios na thread principal.
     *
     * **English:**
     * This flow uses [callbackFlow] to monitor network connectivity changes, emitting boolean values
     * based on network availability and the success of an HTTP request to Google. The check considers
     * the validated network capability ([NetworkCapabilities.NET_CAPABILITY_VALIDATED]) and the HTTP
     * response code. The flow runs on the IO dispatcher ([Dispatchers.IO]) to avoid blocking the main
     * thread.
     */
    val connected: Flow<Boolean> = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            /**
             * Chamado quando as capacidades de rede mudam.
             *
             * @param network Rede que teve suas capacidades alteradas.
             * @param networkCapabilities Capacidades atuais da rede.
             *
             * **Português:**
             * Verifica se a rede possui a capacidade validada e realiza uma requisição HTTP ao Google
             * para confirmar a conectividade com a internet. Emite `true` se a rede for validada e a
             * requisição retornar HTTP 200; caso contrário, emite `false`. Exceções durante a
             * requisição são capturadas, emitindo `false`.
             *
             * **English:**
             * Checks if the network has validated capability and performs an HTTP request to Google to
             * confirm internet connectivity. Emits `true` if the network is validated and the request
             * returns HTTP 200; otherwise, emits `false`. Exceptions during the request are caught,
             * emitting `false`.
             */
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)

                try {
                    val url = URL("https://www.google.com")
                    val http = url.openConnection() as HttpURLConnection

                    val connected = networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )

                    http.connectTimeout = 3000
                    http.connect()

                    val response = http.responseCode == HttpURLConnection.HTTP_OK

                    trySend(connected && response)
                    http.disconnect()
                }
                catch (_: Exception) { trySend(false) }
            }

            /**
             * Chamado quando a rede não está disponível.
             *
             * **Português:**
             * Emite `false` para indicar que a conectividade de rede não está disponível.
             *
             * **English:**
             * Emits `false` to indicate that network connectivity is unavailable.
             */
            override fun onUnavailable() {
                super.onUnavailable()
                trySend(false)
            }

            /**
             * Chamado quando a rede é perdida.
             *
             * @param network Rede que foi perdida.
             *
             * **Português:**
             * Emite `false` para indicar que a conectividade de rede foi interrompida.
             *
             * **English:**
             * Emits `false` to indicate that network connectivity has been lost.
             */
            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }

            /**
             * Chamado quando a rede se torna disponível.
             *
             * @param network Rede que se tornou disponível.
             *
             * **Português:**
             * Emite `true` para indicar que a rede está disponível, sujeito a validação posterior em
             * [onCapabilitiesChanged].
             *
             * **English:**
             * Emits `true` to indicate that the network is available, subject to further validation in
             * [onCapabilitiesChanged].
             */
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true)
            }
        }

        manager.registerDefaultNetworkCallback(networkCallback)
        awaitClose { manager.unregisterNetworkCallback(networkCallback) }

    }.flowOn(Dispatchers.IO)
}







































