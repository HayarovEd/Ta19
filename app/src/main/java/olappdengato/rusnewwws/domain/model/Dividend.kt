package olappdengato.rusnewwws.domain.model

import java.time.LocalDate

data class Dividend(
    val dateDeclaration: LocalDate?,
    val dateClose: LocalDate?,
    val year: Int?,
    val value: Double,
)
