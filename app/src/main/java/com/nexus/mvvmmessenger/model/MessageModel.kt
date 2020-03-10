package com.nexus.mvvmmessenger.model

import androidx.room.*


@Entity(tableName = "message")
open class MessageModel(
    @ColumnInfo(name = "timestamp") var timestamp: String?,
    @ColumnInfo(name = "direction") var direction: String?,
    @ColumnInfo(name = "message") var message: String?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0


}