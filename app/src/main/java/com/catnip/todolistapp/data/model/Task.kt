package com.catnip.todolistapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

@Parcelize
data class Task(
    var id : Int,
    var title : String,
    var desc : String,
    var imgHeaderUrl : String,
    var isTaskCompleted : Boolean,
) : Parcelable
