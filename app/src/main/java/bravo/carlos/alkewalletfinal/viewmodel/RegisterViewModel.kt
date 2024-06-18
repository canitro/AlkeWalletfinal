package bravo.carlos.alkewalletfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bravo.carlos.alkewalletfinal.model.RegisterRequest
import bravo.carlos.alkewalletfinal.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val Unit.id: Any
    get() = "defaultId"

class RegisterViewModel : ViewModel() {

    // Variable que almacena el resultado del registro
    val registerResultLiveData = MutableLiveData<Boolean>()

    // Función para registrar un usuario
    fun register(firstName: String, lastName: String, email: String, password: String) {
        // Corrutina para enviar la solicitud
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Enviar la solicitud utilizando ApiClient
                val response = ApiClient.apiService.register(
                    RegisterRequest(
                        first_name = firstName,
                        last_name = lastName,
                        email = email,
                        password = password
                    )
                )

                // Verificar el estado de la respuesta
                if (response.isSuccessful && response.body()?.id != null) {
                    registerResultLiveData.postValue(true)
                } else {
                    registerResultLiveData.postValue(false)
                }
            } catch (e: Exception) {
                registerResultLiveData.postValue(false)
            }
        }
    }
}

// Define la clase ResponseType según tu implementación
data class ResponseType(val id: String?)