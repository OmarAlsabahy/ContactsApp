package com.example.contactsapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Models.ContactModel
import com.example.contactsapp.databinding.ContactsCardBinding

class ContactsAdapter(var  contacts: List<ContactModel>, val onItemClicked:(contactModel: ContactModel)-> Unit): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ContactsCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(contacts[position])
        holder.binding.root.setOnClickListener {
            onItemClicked(contacts[position])
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(val binding: ContactsCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: ContactModel) {
            binding.contactName.text = model.name
            binding.contactPhoneNumber.text = model.phone
        }

    }
}