package com.example.contactsapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.Interfaces.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomePageViewModel(private val repository: IHomeRepository): ViewModel() {
    private var _contacts = MutableLiveData<List<ContactModel>>()
    val contacts: LiveData<List<ContactModel>>
        get() = _contacts
    private var _searchedItems = MutableLiveData<List<ContactModel>>()
    val searchedItems: LiveData<List<ContactModel>>
        get() = _searchedItems
    fun getAllContacts(){
        val result = viewModelScope.async(Dispatchers.IO) {
            repository.getAllContacts()
        }
        viewModelScope.launch(Dispatchers.Main) {
            _contacts.value = result.await()
        }
    }

    fun search(query: String){
        val items = _contacts.value.filter {item->
            item.name!!.contains(query)
            item.phone!!.contains(query)
        }
        _searchedItems.value = items
    }

    fun deleteContact(contactModel: ContactModel){
        val result =viewModelScope.async(Dispatchers.IO) {
            repository.deleteContact(contactModel)
        }
        viewModelScope.launch(Dispatchers.Main) {
            result.await()
            getAllContacts()
        }


    }
}