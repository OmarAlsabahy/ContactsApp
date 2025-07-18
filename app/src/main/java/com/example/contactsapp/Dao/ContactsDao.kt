package com.example.contactsapp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.contactsapp.Models.ContactModel
@Dao
interface ContactsDao {
    @Query("select * from contacts")
    suspend fun getAllContacts(): List<ContactModel>
    @Insert
   suspend fun insertContact(contact: ContactModel)
    @Delete
    suspend fun deleteContact(contact: ContactModel)

}