package olappdengato.rusnewwws.domain.repository

import olappdengato.rusnewwws.domain.model.Loan
import olappdengato.rusnewwws.domain.utils.Resource


interface RemoteRepository {
    suspend fun getRemoteData(): Resource<List<Loan>>
}