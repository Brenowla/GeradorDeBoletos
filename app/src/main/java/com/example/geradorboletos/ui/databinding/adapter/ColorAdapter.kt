package com.example.geradorboletos.ui.databinding.adapter

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.example.geradorboletos.ui.utils.Layout

@BindingAdapter("colorChanger")
fun setLineColor(view: EditText, status: Boolean?){
    view.background.setTint(Layout.statusColor(status))
}