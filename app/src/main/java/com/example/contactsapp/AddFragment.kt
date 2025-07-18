package com.example.contactsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.Database.AppDatabase
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.AddRepository
import com.example.contactsapp.Repositories.Interfaces.IAddRepository
import com.example.contactsapp.ViewModels.AddViewModel
import com.example.contactsapp.ViewModels.Factories.AddViewModelFactory
import com.example.contactsapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    lateinit var database: AppDatabase
    lateinit var repository: IAddRepository
    lateinit var viewModelFactory: AddViewModelFactory
    lateinit var viewModel: AddViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        database = AppDatabase.getInstance(requireContext())!!
        repository = AddRepository(dao = database.contactDao())
        viewModelFactory = AddViewModelFactory(repository = repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(AddViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.isValidated.observe (viewLifecycleOwner){isValidated->
            if (isValidated){
                val contact = ContactModel(
                    name = binding.nameField.text.toString(),
                    phone = binding.phoneNumberField.text.toString()
                )
                viewModel.addContact(contact)
                findNavController().popBackStack()
            }else{
                Toast.makeText(requireContext(),"Please fill all fields",Toast.LENGTH_SHORT).show()
            }
        }

        binding.checkIcon.setOnClickListener {
            viewModel.validate(
                name = binding.nameField.text.toString(),
                phoneNumber = binding.phoneNumberField.text.toString()
            )
        }
    }
}