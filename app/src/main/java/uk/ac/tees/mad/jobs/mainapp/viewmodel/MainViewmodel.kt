package uk.ac.tees.mad.jobs.mainapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.jobs.mainapp.model.JobInfo
import javax.inject.Inject


@HiltViewModel
class MainViewmodel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {

    private val _allJobs = MutableStateFlow<List<JobInfo>>(emptyList())
    val allJobs = _allJobs.asStateFlow()

    init {
        getAllJobs()
    }

    fun getAllJobs(){
        viewModelScope.launch {
            firestore.collection("jobs")
                .get()
                .addOnSuccessListener {
                    _allJobs.value = it.toObjects(JobInfo::class.java)
                    Log.i("Fetching all the jobs: ", "The data is $it")
                }
                .addOnFailureListener {
                    Log.i("Fetching all the jobs: ", "Fetching failed with error ${it.message}")
                }
        }
    }

    fun addNewJob(jobInfo: JobInfo){
        viewModelScope.launch {
            firestore.collection("jobs")
                .add(jobInfo)
                .addOnSuccessListener {
                    getAllJobs()
                    Log.i("Add New Job: ", "A new job is added successfully in the db")
                }
                .addOnFailureListener {
                    Log.i("Add New Job: ", "A new job is not added")
                }
        }
    }

    fun searchJobsByTitle(title: String) {
        viewModelScope.launch {
            firestore.collection("jobs")
                .whereGreaterThanOrEqualTo("title", title)
                .whereLessThanOrEqualTo("title", title + "\uf8ff")
                .get()
                .addOnSuccessListener { documents ->
                    _allJobs.value = documents.toObjects(JobInfo::class.java)
                    Log.i("Search Jobs: ", "Jobs found: ${documents.size()}")
                }
                .addOnFailureListener { exception ->
                    Log.i("Search Jobs: ", "Search failed with error ${exception.message}")
                }
        }
    }


}