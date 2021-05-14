package com.example.geradorboletos.ui.list_person


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.example.geradorboletos.databinding.PersonItemListPersonBinding
import com.example.geradorboletos.models.Person
import com.example.geradorboletos.models.PersonData
import javax.inject.Inject

class ListPersonAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<ListPersonAdapter.ViewHolder>(){

    var onItemClick: (person: Person) -> Unit = {}

    var listPerson = listOf<PersonData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = PersonItemListPersonBinding.inflate(inflater, parent, false)
        return ViewHolder(viewDataBinding).also {
            viewDataBinding.lifecycleOwner = it
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listPerson[position]?.let{ personData ->
            holder.bind(personData.person)
        }
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

    fun updateList(list: List<PersonData>){
        listPerson = list
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

    inner class ViewHolder(val viewDataBinding: PersonItemListPersonBinding) : RecyclerView.ViewHolder(viewDataBinding.root), LifecycleOwner, View.OnClickListener {

        private lateinit var person: Person
        private val registry = LifecycleRegistry(this)

        init {
            viewDataBinding.onClick = this
            registry.currentState = Lifecycle.State.INITIALIZED
        }

        fun bind(person: Person) {
            this.person = person
            viewDataBinding.person = person
            getInicials()
        }

        fun active() {
            registry.currentState = Lifecycle.State.STARTED
        }

        fun inactive(){
            registry.currentState = Lifecycle.State.DESTROYED
        }

        override fun onClick(v: View?) {
            if(::person.isInitialized){
                onItemClick(person)
            }
        }

        override fun getLifecycle(): Lifecycle {
            return registry
        }

        fun getInicials(){
            val str = if (person.name != null){
               person.name!!.split(" ")
            }
            else{
                person.juridicalPerson!!.corporateName.split(" ")
            }
            val inicials = str[0].get(0).toString() + str[str.size-1].get(0).toString()
            viewDataBinding.inicials = inicials
        }

    }
}