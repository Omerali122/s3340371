package uk.ac.tees.mad.jobs.mainapp.model

data class JobInfo(
    val title: String = "",
    val description: String = "",
    val requiredSkills: String = "",
    val maxBudget: String = "",
    val location: String = ""
)
