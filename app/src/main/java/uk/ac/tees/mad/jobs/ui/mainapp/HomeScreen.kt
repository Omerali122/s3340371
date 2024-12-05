package uk.ac.tees.mad.jobs.ui.mainapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uk.ac.tees.mad.jobs.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.jobs.mainapp.model.JobInfo
import uk.ac.tees.mad.jobs.mainapp.viewmodel.MainViewmodel
import uk.ac.tees.mad.jobs.ui.theme.poppinsFamily

@Composable
fun HomeScreen(
    navController: NavHostController,
    mainViewmodel: MainViewmodel,
    modifier: Modifier = Modifier
) {

    val allJobs by mainViewmodel.allJobs.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        LazyColumn {
            items(allJobs){job->
                JobDetails(job)
            }
        }
    }
}

@Composable
fun JobDetails(
    job: JobInfo,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {

        }
    ) {
        Text(
            text = job.title,
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )

        Text(
            text = job.description,
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )

        Text(
            text = job.location,
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
    }
}