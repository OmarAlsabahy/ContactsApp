package com.example.contactsapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.Interfaces.IAddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val repository: IAddRepository): ViewModel() {
    private val  _isValidated = MutableLiveData<Boolean>()
    val isValidated : LiveData<Boolean>
        get() = _isValidated
    fun addContact(contact: ContactModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContact(contact)
        }
    }

    fun validate(name: String, phoneNumber: String){
        if (name.isNotEmpty() && phoneNumber.isNotEmpty()){
            _isValidated.value = true
        }else{
            _isValidated.value = false
        }
    }
}