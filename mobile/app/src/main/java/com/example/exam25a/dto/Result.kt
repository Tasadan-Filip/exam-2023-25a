package com.example.exam25a.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("id")
    @Expose
    var id: Int = 0
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("text")
    @Expose
    var text: String = ""
        get() = field
        set(value) {
            field = value
        }
}