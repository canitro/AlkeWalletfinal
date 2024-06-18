package bravo.carlos.alkewalletfinal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import bravo.carlos.alkewalletfinal.model.PaymentResponse
import bravo.carlos.alkewalletfinal.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class RequestMoneyViewModel(application: Application) : AndroidViewModel(application) {

    val topupResultLiveData = MutableLiveData<PaymentResponse?>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun requestMoney(accountId: Int, concept: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = GlobalClassApp.tokenAccess
                if (token.isNullOrEmpty()) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val paymentRequest =
                    PaymentRequest(type = "topup", concept = concept, amount = amount)
                val response =
                    ApiClient.apiService.sendPaymen("Bearer $token", accountId, paymentRequest)
                if (response.isSuccessful) {
                    val paymentResponse = response.body()
                    topupResultLiveData.postValue(paymentResponse)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("RequestMoneyViewModel", "Error al realizar el depósito: $errorBody")
                    errorMessageLiveData.postValue("Error al realizar el depósito: $errorBody")
                }
            } catch (e: IOException) {
                Log.e("RequestMoneyViewModel", "Error de red: ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("RequestMoneyViewModel", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }

    private fun PaymentRequest(type: String, concept: String, amount: Double) {


    }
}
