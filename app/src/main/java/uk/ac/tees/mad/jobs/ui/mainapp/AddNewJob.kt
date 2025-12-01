package uk.ac.tees.mad.jobs.ui.mainapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import uk.ac.tees.mad.jobs.ui.theme.JobsNJobsTheme
import uk.ac.tees.mad.jobs.ui.theme.poppinsFamily
import java.util.Locale

@Composable
fun AddNewJob(
    modifier: Modifier = Modifier
) {

    var title by remember { mutableStateOf("") }

    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var hasLocationPermission by remember { mutableStateOf(false) }
    var employerLocation by remember { mutableStateOf("") }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted
        if (isGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        } else {
            Toast.makeText(context, "Location permission denied.", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            hasLocationPermission = true
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    if (!addresses.isNullOrEmpty()){
                        val address = addresses[0]
                        employerLocation = address.locality+", "+address.adminArea
                    }else{
                        Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Spacer(modifier = modifier.weight(1f))

        Text(
            text = "Add New Job Posting!!",
            fontSize = 22.sp,
            fontFamily = poppinsFamily
        )

        Spacer(modifier = modifier.weight(1f))

        Text(
            modifier = modifier
                .fillMaxWidth(0.8f),
            text = "Title",
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(0.85f),
            value = title,
            onValueChange = {
                title = it
            },
            placeholder = {
                Text(
                    text = "Enter the title of the Job",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            },
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = modifier.weight(1f))

        Text(
            modifier = modifier
                .fillMaxWidth(0.8f),
            text = "Description",
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(0.85f),
            value = title,
            onValueChange = {
                title = it
            },
            placeholder = {
                Text(
                    text = "Enter the Description of the Job",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            },
            shape = RoundedCornerShape(15.dp),
            maxLines = 5
        )

        Spacer(modifier = modifier.weight(1f))

        Text(
            modifier = modifier
                .fillMaxWidth(0.8f),
            text = "Required Skillset",
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(0.85f),
            value = title,
            onValueChange = {
                title = it
            },
            placeholder = {
                Text(
                    text = "Enter the skill",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            },
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = modifier.weight(1f))

        Text(
            modifier = modifier
                .fillMaxWidth(0.8f),
            text = "Max. Budget",
            fontSize = 18.sp,
            fontFamily = poppinsFamily
        )
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(0.85f),
            value = title,
            onValueChange = {
                title = it
            },
            placeholder = {
                Text(
                    text = "Budget",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily
                )
            },
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = modifier.weight(1f))

        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = employerLocation,
            fontSize = 17.sp,
            fontFamily = poppinsFamily,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.weight(10f))

    }
}

@PreviewLightDark
@Composable
fun AddNewJobPreview(){
    JobsNJobsTheme {
        AddNewJob()
    }
}