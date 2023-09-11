package enrich.waste.adminpage.utils

sealed class Constants(val value: String)  {
    object AdminCredentials: Constants("waste2wealth-admin")
}