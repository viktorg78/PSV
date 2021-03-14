package com.example.myapplicationpsv

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.PersistableBundle
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.myapplicationpsv.DataBaseHelper.DatabaseHelper
import com.example.myapplicationpsv.myFun.*
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_list.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class ActivityList : AppCompatActivity() {
    lateinit var databaseHelper: DatabaseHelper
    lateinit var db:SQLiteDatabase
    lateinit var cursor:Cursor
    var dateFor by Delegates.notNull<Long>() // Начало периода
    var dateTo by Delegates.notNull<Long>() // Конец периода

    var dateCalendar = Calendar.getInstance() // getInstance() текущее время

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        databaseHelper = DatabaseHelper(applicationContext)
        databaseHelper.create_db()
        setInitialDate()
        list.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, ActivityDB::class.java)
            intent.putExtra(DatabaseHelper.KEY_ID, id)
            startActivity(intent)
        }
        dbList(dateFor,dateTo)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(KEY_SAVE_DATE_FOR_JOURNAL, dateFor)
        outState.putLong(KEY_SAVE_DATE_TO_JOURNAL, dateTo)
        outState.putString(KEY_BUTTON_JOURNAL, button3.text as String)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        dateFor = savedInstanceState.getLong(KEY_SAVE_DATE_FOR_JOURNAL,dateCalendar.timeInMillis)
        dateTo = savedInstanceState.getLong(KEY_SAVE_DATE_TO_JOURNAL,dateCalendar.timeInMillis)
        button3.text =  savedInstanceState.getString(KEY_BUTTON_JOURNAL,"-----")
        dbList(dateFor,dateTo)

        super.onRestoreInstanceState(savedInstanceState)
    }


//    override fun onResume() {
//        super.onResume()
//        db = databaseHelper.open()
//        cursor = db.rawQuery(
//            "select * from ${DatabaseHelper.TABLE} where ${DatabaseHelper.COLUMN_DATE_TIME_INT} <= $dateTo  and ${DatabaseHelper.COLUMN_DATE_TIME_INT} >= $dateFor  ORDER BY ${DatabaseHelper.COLUMN_DATE_TIME_INT}",
//            null
//        )
//        val  headers = arrayOf(DatabaseHelper.COLUMN_PSV, DatabaseHelper.COLUMN_DATE_TIME_TEXT)
//
//        val userAdapter = SimpleCursorAdapter(
//            this,
//            android.R.layout.two_line_list_item,
//            cursor,
//            headers,
//            intArrayOf(android.R.id.text1, android.R.id.text2),
//            0
//        )
//        list.adapter = userAdapter
//    }

    private fun dbList(dateForD: Long, dateToD: Long){
        db = databaseHelper.open()
        cursor = db.rawQuery(
            "select * from ${DatabaseHelper.TABLE} where ${DatabaseHelper.COLUMN_DATE_TIME_INT} <= $dateToD  and ${DatabaseHelper.COLUMN_DATE_TIME_INT} >= $dateForD  ORDER BY ${DatabaseHelper.COLUMN_DATE_TIME_INT}",
            null
        )
        val  headers = arrayOf(DatabaseHelper.COLUMN_PSV, DatabaseHelper.COLUMN_DATE_TIME_TEXT)

        val userAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.two_line_list_item,
            cursor,
            headers,
            intArrayOf(android.R.id.text1, android.R.id.text2),
            0
        )
        list.adapter = userAdapter
    }
    override fun onDestroy() {
        super.onDestroy()
        cursor.close()
        db.close()
    }
    fun add(view: View){
        val intentActivity2 = Intent(this, ActivityDB::class.java)
        startActivity(intentActivity2)
    }

    fun dateFromTo(view: View){
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val picker = builder.build()
        picker.show(supportFragmentManager, picker.toString())

        picker.addOnCancelListener {
            Log.d("DatePicker", "диалог был отменен")
            setInitialDate()
        }

        picker.addOnNegativeButtonClickListener {
            Log.d("DatePicker", "Нажата отрицательная кнопка диалогового окна")
            setInitialDate()
        }

        picker.addOnPositiveButtonClickListener {
            Log.d("DatePicker", "Дата: ${picker.headerText}, Значение эпохи даты = ${it}")
            Log.d(
                "DatePicker",
                "Строка даты = ${picker.headerText}, Значения эпохи даты::${
                    it
                        .first
                }:: to :: ${it.second}"
            )
            dateFor = it.first!!
            dateTo = it.second!!
            dbList(it.first!!,it.second!!)
            button3.text = picker.headerText
        }

    }

    // установка начальных даты
    private fun setInitialDate() {
        val bt = DateUtils.formatDateTime(
            this,
            dateCalendar.timeInMillis,
             DateUtils.FORMAT_NO_MONTH_DAY  or DateUtils.FORMAT_SHOW_YEAR//месяц
        )
        button3.text = bt
       dateFor = beginningOfThMonth(dateCalendar[Calendar.YEAR],dateCalendar[Calendar.MONTH]) // начало периода
       dateTo = endOfTheMonth(dateCalendar[Calendar.YEAR],dateCalendar[Calendar.MONTH]) // конец периода
    }


}