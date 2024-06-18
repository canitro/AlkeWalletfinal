package bravo.carlos.alkewalletfinal.model

data class TransactionsResponse(
    val previousPage: Any?,
    val nextPage: Any?,
    val data: List<Transaction>?,
    val error: String?,
    val status: String?
)
