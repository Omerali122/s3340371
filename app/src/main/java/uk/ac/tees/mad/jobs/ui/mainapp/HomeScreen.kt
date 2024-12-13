package uk.ac.tees.mad.jobs.ui.mainapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.gson.Gson
import uk.ac.tees.mad.jobs.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.jobs.mainapp.model.JobInfo
import uk.ac.tees.mad.jobs.mainapp.viewmodel.MainViewmodel
import uk.ac.tees.mad.jobs.ui.theme.metamorphousFamily
import uk.ac.tees.mad.jobs.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    mainViewmodel: MainViewmodel,
    authViewmodel: AuthViewmodel,
    modifier: Modifier = Modifier
) {

    val allJobs by mainViewmodel.allJobs.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Be Prepared",
                        fontSize = 25.sp,
                        fontFamily = metamorphousFamily
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            authViewmodel.fetchCurrentUser()
                            navController.navigate("profile_screen")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Profile Screen"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navController.navigate("add_new_job")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new Job"
                )
            }
        }
    ){innerpadding->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LazyColumn {
                items(allJobs){job->
                    JobDetails(job, navController)
                }
            }
        }
    }
}

@Composable
fun JobDetails(
    job: JobInfo,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ){
        Card(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                val jobItem = Gson().toJson(job)
                navController.navigate("job_details/$jobItem")
            },
            border = BorderStroke(1.dp, color = Color.Black),
            elevation = CardDefaults.elevatedCardElevation(15.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = job.title,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = job.description,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = job.location,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = "${job.maxBudget} LPA",
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = job.requiredSkills,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )
        }
    }
}