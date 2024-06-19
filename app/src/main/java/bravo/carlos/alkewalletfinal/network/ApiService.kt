package bravo.carlos.alkewalletfinal.network

import bravo.carlos.alkewalletfinal.model.LoginRequest
import bravo.carlos.alkewalletfinal.model.LoginResponse
import bravo.carlos.alkewalletfinal.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @Headers("Content-Type: application/json")
    @POST("users")
    suspend fun register(@Body request: RegisterRequest): response

    class response {
        val id: Any = TODO()
    }
}


