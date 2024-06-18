package bravo.carlos.alkewalletfinal.model

data class LoginResponse(
    val id: String?,
    val accessToken: String?,
    val error: String?,
    val status: Int?
)
