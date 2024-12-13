package uk.ac.tees.mad.jobs.ui.mainapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = job.title,
            fontSize = 25.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = job.location,
            fontSize = 18.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = job.description,
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )

        Text(
            text = job.maxBudget,
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )

        Text(
            text = job.requiredSkills,
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Apply Now!!",
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
        }
    }
}