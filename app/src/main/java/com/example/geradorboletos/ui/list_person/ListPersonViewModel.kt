package com.example.geradorboletos.ui.list_person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.PersonData
import com.example.geradorboletos.repositories.database.PersonRepository
import javax.inject.Inject

class ListPersonViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel() {

   val allPerson: LiveData<List<PersonData>> = personRepository.getAllPerson()

}