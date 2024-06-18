package bravo.carlos.alkewalletfinal.network

import bravo.carlos.alkewalletfinal.model.LoginRequest
import bravo.carlos.alkewalletfinal.model.LoginResponse
import bravo.carlos.alkewalletfinal.model.RegisterRequest
import bravo.carlos.alkewalletfinal.model.PaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Header

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @Headers("Content-Type: application/json")
    @POST("users")
    suspend fun register(@Body request: RegisterRequest): Response<YourResponseType>

    @Headers("Content-Type: application/json")
    @POST("payments")
    suspend fun sendPayment(
        @Header("Authorization") token: String,
        @Body paymentRequest: Unit,
        paymentRequest1: Unit
    ): Response<PaymentResponse>

    abstract fun sendPaymen(s: String, accountId: Int, paymentRequest: Unit): Any
    abstract fun <AccountRequest> createAccount(s: String, accountRequest: AccountRequest): Any
    abstract fun getTransactions(s: String): Any
}

class YourResponseType {

    val id: Any = TODO()
}
