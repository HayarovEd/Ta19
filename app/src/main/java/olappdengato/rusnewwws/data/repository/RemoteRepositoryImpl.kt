package olappdengato.rusnewwws.data.repository


import olappdengato.rusnewwws.data.mapper.mapToLoans
import olappdengato.rusnewwws.data.remote.ZaimApi
import olappdengato.rusnewwws.domain.model.Loan
import olappdengato.rusnewwws.domain.repository.RemoteRepository
import olappdengato.rusnewwws.domain.utils.Resource
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private  val api: ZaimApi): RemoteRepository {
    override suspend fun getRemoteData(): Resource<List<Loan>> {
        return try {
            val result = api.getData()
            Resource.Success(
                result.mapToLoans()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}