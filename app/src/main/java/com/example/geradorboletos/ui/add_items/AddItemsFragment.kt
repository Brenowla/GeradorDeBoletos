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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.AddItemsFragmentBinding
import com.example.geradorboletos.databinding.DeleteItemDialogBinding
import com.example.geradorboletos.databinding.FormItemBinding
import javax.inject.Inject

class AddItemsFragment : Fragment(){

    lateinit var binding: AddItemsFragmentBinding

    private val controler by lazy {
        findNavController()
    }
    private val arguments by navArgs<AddItemsFragmentArgs>()
    private val person by lazy {
        arguments.person
    }

    @Inject
    lateinit var viewModel: AddItemsViewModel

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GeradorBoletosApp.appComponent.addItemsComponent().create().inject(this)
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
        binding.addItemsLista.adapter = adapter

        viewModel.listItemsData.observe(viewLifecycleOwner, Observer {
            adapter.changeList(it)
        })

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun toAditionalInformation() {
        AddItemsFragmentDirections.actionAddItemsFragmentToAditionalInformationFragment(
            person,
            viewModel.getLista().toTypedArray()
        ).run {
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