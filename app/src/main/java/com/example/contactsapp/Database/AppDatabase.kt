package com.example.contactsapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.Dao.ContactsDao
import com.example.contactsapp.Models.ContactModel

@Database(entities = [ContactModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun contactDao(): ContactsDao
    companion object{
        private  var database:AppDatabase? = null
        fun getInstance(context:Context): AppDatabase? {
            if (database==null){
                synchronized(this){
                    database = Room.databaseBuilder(
                        context = context,
                        klass = AppDatabase::class.java,
                        name = "contacts_db"
                    ).build()
                }

            }
            return database;
        }
    }

}