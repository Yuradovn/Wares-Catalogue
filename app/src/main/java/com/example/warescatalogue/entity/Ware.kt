package com.example.warescatalogue.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Ware(
    var name: String = "",
    var description: String = "",
    var price: Int = 0,
): Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}