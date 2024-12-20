package uk.ac.tees.mad.jobs.ui.mainapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                        text = "Jobs&Jobs",
                        fontSize = 25.sp,
                        fontFamily = poppinsFamily
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

            SearchBar(mainViewmodel)
            LazyColumn (
                modifier = modifier.padding(vertical = 10.dp)
            ){
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

    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        onClick = {
            val jobItem = Gson().toJson(job)
            navController.navigate("job_details/$jobItem")
        },
        border = BorderStroke(1.dp, color = Color.Black),
        elevation = CardDefaults.elevatedCardElevation(15.dp)
    ) {
        Column(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ){
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = job.title,
                fontSize = 22.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = job.description,
                fontSize = 18.sp,
                fontFamily = poppinsFamily
            )

            if (job.location.isNotEmpty()) {
                Text(
                    modifier = modifier
                        .padding(horizontal = 10.dp),
                    text = "Location: ${job.location}",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            }

            Text(
                modifier = modifier
                    .padding(horizontal = 10.dp),
                text = "Salary: ${job.maxBudget} LPA",
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

            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, "Applied to the ${job.title}", Toast.LENGTH_SHORT).show()
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
}

@Composable
fun SearchBar(mainViewmodel: MainViewmodel) {
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            mainViewmodel.searchJobsByTitle(searchQuery)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(0.9f),
            label = { Text("Search jobs by title") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        searchQuery = ""
                        mainViewmodel.getAllJobs()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            mainViewmodel.searchJobsByTitle(searchQuery)
        }) {
            Text(
                text = "Search",
                fontFamily = poppinsFamily,
                fontSize = 16.sp
            )
        }
    }
}
