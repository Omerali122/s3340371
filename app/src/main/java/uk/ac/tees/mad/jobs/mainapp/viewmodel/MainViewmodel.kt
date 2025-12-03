package uk.ac.tees.mad.jobs.mainapp.viewmodel

import android.app.job.JobInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewmodel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {

    fun addNewJob(jobInfo: JobInfo){
        viewModelScope.launch {
            
        }
    }

}