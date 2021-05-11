package com.example.geradorboletos.ui.add_items

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

interface MenuListener {

    fun showMenu(v: View?, onMenuItem: PopupMenu.OnMenuItemClickListener)

    fun clickItem(position: Int, item: MenuItem?) : Boolean
}