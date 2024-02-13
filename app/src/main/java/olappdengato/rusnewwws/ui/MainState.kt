package olappdengato.rusnewwws.ui

import olappdengato.rusnewwws.domain.model.Loan


data class MainState  (
    val loans: List<Loan> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
)

