package olappdengato.rusnewwws.data.remote


import com.google.gson.annotations.SerializedName

data class RemoteDto(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("percent")
    val percent: String,
    @SerializedName("sum_one")
    val sumOne: String,
    @SerializedName("text")
    val text: String?,
    @SerializedName("url")
    val url: String
)