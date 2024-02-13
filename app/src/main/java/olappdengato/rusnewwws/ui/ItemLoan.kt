package olappdengato.rusnewwws.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import olappdengato.rusnewwws.R
import olappdengato.rusnewwws.domain.model.Loan
import olappdengato.rusnewwws.ui.theme.blue
import olappdengato.rusnewwws.ui.theme.white
import olappdengato.rusnewwws.ui.theme.yellow

@Composable
fun ItemLoan(
    modifier: Modifier = Modifier,
    loan: Loan
) {
    val openLink = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loan.url))
    Row(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp))
            .background(color = white)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier.width(120.dp),
            model = loan.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier = modifier.weight(1f),
        ) {
            Text(
                color = blue,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontSize = 18.sp,
                fontWeight = FontWeight(900),
                text = loan.sumOne
            )
            Spacer(modifier = modifier.height(2.dp))
            Text(
                color = blue,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontSize = 12.sp,
                fontWeight = FontWeight(900),
                text = "${stringResource(id = R.string.rate)} ${loan.percent}"
            )
            Spacer(modifier = modifier.height(6.dp))
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = yellow
                ),
                contentPadding  = PaddingValues(vertical = 6.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    openLink.launch(intent)
                }) {
                Text(
                    color = white,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    text = stringResource(id = R.string.take_money)
                )
            }
        }
    }
}