package com.example.contactsapp.Repositories

import com.example.contactsapp.Dao.ContactsDao
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.Interfaces.IDetailsRepository

class DetailRepository(private val dao: ContactsDao): IDetailsRepository{
    override suspend fun deleteContact(contactModel: ContactModel) {
        dao.deleteContact(contactModel)
    }

}