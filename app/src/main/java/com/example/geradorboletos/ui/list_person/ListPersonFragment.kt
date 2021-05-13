package com.example.geradorboletos.ui.list_person

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.databinding.ListPersonFragmentBinding
import com.example.geradorboletos.models.Person
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class ListPersonFragment : Fragment() {

    private lateinit var binding: ListPersonFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    @Inject
    lateinit var listPersonAdapter: ListPersonAdapter

    private val viewModel by viewModels<ListPersonViewModel> { viewModelProvider }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelProvider }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListPersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        getAllperson()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.listPersonComponent().create().inject(this)
    }

    private fun getAllperson() {
        viewModel.allPerson.observe(viewLifecycleOwner, {
            listPersonAdapter.updateList(it)
        })
    }

    private fun configRecyclerView() {
        binding.listPersonList.run {
            adapter = listPersonAdapter
        }
        listPersonAdapter.onItemClick = {person ->
            toFormPerson(person)
        }
    }

    private fun toFormPerson(person: Person) {
        mainViewModel.costumer = person
        ListPersonFragmentDirections.actionListPersonFragmentToFormPersonFragment().run {
            controler.navigate(this)
        }
    }

}