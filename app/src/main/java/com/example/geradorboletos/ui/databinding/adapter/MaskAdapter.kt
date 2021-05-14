package com.example.geradorboletos.ui.databinding.adapter

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.geradorboletos.ui.utils.Mask
import com.example.geradorboletos.ui.utils.MoneyMask

@BindingAdapter(value =["maskInput", "valueField"] , requireAll = true)
fun setMaskedText(view: TextView, mask: String, value: String){
    view.text = Mask.imediateMask(mask,value)
}

@BindingAdapter(value =["moneyMask", "valueField"], requireAll = true)
fun setMoneyMaskedText(view: TextView, mask: String, value: String){
    view.text = MoneyMask.imediateMask(mask,value)
}

@BindingAdapter("moneyMask")
fun setMoneyMaskedEditText(view: EditText, mask: String) {
    view.addTextChangedListener(MoneyMask.mask(mask,view))
}

@BindingAdapter("maskInput")
fun setMaskedEditText(view: EditText, mask: String) {
    view.addTextChangedListener(Mask.mask(mask,view))
}
