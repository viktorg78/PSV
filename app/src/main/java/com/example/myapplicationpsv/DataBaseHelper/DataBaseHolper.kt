package com.example.myapplicationpsv.DataBaseHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.*

class DatabaseHelper(context: Context?):SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    SVHEMA) {
    companion object{
//        private const val DATABASE_NAME = "myDB3.db"
//        private const val SVHEMA =1
//        const val TABLE = "users"
//        const val COLUMN_ID = "_id"
//        const val COLUMN_NAME ="name"
//        const val COLUMN_YEAR = "year"
//        const val KEY_ID = "com.github.viktorg78.mybd10.id"
        private const val DATABASE_NAME = "myDB3_2.db"
        private const val SVHEMA =1
        const val TABLE = "PSV"
        const val COLUMN_ID = "_id"
        const val COLUMN_PSV = "psv_"
        const val COLUMN_DATE_TIME_INT = "date_time_int_"
        const val COLUMN_DATE_TIME_TEXT = "date_time_text_"
        const val COLUMN_MESSEGE = "messege_"
        const val KEY_ID = "com.github.viktorg78.mybd3_2.id"

    }
    val DB_PATH = context!!.filesDir.path + DATABASE_NAME

    private val myContext = context
    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    fun create_db() {
        var myInput: InputStream? = null
        var myOutput: OutputStream? = null
        try {
            val file = File(DB_PATH)
            if (!file.exists()) {
                this.readableDatabase
                //получаем локальную бд как поток
                myInput = myContext!!.assets.open(DATABASE_NAME)
                // Путь к новой бд
                val outFileName = DB_PATH
                // Открываем пустую бд
                myOutput = FileOutputStream(outFileName)
                // побайтово копируем данные
                val buffer = ByteArray(1024)
                var length: Int
                while (myInput.read(buffer).also { length = it } > 0) {
                    myOutput.write(buffer, 0, length)
                }
                myOutput.flush()
                myOutput.close()
                myInput.close()
            }
        } catch (ex: IOException) {

            Log.d("DatabaseHelper", ex.message!!)

        }
    }
    fun open(): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE)
    }
}