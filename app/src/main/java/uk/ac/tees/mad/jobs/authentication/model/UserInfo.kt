package uk.ac.tees.mad.jobs.authentication.model

data class UserInfo(
    val name: String = "",
    val email: String = "",
    val highestQualification: String = "",
    val profilePictureUrl: String = ""
)
