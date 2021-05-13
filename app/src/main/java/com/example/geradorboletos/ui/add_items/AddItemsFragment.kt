package com.example.geradorboletos.ui.add_items

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.AddItemsFragmentBinding
import com.example.geradorboletos.databinding.DeleteItemDialogBinding
import com.example.geradorboletos.databinding.FormItemBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class AddItemsFragment() : Fragment(){

    lateinit var binding: AddItemsFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AddItemsViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelFactory }

    val adapter by lazy {
        ListItemsAdapter(requireContext(), menuListener)
    }

    private val menuListener = object : MenuListener {
        override fun showMenu(v: View?, onClickListener: PopupMenu.OnMenuItemClickListener) {
            val popupMenu = PopupMenu(context, v).apply {
                setOnMenuItemClickListener(onClickListener)
                inflate(R.menu.item_menu)
                show()
            }
        }

        override fun clickItem(position: Int, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.item_menu_edit -> {
                    viewModel.setEditableItem(position)
                    dialogFormItens(type = 1, position)
                    return true
                }
                R.id.item_menu_delete -> {
                    dialogRemoveItem(position)
                    return true
                }
                else -> return false
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.addItemsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddItemsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addItemListener = View.OnClickListener { dialogFormItens(type = 0, -1) }
        binding.toAditionalItems = View.OnClickListener { toAditionalInformation() }
        binding.backToFormPerson = View.OnClickListener { backToFormPerson() }
        binding.toListItems = View.OnClickListener { toListItems() }
        binding.addItemsLista.adapter = adapter

        viewModel.updateList(mainViewModel.items)

        viewModel.listItemsData.observe(viewLifecycleOwner, Observer {
            adapter.changeList(it)
        })

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun toAditionalInformation() {
        mainViewModel.items = viewModel.getLista()
        AddItemsFragmentDirections.actionAddItemsFragmentToAditionalInformationFragment().run {
            controler.navigate(this)
        }

    }

    private fun toListItems() {
        mainViewModel.items = viewModel.getLista()
        AddItemsFragmentDirections.actionAddItemsFragmentToListItemsFragment().run {
            controler.navigate(this)
        }
    }

    private fun backToFormPerson() {
        mainViewModel.items = viewModel.getLista()
        AddItemsFragmentDirections.actionAddItemsFragmentToFormPersonFragment().run {
            controler.navigate(this)
        }
    }

    private fun dialogFormItens(type: Int, position: Int) {
        val dialogBinding: FormItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.form_item, null, false)
        dialogBinding.viewModel = viewModel
        if (type == 0){
            dialogBinding.title = getString(R.string.add_item)
            dialogBinding.buttonText = getString(R.string.adicionar)
        }else if(type == 1) {
            dialogBinding.title = getString(R.string.atualizar_item)
            dialogBinding.buttonText = getString(R.string.atualizar)
        }
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogBinding.root)
        val dialog: AlertDialog = builder.create()

        if(type == 0){
            dialogBinding.action = View.OnClickListener {
                viewModel.addItem()
                dialog.dismiss()
            }
        }else if (type == 1){
            dialogBinding.action = View.OnClickListener {
                viewModel.edit(position)
                dialog.dismiss()
            }
        }
        dialogBinding.dismiss = View.OnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun dialogRemoveItem(position: Int){
        val dialogBinding: DeleteItemDialogBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.delete_item_dialog, null, false)
        dialogBinding.nameitem = viewModel.getItemName(position)

        val builder = AlertDialog.Builder(context)
        builder.setView(dialogBinding.root)
        val dialog: AlertDialog = builder.create()

        dialogBinding.action = View.OnClickListener {
            viewModel.delete(position)
            dialog.dismiss()
        }
        dialogBinding.dismiss = View.OnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}