package com.example.contactsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.example.contactsapp.Adapters.ContactsAdapter
import com.example.contactsapp.Database.AppDatabase
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.Repositories.HomeRepository
import com.example.contactsapp.Repositories.Interfaces.IHomeRepository
import com.example.contactsapp.ViewModels.Factories.HomePageFactory
import com.example.contactsapp.ViewModels.HomePageViewModel
import com.example.contactsapp.databinding.FragmentHomePageBinding

class HomePage : Fragment() {
    lateinit var binding: FragmentHomePageBinding
    lateinit var database: AppDatabase
    lateinit var repository: IHomeRepository
    lateinit var viewModelFactory: HomePageFactory
    lateinit var viewModel: HomePageViewModel
    private var adapter: ContactsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)
        database = AppDatabase.getInstance(requireContext())!!
        repository = HomeRepository(dao = database.contactDao())
        viewModelFactory = HomePageFactory(repository = repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomePageViewModel::class.java)
        viewModel.getAllContacts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return  false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                if(adapter!=null){
                    val position = viewHolder.layoutPosition
                    val  contact = adapter?.contacts[position]
                    viewModel.deleteContact(contact!!)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.contactsRecyclerView)

        viewModel.contacts.observe(viewLifecycleOwner) {contacts->
            if (contacts.isEmpty()){
                binding.contactsRecyclerView.visibility = View.GONE
                binding.box.visibility = View.VISIBLE
                binding.txtNoContacts.visibility = View.VISIBLE
            }else{
                binding.contactsRecyclerView.visibility = View.VISIBLE
                binding.box.visibility = View.GONE
                binding.txtNoContacts.visibility = View.GONE
                adapter = ContactsAdapter(contacts){contact->
                    navigateToDetailsFragment(contact)
                }
                binding.contactsRecyclerView.adapter = adapter
            }
        }

        viewModel.searchedItems.observe(viewLifecycleOwner) {searchedItems->
            if(searchedItems.isNotEmpty()){
                adapter = ContactsAdapter(contacts = searchedItems){contact->
                    navigateToDetailsFragment(contact)
                }
                binding.contactsRecyclerView.adapter = adapter
            }
        }

        binding.btnAdd.setOnClickListener {
            navigateToAddFragment()
        }

        binding.searchView.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus){
                    binding.txtContacts.visibility= View.GONE
                }else{
                    binding.txtContacts.visibility= View.VISIBLE
                }
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    viewModel.search(query)
                }
                return  true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null){
                    viewModel.search(newText)
                }
                return  true
            }
        })


    }

    private fun navigateToDetailsFragment(model: ContactModel) {
        findNavController().navigate(HomePageDirections.actionHomePageToDetailsFragment(contact = model))
    }

    private fun navigateToAddFragment() {
        findNavController().navigate(HomePageDirections.actionHomePageToAddFragment())
    }

    override fun onResume() {
        super.onResume()
        if (adapter!=null){
            adapter?.notifyDataSetChanged()
        }
    }
}