package uk.ac.tees.mad.jobs.ui.mainapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.jobs.mainapp.model.JobInfo
import uk.ac.tees.mad.jobs.ui.theme.poppinsFamily


@Composable
fun JobDetailsScreen(
    job: JobInfo,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(50.dp))
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        Spacer(modifier.height(100.dp))
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            border = BorderStroke(1.dp, Color.Black),
            elevation = CardDefaults.elevatedCardElevation(15.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(15.dp),
                text = job.title,
                fontSize = 25.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold
            )
        }

        if (job.location.isNotEmpty()){
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.elevatedCardElevation(15.dp)
            ) {
                Text(
                    modifier = modifier
                        .padding(15.dp),
                    text = job.location,
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }


        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            border = BorderStroke(1.dp, Color.Black),
            elevation = CardDefaults.elevatedCardElevation(15.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(15.dp),
                text = job.description,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
        }


        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            border = BorderStroke(1.dp, Color.Black),
            elevation = CardDefaults.elevatedCardElevation(15.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(15.dp),
                text = job.maxBudget,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
        }

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            border = BorderStroke(1.dp, Color.Black),
            elevation = CardDefaults.elevatedCardElevation(15.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(15.dp),
                text = job.requiredSkills,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
        }

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(
                modifier = modifier
                    .padding(15.dp),
                text = "Apply Now!!",
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
        }
    }
}