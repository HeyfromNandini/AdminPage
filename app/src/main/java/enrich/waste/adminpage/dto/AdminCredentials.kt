package enrich.waste.adminpage.dto

data class Credentials(
    val username: String,
    val password: String,
) {
    constructor() : this("", "")
}