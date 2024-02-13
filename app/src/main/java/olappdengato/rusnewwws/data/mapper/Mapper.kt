package olappdengato.rusnewwws.data.mapper


import olappdengato.rusnewwws.domain.model.Loan
import olappdengato.rusnewwws.data.remote.RemoteDto


fun List<RemoteDto>.mapToLoans(): List<Loan> {
    return this.map {
        Loan(
            imageUrl = it.imageUrl,
            url = it.url,
            sumOne = it.sumOne,
            percent = it.percent,
            text = it.text?:""
        )
    }
}