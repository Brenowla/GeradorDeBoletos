package com.example.geradorboletos.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Item(
    @PrimaryKey
    val name: String = "",
    val value: Int = 0,
    val amount: Int = 1
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(value)
        parcel.writeInt(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}