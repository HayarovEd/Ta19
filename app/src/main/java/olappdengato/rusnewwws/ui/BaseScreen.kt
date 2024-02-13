package olappdengato.rusnewwws.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import olappdengato.rusnewwws.R
import olappdengato.rusnewwws.ui.theme.background
import olappdengato.rusnewwws.ui.theme.green


@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    if (state.value.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = background)
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .align(alignment = Alignment.Center)
                    .size(100.dp),
                color = green
            )
        }
    } else {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            containerColor = background,
            topBar = {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    color = green,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    text = stringResource(id = R.string.credit),
                    textAlign = TextAlign.Center
                )
            }
        ) { paddings ->
            LazyColumn(
                modifier = modifier
                    .padding(paddings)
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.value.loans) { loan ->
                    ItemLoan(loan = loan)
                }
            }
        }
    }
}