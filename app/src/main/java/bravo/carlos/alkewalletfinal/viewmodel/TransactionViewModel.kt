package bravo.carlos.alkewalletfinal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import bravo.carlos.alkewalletfinal.model.Transaction
import bravo.carlos.alkewalletfinal.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

// Propiedad de extensión para obtener los datos de una respuesta
private val Any.data: List<Transaction>?
    get() {
        // Implementar la lógica para extraer datos de la respuesta
        return (this as? Response<TransactionResponse>)?.body()?.data
    }

class TransactionResponse {

}

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    val transactionsLiveData = MutableLiveData<List<Transaction>>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun fetchTransactions() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = GlobalClassApp.tokenAccess
                if (token.isNullOrEmpty()) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val response = ApiClient.apiService.getTransactions("Bearer $token")
                if (response.isSuccessful) {
                    val transactions = response.data
                    Log.d("TransactionViewModel", "Transacciones obtenidas: $transactions")
                    transactionsLiveData.postValue(transactions ?: emptyList())
                } else {
                    Log.e("TransactionViewModel", "Error al obtener las transacciones")
                    errorMessageLiveData.postValue("Error al obtener las transacciones")
                }
            } catch (e: IOException) {
                Log.e("TransactionViewModel", "Error de red: ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("TransactionViewModel", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}
