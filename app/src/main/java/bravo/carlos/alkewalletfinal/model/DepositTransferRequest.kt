package bravo.carlos.alkewalletfinal.model

data class DepositTransferRequest (
    val type : String,
    val concept : String,
    val amonut : Double
)
