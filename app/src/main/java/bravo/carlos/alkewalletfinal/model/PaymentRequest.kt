package bravo.carlos.alkewalletfinal.model

data class PaymentRequest(
    val type: String,
    val concept: String,
    val amount: Double
)
