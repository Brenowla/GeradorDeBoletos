package com.example.geradorboletos.ui.list_items

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.databinding.ListItemsFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class ListItemsFragment : Fragment() {

    private lateinit var binding: ListItemsFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var listItemsAdapter: ListItemsAdapter

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private val viewModel by viewModels<ListItemsViewModel> { viewModelProvider }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelProvider }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.listItemComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListItemsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecycler()
        getAllItems()
    }

    private fun getAllItems() {
        viewModel.allItems.observe(viewLifecycleOwner, Observer {
            listItemsAdapter.updateList(it)
        })
    }

    private fun configRecycler() {
        binding.listItemsList.run {
            adapter = listItemsAdapter
        }
        listItemsAdapter.onItemClick = {
            mainViewModel.items.add(it)
            ListItemsFragmentDirections.actionListItemsFragmentToAddItemsFragment().run {
                controler.navigate(this)
            }
        }
    }

}