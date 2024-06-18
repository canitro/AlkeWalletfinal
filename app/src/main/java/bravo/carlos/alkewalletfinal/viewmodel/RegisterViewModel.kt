package bravo.carlos.alkewalletfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bravo.carlos.alkewalletfinal.model.RegisterRequest
import bravo.carlos.alkewalletfinal.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val Any.id: Any
    get() {}

class RegisterViewModel : ViewModel() {

    // variable que almacena el resultado del registro
    val registerResultLiveData = MutableLiveData<Boolean>()

    // funcion para registrar un usuario
    fun register(firstName: String, lastName: String, email: String, password: String) {
        // corrutina para enviar la solicitud
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // enviar la solicitud utilizando ApiClient
                val response = ApiClient.apiService.register(
                    RegisterRequest(
                        first_name = firstName,
                        last_name = lastName,
                        email = email,
                        password = password
                    )
                )
                // verificar el estado de la respuesta
                val response: ResponseType = getResponse() // getResponse()
                if (response.id != null) {
                    registerResultLiveData.postValue(true)
                } else {
                    registerResultLiveData.postValue(false)
                }
            } catch (e: Exception) {
                registerResultLiveData.postValue(false)
            }
        }
    }

    private fun getResponse(): ResponseType {

    }


    class ResponseType {

    }
}