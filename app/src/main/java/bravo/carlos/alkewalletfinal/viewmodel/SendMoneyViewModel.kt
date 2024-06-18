package bravo.carlos.alkewalletfinal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import bravo.carlos.alkewalletfinal.model.PaymentRequest
import bravo.carlos.alkewalletfinal.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

private fun <T, PaymentResponse> MutableLiveData<T>.postValue(paymentResponse: PaymentResponse?) {

}

class SendMoneyViewModel<PaymentResponse>(application: Application) : AndroidViewModel(application) {

    val paymentResultLiveData = MutableLiveData<PaymentResponse?>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun sendPayment(concept: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = GlobalClassApp.tokenAccess
                if (token.isNullOrEmpty()) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val paymentRequest = PaymentRequest(type = "payment", concept = concept, amount = amount)
                val response = ApiClient.apiService.sendPayment("Bearer $token", 2163, paymentRequest)
                if (response.isSuccessful) {
                    val paymentResponse = response.body()
                    paymentResultLiveData.postValue(paymentResponse)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("SendMoneyViewModel", "Error al enviar el pago: $errorBody")
                    errorMessageLiveData.postValue("Error al enviar el pago: $errorBody")
                }
            } catch (e: IOException) {
                Log.e("SendMoneyViewModel", "Error de red: ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("SendMoneyViewModel", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }