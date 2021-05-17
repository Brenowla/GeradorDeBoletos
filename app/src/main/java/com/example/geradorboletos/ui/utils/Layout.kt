package com.example.geradorboletos.ui.utils

import android.graphics.Color

class Layout {

    companion object {
        private val BASE = Color.parseColor("#95989A")
        private val ERROR = Color.parseColor("#B80610")
        private val SUCESS = Color.parseColor("#019616")
        fun statusColor(status: Boolean?): Int {
            if(status == null){
                return BASE
            }else if(status == false) {
                return ERROR
            }else {
                return SUCESS
            }
        }
    }

}