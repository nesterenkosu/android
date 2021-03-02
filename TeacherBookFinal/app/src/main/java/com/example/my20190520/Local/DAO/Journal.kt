package com.example.my20190520.Local.DAO

import  android.arch.persistence.room.Dao
import  android.arch.persistence.room.Query
import  android.arch.persistence.room.Insert
import  android.arch.persistence.room.Delete

import com.example.my20190520.Local.Entity.Journal
import com.example.my20190520.Local.Entity.JournalView

@Dao
interface JournalDao {
   // @Query("SELECT Journal.uid As uid, Journal.StudentID As StudentID, Journal.LabID As LabID, Journal.NotSynchronized As NotSynchronized, Lab.Name As Name FROM Journal INNER JOIN Lab ON Journal.LabID<>Lab.uid")
   // fun getAll(): List<JournalView>
   @Query("SELECT Lab.uid As uid, Lab.Name As Name FROM Lab Where uid not in(SELECT LabID From Journal)")
   fun getAll(): List<JournalView>
    /*@Query("INSERT INTO Journal(StudentID,LabID) VALUES (':StudentID',':LabID')")
    fun acceptLab(StudentID: Int,LabID:Int): List<Journal>*/

    @Insert
    fun insertAll(vararg users: Journal)

    @Query("DELETE FROM Journal WHERE 1")
    fun deleteAll()
}