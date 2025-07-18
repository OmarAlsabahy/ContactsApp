package com.example.contactsapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.Interfaces.IDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: IDetailsRepository): ViewModel() {
    fun deleteContact(contactModel: ContactModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contactModel = contactModel)
        }
    }
}