package com.example.geradorboletos.ui.list_person

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.PersonData
import com.example.geradorboletos.repositories.database.PersonRepository
import javax.inject.Inject

class ListPersonViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel() {

   val allPerson: LiveData<List<PersonData>> = personRepository.getAllPerson()
   val allPersonUpdateLive: MutableLiveData<List<PersonData>> = MutableLiveData<List<PersonData>>()

   fun filterNames(search: Editable) {
      val search = search.toString()
      allPersonUpdateLive.value = allPerson.value?.filter { personData ->
         if(personData.person.juridicalPerson != null){
            personData.person.juridicalPerson.corporateName.toLowerCase().startsWith(search.toLowerCase())
         }else {
            personData.person.name != null && personData.person.name.toLowerCase()
               .startsWith(search.toLowerCase())
         }
      }
   }
}