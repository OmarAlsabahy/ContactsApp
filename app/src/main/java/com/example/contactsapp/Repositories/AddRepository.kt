package com.example.contactsapp.Repositories

import com.example.contactsapp.Dao.ContactsDao
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.Interfaces.IAddRepository

class AddRepository(private val dao: ContactsDao): IAddRepository{
    override suspend fun addContact(contact: ContactModel) {
        dao.insertContact(contact)
    }

}