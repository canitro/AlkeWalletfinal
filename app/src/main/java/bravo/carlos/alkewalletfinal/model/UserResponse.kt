package bravo.carlos.alkewalletfinal.model

data class UserResponse (
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val roleId: Long = 2,
    val points: Long = 0
)
