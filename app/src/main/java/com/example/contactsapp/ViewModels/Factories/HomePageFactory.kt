package com.example.contactsapp.ViewModels.Factories
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.Repositories.Interfaces.IHomeRepository
import com.example.contactsapp.ViewModels.HomePageViewModel

class HomePageFactory(private  val repository: IHomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomePageViewModel::class.java)){
            return HomePageViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}