package bravo.carlos.alkewalletfinal.model

data class PaymentRequest(
    val amount: Double,
    val currency: String,
    val recipient: String,
    val type: String,
    val concept: String
)
