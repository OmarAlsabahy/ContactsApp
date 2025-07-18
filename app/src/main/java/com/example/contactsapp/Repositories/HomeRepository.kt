package com.example.contactsapp.Repositories

import com.example.contactsapp.Dao.ContactsDao
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.Interfaces.IHomeRepository

class HomeRepository(private val  dao: ContactsDao): IHomeRepository {
    override suspend fun getAllContacts(): List<ContactModel> {
        return  dao.getAllContacts()
    }

    override suspend fun deleteContact(contactModel: ContactModel) {
        dao.deleteContact(contactModel)
    }


}