package com.example.contactsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.Database.AppDatabase
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.DetailRepository
import com.example.contactsapp.Repositories.Interfaces.IDetailsRepository
import com.example.contactsapp.ViewModels.DetailsViewModel
import com.example.contactsapp.ViewModels.Factories.DetailsViewModelFactory
import com.example.contactsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    lateinit var contactModel: ContactModel
    lateinit var database: AppDatabase
    lateinit var repository: IDetailsRepository
    lateinit var viewModelFactory: DetailsViewModelFactory
    lateinit var viewModel: DetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        contactModel = DetailsFragmentArgs.fromBundle(requireArguments()).contact
        database = AppDatabase.getInstance(requireContext())!!
        repository = DetailRepository(dao = database.contactDao())
        viewModelFactory = DetailsViewModelFactory(repository = repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contactName.text = contactModel.name
        binding.phoneNumber.text = contactModel.phone
        binding.delete.setOnClickListener {
            viewModel.deleteContact(contactModel)
            findNavController().popBackStack()
        }
    }
}