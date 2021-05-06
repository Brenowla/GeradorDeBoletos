package com.example.geradorboletos.repositories.database

import com.example.geradorboletos.database.dao.PersonDAO
import com.example.geradorboletos.models.PersonData
import javax.inject.Inject

class PersonRepository @Inject constructor(private val personDAO: PersonDAO) {

    fun savePerson(person: PersonData){
        personDAO.save(person)
    }

}