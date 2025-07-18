package com.example.contactsapp.Repositories.Interfaces

import com.example.contactsapp.Models.ContactModel

interface IHomeRepository {
   suspend fun getAllContacts(): List<ContactModel>
   suspend fun deleteContact(contactModel: ContactModel)
}