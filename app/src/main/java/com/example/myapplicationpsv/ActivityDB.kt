package com.example.myapplicationpsv

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationpsv.DataBaseHelper.DatabaseHelper
import com.example.myapplicationpsv.myFun.convertDate
import com.example.myapplicationpsv.myFun.setInitialDate2
import com.example.myapplicationpsv.myFun.setInitialTime2
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import kotlinx.android.synthetic.main.activity_d_b.*
import org.xml.sax.Parser
import java.util.*

class ActivityDB : AppCompatActivity() {
    lateinit var sqlHelper: DatabaseHelper
    lateinit var db:SQLiteDatabase
    lateinit var cursor: Cursor
    var userId:Long = 0


    private lateinit var prefs: SharedPreferences
    private var message1: String? = null // содержание мессаджа

    var dateAndTime = Calendar.getInstance() // getInstance() текущее время

    private val N_COLUMN_MESSEGE = "messege1" // сдесь храница последний мессадж

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_b)

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        buttonDate2.text = setInitialDate2(this, dateAndTime)
        buttonTime2.text = setInitialTime2(this, dateAndTime)


        userId = intent.getLongExtra(DatabaseHelper.KEY_ID, 0)
        sqlHelper = DatabaseHelper(this)
        db = sqlHelper.open()

        if (userId > 0) {
            cursor = db.rawQuery(
                "select * from ${DatabaseHelper.TABLE} where ${DatabaseHelper.COLUMN_ID} =?",
                arrayOf(userId.toString())
            )
            // чтение из журнала
            cursor.moveToFirst()
            editTextPSV.setText(cursor.getInt(1).toString())
            editTextNote.setText(cursor.getString(4))
            dateAndTime.timeInMillis=cursor.getString(2).toLong()
            cursor.close()

        }else
            buttonDel.visibility = View.GONE

        setInitialDateTime()
    }

    // запись нового в журнал
    fun save(view: View){
        val cv = ContentValues()
        cv.put(DatabaseHelper.COLUMN_PSV, Integer.parseInt(editTextPSV.text.toString()))
        cv.put(DatabaseHelper.COLUMN_DATE_TIME_INT, dateAndTime.timeInMillis)
        cv.put(DatabaseHelper.COLUMN_DATE_TIME_TEXT, setInitialDateTime2())
        cv.put(DatabaseHelper.COLUMN_MESSEGE, editTextNote.text.toString())
        message1 = editTextNote.text.toString()
        if (userId > 0){
            db.update(
                DatabaseHelper.TABLE,
                cv,
                "${DatabaseHelper.COLUMN_ID} = ${userId.toString()}",
                null
            )
        }else
            db.insert(DatabaseHelper.TABLE, null, cv)
        goHome()
    }
    fun delete(view: View){
        db.delete(DatabaseHelper.TABLE, "_id = ?", arrayOf(userId.toString()))
        goHome()
    }
    private fun goHome(){
        db.close()
        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    // установка начальных даты и времени
    private fun setInitialDateTime() {
//        currentDateTime.text = DateUtils.formatDateTime(
//            this,
//            dateAndTime.timeInMillis,
//            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_TIME
//        )
        buttonDate2.text = setInitialDate2(this,dateAndTime)
        buttonTime2.text = setInitialTime2(this,dateAndTime)
    }
    private fun setInitialDateTime2() =  DateUtils.formatDateTime(
            this,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_TIME
        )


    // отображаем диалоговое окно для выбора даты
    fun setDate(v: View?) {
//        DatePickerDialog(
//            this, d,
//            dateAndTime[Calendar.YEAR],
//            dateAndTime[Calendar.MONTH],
//            dateAndTime[Calendar.DAY_OF_MONTH]
//        )
//            .show()
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(supportFragmentManager,picker.toString())
        picker.addOnPositiveButtonClickListener {
            Log.d("DatePicker", "Date: ${picker.headerText}, Date epoch value = ${it}")
            dateAndTime.timeInMillis = it
            setInitialDateTime()
        }
    }

    // отображаем диалоговое окно для выбора времени
    fun setTime(v: View?) {
//        TimePickerDialog(
//            this, t,
//            dateAndTime[Calendar.HOUR_OF_DAY],
//            dateAndTime[Calendar.MINUTE], true
//        )
//            .show()
        val builder = MaterialTimePicker.Builder()
        val picker = builder.build()
        picker.show(supportFragmentManager,picker.toString())

        picker.addOnPositiveButtonClickListener {

//            Toast.makeText(this, "$h:$m", Toast.LENGTH_SHORT).show()
            dateAndTime[Calendar.HOUR_OF_DAY]= picker.hour
            dateAndTime[Calendar.MINUTE] = picker.minute
            setInitialDateTime()
        }

    }

    // установка обработчика выбора даты
//    var d =
//        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            dateAndTime[Calendar.YEAR] = year
//            dateAndTime[Calendar.MONTH] = monthOfYear
//            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
//            setInitialDateTime()
//        }
    // установка обработчика выбора времени
    var t =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
            dateAndTime[Calendar.MINUTE] = minute
            setInitialDateTime()
        }


    override fun onPause() {
        super.onPause()
        // записываем последний мессадж
        val editor = prefs.edit()
        editor.putString(N_COLUMN_MESSEGE, message1).apply()
    }

    override fun onResume() {
        super.onResume()
        if(prefs.contains(N_COLUMN_MESSEGE)){
            // Получаем примечание
            message1 = prefs.getString(N_COLUMN_MESSEGE, null)
            // Выводим на экран
            editTextNote.setText(message1)
        }
    }






}