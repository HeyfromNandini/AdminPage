package enrich.waste.adminpage.dto

import android.nfc.Tag
import java.util.Locale
import java.util.concurrent.TimeUnit

data class WasteItem(
    val latitude: Double,
    val longitude: Double,
    val imagePath: String,
    val timeStamp: Long,
    val userEmail: String,
    val address: String,
    val tag: List<TagWithoutTips> = emptyList(),
) {
    constructor() : this(
        0.0,
        0.0,
        "",
        0,
        "",
        "",
        emptyList()
    )

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            address.lowercase(Locale.ROOT),
            tag.joinToString(separator = " ") { it.name.lowercase(Locale.ROOT) },
            getTimeAgo(timeStamp)
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}


fun getTimeAgo(timeInMillis: Long): String {
    val currentTime = System.currentTimeMillis()
    val elapsedTime = currentTime - timeInMillis

    val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime)
    val hours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
    val days = TimeUnit.MILLISECONDS.toDays(elapsedTime)
    val months = TimeUnit.MILLISECONDS.toDays(elapsedTime) / 30
    val years = TimeUnit.MILLISECONDS.toDays(elapsedTime) / 365

    return when {
        years >= 1 -> "$years years ago"
        months >= 1 -> "$months months ago"
        days >= 1 -> "$days days ago"
        hours >= 1 -> "$hours hours ago"
        else -> "$minutes minutes ago"
    }
}


data class TagWithoutTips(
    val name: String,
    val image: String,
) {
    constructor() : this("", "")

    fun mapWithTips() = Tag(
        name = name,
        image = image
    )
}



data class Tag(
    val name: String,
    val image: String,
    val tips: List<String> = emptyList()
) {
    constructor() : this("", "", emptyList())

    fun mapWithoutTips() = TagWithoutTips(
        name = name,
        image = image
    )
}

data class ProfileInfo(
    val name: String?,
    val email: String?,
    val phoneNumber: String?,
    val gender: String?,
    val organization: String?,
    val address: String?,
    val pointsEarned: Int,
    val pointsRedeemed: Int,
    val noOfTimesReported: Int = 0,
    val noOfTimesCollected: Int = 0,
    val noOfTimesActivity: Int = 0,
    val communities: List<String> = emptyList()
) {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        0,
        0,
        0,
        0,
        emptyList()
    )
}