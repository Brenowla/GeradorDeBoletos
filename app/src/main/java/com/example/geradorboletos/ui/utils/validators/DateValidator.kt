package com.example.geradorboletos.ui.utils.validators

import com.example.geradorboletos.ui.utils.Mask
import java.text.SimpleDateFormat
import java.util.*

class DateValidator {
    companion object {
        fun remountDate(date: String):String {
            val newDate =  date.split("/")
            return "${newDate[2]}-${newDate[1]}-${newDate[0]}"
        }

        fun isValid(date: String): Boolean{
            val str = Mask.replaceChars(date)
            val sdf = SimpleDateFormat("ddMMyyyy")
            try {
                sdf.isLenient = false
                val strData : Date = sdf.parse(str)
                val after = Calendar.getInstance()
                after.add(Calendar.YEAR, 5)
                val strAfter: Date = sdf.parse(sdf.format(after.time))
                return Date().before(strData) && strAfter.after(strData)
            }catch (exception: Exception){
                return false
            }
        }
    }

}