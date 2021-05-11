package com.example.geradorboletos.ui.add_items

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.PopupMenu
import com.example.geradorboletos.databinding.ItemListItensAddBinding
import com.example.geradorboletos.models.Item

class ListItemsAdapter (private val context: Context,private val menuListener: MenuListener) :
    BaseAdapter() {

    private var list = listOf<Item>()

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =  ItemListItensAddBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        binding.item = list[position]
        binding.showMenu = myClickMenuListener(position, menuListener)
        return binding.root
    }

    val menu = object : View.OnClickListener {
        override fun onClick(v: View?) {

        }
    }

    fun changeList(list : List<Item>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class myClickMenuListener(private val position: Int, private val menuListener: MenuListener): View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        override fun onClick(v: View?) {
            menuListener.showMenu(v, this)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return menuListener.clickItem(position, item)
        }

    }

}
