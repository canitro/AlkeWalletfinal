package bravo.carlos.alkewalletfinal.model

data class PaymentResponse(
    val message: String?,
    val error: String?,
    val status: Int?
)
