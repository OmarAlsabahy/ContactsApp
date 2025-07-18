package com.example.contactsapp.ViewModels.Factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.Repositories.Interfaces.IAddRepository
import com.example.contactsapp.ViewModels.AddViewModel

class AddViewModelFactory(private val repository: IAddRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}