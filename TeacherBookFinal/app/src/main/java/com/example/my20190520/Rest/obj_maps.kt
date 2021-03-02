package com.example.my20190520.Rest

data class GroupRepo(val ID: Int, val Name: String)
data class JournalRepo(val ID: Int, val StudentID: Int, val LabID: Int,val Name: String)
data class LabRepo(val ID: Int, val Name: String)
data class StudentRepo(val ID: Int, val GroupID: Int, val Name: String)
data class SubjectRepo(val ID: Int, val Name: String)
data class SubjectGroupRepo(val ID: Int, val SubjectID: Int,val GroupID: Int)
