package com.nexus.mvvmmessenger.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nexus.mvvmmessenger.model.MessageModel
import java.util.concurrent.Executors

@Database(entities = [MessageModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "Messages.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadExecutor().execute {
                            getInstance(context).messageDao().addMessages(PREPOPULATE_DATA)
                        }
                    }
                })
                .build()
        }


        val PREPOPULATE_DATA = listOf(
            MessageModel("2018-05-28T10:00:00.000Z", "OUTGOING", "Hello"),
            MessageModel("2018-05-29T11:05:00.000Z", "INCOMING", "Hi"),
            MessageModel("2018-06-01T22:00:00.000Z", "OUTGOING", "What is Mudah.my"),
            MessageModel(
                "2018-06-02T22:01:00.000Z",
                "INCOMING",
                "Mudah.my is the most favorite local app in Malaysia for searching, buying and selling pre-loved items. Get best deals at the reasonable prices from over 1,6 million items every day."
            ),
            MessageModel("2018-06-03T09:15:00.000Z", "OUTGOING", "I see, thanks!"),
            MessageModel("2018-06-04T08:50:00.000Z", "INCOMING", "No problem!")
        )
    }


}