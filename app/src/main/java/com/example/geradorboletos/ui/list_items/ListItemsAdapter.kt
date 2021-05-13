package com.example.geradorboletos.ui.list_items

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.example.geradorboletos.databinding.ItemListItemsBinding
import com.example.geradorboletos.models.Item
import javax.inject.Inject

class ListItemsAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<ListItemsAdapter.ViewHolder>(){

    var onItemClick: (item: Item) -> Unit = {}

    var listItem = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("Criando", "Entrou")
        val inflater = LayoutInflater.from(context)
        val viewDataBinding = ItemListItemsBinding.inflate(inflater, parent, false)
        return ViewHolder(viewDataBinding).also {
            viewDataBinding.lifecycleOwner = it
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun updateList(list: List<Item>){
        listItem = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.active()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.inactive()
    }

    inner class ViewHolder(val viewDataBinding: ItemListItemsBinding) : RecyclerView.ViewHolder(viewDataBinding.root), LifecycleOwner, View.OnClickListener {

        private lateinit var item: Item
        private val registry = LifecycleRegistry(this)

        init {
            viewDataBinding.onClick = this
            registry.currentState = Lifecycle.State.INITIALIZED
        }

        fun bind(item: Item) {
            this.item = item
            viewDataBinding.item = item
        }

        fun active() {
            registry.currentState = Lifecycle.State.STARTED
        }

        fun inactive(){
            registry.currentState = Lifecycle.State.DESTROYED
        }

        override fun onClick(v: View?) {
            if(::item.isInitialized){
                onItemClick(item)
            }
        }

        override fun getLifecycle(): Lifecycle {
            return registry
        }

    }
}