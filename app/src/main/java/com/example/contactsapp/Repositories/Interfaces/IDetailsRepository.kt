package com.example.contactsapp.Repositories.Interfaces

import com.example.contactsapp.Models.ContactModel

interface IDetailsRepository {
   suspend fun deleteContact(contactModel: ContactModel)
}