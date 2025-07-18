package com.example.contactsapp.Repositories.Interfaces

import com.example.contactsapp.Models.ContactModel

interface IAddRepository {
    suspend fun addContact(contact: ContactModel)
}