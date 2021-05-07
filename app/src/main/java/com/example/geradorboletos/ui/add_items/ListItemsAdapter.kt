package com.example.geradorboletos.ui.add_items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.geradorboletos.databinding.ItemListItensAddBinding
import com.example.geradorboletos.models.Item

class ListItemsAdapter (private val context: Context) :
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
        return binding.root
    }

    fun changeList(list : List<Item>){
        this.list = list
        notifyDataSetChanged()
    }

}