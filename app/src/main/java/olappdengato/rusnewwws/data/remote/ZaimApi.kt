package olappdengato.rusnewwws.data.remote

import retrofit2.http.GET

interface ZaimApi {
    @GET("db/api.json")
    suspend fun getData(): List<RemoteDto>
}