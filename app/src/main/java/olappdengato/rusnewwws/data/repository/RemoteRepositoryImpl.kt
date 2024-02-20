package olappdengato.rusnewwws.data.repository


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import olappdengato.rusnewwws.data.mapper.mapToLoans
import olappdengato.rusnewwws.data.remote.ZaimApi
import olappdengato.rusnewwws.domain.model.Dividend
import olappdengato.rusnewwws.domain.model.Loan
import olappdengato.rusnewwws.domain.repository.RemoteRepository
import olappdengato.rusnewwws.domain.utils.Resource
import org.jsoup.Jsoup
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun parsePage(): Resource<List<Dividend>> {
        return  try {
            val dividends = mutableListOf<Dividend>()
            val doc = Jsoup.connect("https://www.dohod.ru/ik/analytics/dividend/lkoh").get()

            val baseClass = doc.select("table")
            val linksTd = baseClass[2].select("td")
            for (i in 0..<linksTd.size step 4) {
                val dividend = mapToDividend(
                    dateDeclaration = linksTd[i].text(),
                    dateClose = linksTd[i+1].text(),
                    year = linksTd[i+2].text(),
                    value = linksTd[i+3].text()
                )
                //Log.d("test parse page", "number {$i}, dividend: ${dividend}")
                dividends.add(dividend)
            }
            Resource.Success(dividends)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun mapToDividend(
    dateDeclaration: String,
    dateClose: String,
    year: String,
    value: String
): Dividend {
    return Dividend(
        dateDeclaration = parseToLocalDate(dateDeclaration),
        dateClose = parseToLocalDate(dateClose),
        year = parseToInt(year),
        value = value.toDouble()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
private fun parseToLocalDate(
    dateForParse: String,
): LocalDate? {
    return try {
        val trimmed  = dateForParse.substring(0, 10)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        LocalDate.parse(trimmed, formatter)
    } catch (e:Exception) {
        null
    }
}

private fun parseToInt(
    value: String,
): Int? {
    return try {
        value.toInt()
    } catch (e:Exception) {
        null
    }
}