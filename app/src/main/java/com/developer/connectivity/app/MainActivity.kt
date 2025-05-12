package com.developer.connectivity.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * Ponto de entrada principal da aplicação, responsável por inicializar a interface do usuário em Jetpack Compose.
 * Esta classe configura a atividade principal, habilita o modo edge-to-edge e define o conteúdo inicial da UI.
 *
 * @see ComponentActivity Classe base do Android para atividades que utilizam Jetpack Compose.
 * @see enableEdgeToEdge Habilita o modo edge-to-edge, permitindo que o conteúdo da aplicação se estenda sob as barras do sistema.
 * @see setContent Define o conteúdo da UI utilizando composables do Jetpack Compose.
 *
 * *Português:*
 * Classe que representa a atividade principal da aplicação, configurando o modo edge-to-edge e inicializando
 * a interface do usuário com o composable `MainCompose`. Esta classe é responsável por gerenciar o ciclo de vida
 * inicial da atividade e garantir que a UI seja renderizada corretamente.
 *
 * **English:**
 * Class representing the main activity of the application, setting up edge-to-edge mode and initializing
 * the user interface with the `MainCompose` composable. This class is responsible for managing the initial
 * lifecycle of the activity and ensuring the UI is rendered correctly.
 */
class MainActivity : ComponentActivity() {

    /**
     * Método chamado quando a atividade é criada, responsável por configurar o ambiente inicial da aplicação.
     * Habilita o modo edge-to-edge e define o composable principal `MainCompose` como conteúdo da UI.
     *
     * @param savedInstanceState Estado salvo da atividade, usado para restaurar a UI em caso de recriação.
     *
     * **Português:**
     * Configura a atividade ao ser criada, habilitando o modo edge-to-edge e definindo o composable `MainCompose`
     * como o conteúdo principal da interface do usuário.
     *
     * **English:**
     * Sets up the activity upon creation, enabling edge-to-edge mode and setting the `MainCompose` composable
     * as the main content of the user interface.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MainCompose() }
    }
}


































