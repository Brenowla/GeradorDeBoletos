package com.example.geradorboletos.ui.form_person

import android.view.View
import android.widget.AdapterView

class SpinnerStatesAdapter(private val listenerSpinner: ListenerSpinner) : AdapterView.OnItemSelectedListener{

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        listenerSpinner.onSelection(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}

interface ListenerSpinner {
    fun onSelection(position: Int)
}