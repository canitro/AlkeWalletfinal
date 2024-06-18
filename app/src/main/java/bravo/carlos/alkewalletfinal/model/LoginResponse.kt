package bravo.carlos.alkewalletfinal.model

data class LoginResponse(
    val accessToken: String?,
    val error: String?,
    val status: Int?
)
