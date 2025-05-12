package com.developer.connectivity.core.connectivity

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService

/**
 * Classe utilitária para acessar o serviço [ConnectivityManager] do sistema.
 *
 * **Português:**
 * Esta classe fornece um método estático para obter uma instância do [ConnectivityManager], que é
 * usado para verificar o estado da conectividade de rede no dispositivo. A classe utiliza um
 * [companion object] para encapsular a lógica de acesso ao serviço, garantindo uma interface simples
 * e reutilizável.
 *
 * **English:**
 * This utility class provides a static method to obtain an instance of [ConnectivityManager], which is
 * used to check the network connectivity status on the device. The class uses a [companion object] to
 * encapsulate the service access logic, ensuring a simple and reusable interface.
 */
class AndroidConnectivityManager {

    companion object {

        /**
         * Obtém uma instância do [ConnectivityManager] para o contexto fornecido.
         *
         * @param context Contexto da aplicação ou atividade usado para acessar serviços do sistema.
         * @return Uma instância do [ConnectivityManager].
         * @throws NullPointerException Se o serviço [ConnectivityManager] não for encontrado.
         *
         * **Português:**
         * Este método utiliza a extensão [getSystemService] para recuperar o [ConnectivityManager] do
         * sistema com base no contexto fornecido. O operador `!!` é usado, assumindo que o serviço
         * estará disponível em condições normais. Caso o serviço não seja encontrado, uma
         * [NullPointerException] será lançada.
         *
         * **English:**
         * This method uses the [getSystemService] extension to retrieve the [ConnectivityManager] from
         * the system based on the provided context. The `!!` operator is used, assuming the service
         * is available under normal conditions. If the service is not found, a [NullPointerException]
         * will be thrown.
         */
        fun getInstance(context: Context): ConnectivityManager {
            return context.getSystemService<ConnectivityManager>()!!
        }
    }
}