package com.example.myapplicationpsv

import android.app.DatePickerDialog
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.text.format.DateUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.myapplicationpsv.DataBaseHelper.DatabaseHelper
import com.example.myapplicationpsv.myFun.*
//import com.example.myapplicationpsv.myFun.KEY_SAVE_DEKADE
import com.example.myapplicationpsv.myGraph.GraphPSV
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    ////////////////////
    lateinit var databaseHelper: DatabaseHelper
    ////////////////////////
    var dateCalendar = Calendar.getInstance() // getInstance() текущее время

    var decade by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefStartPfm = pref.getInt("settings_lower_limit_of_the_red", 10)
        val settingsTopOfTheRedLine = pref.getInt("settings_top_of_the_red_line", 15)
        val settingsTopOfTheYellowLine = pref.getInt("settings_top_of_the_yellow_line",20)
        val settingsTopOfTheGreenLine = pref.getInt("settings_top_of_the_green_line",25)

        // пока отключили привязку к настройкам
//        psf.startPFM = k50(prefStartPfm)
//        psf.numberOfPFM = settingsTopOfTheGreenLine - prefStartPfm+1
//        psf.startGreenL = 1
//        psf.finishGreenL = settingsTopOfTheGreenLine - settingsTopOfTheYellowLine+1
//        psf.startYellowL = settingsTopOfTheGreenLine - settingsTopOfTheYellowLine+1
//        psf.finishYellowL =settingsTopOfTheGreenLine - settingsTopOfTheRedLine+1
//        psf.startRedL = settingsTopOfTheGreenLine - settingsTopOfTheRedLine+1
//        psf.finishRedL =settingsTopOfTheGreenLine - prefStartPfm+1







        databaseHelper = DatabaseHelper(applicationContext)
        databaseHelper.create_db()
        decade =1
        setInitialDate()
        arrayToPoint(dbToArray())

    }



//    Запоминаем ыбранную дату
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(KEY_SAVE_DATE,dateCalendar.timeInMillis)
//        outState.putInt(KEY_SAVE_DEKADE, decade)
        super.onSaveInstanceState(outState)
    }

    // споминаем выбраную дату
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        dateCalendar.timeInMillis = savedInstanceState.getLong(KEY_SAVE_DATE, dateCalendar.timeInMillis)
//        decade = savedInstanceState.getInt(KEY_SAVE_DEKADE, 1)
        setInitialDate()
        super.onRestoreInstanceState(savedInstanceState)
    }


    // неделя
    fun week(view: View){
        psf.startDays = spreadOutDate2(dateCalendar, 1)
        psf.numberOfDays = 7
        decade=1

    }

//    декада
    fun decade(view: View){
        psf.startDays = spreadOutDate2(dateCalendar, 2)
        psf.numberOfDays = 10
        decade=2
    }


//    месяц
    fun month(view: View){
        psf.startDays = spreadOutDate2(dateCalendar, 3)
        psf.numberOfDays = dateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)// Колличество дней в месяце
        decade=3
    }

    // для удобства декады месц и недели
    fun decada(flag:Int=1){
        when(flag){
            1->{psf.startDays = spreadOutDate2(dateCalendar, 1)
                psf.numberOfDays = 7
                decade=1}
            2->{psf.startDays = spreadOutDate2(dateCalendar, 2)
                psf.numberOfDays = 10
                decade=2}
            3->{psf.startDays = spreadOutDate2(dateCalendar, 3)
                psf.numberOfDays = dateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)// Колличество дней в месяце
                decade=3}
        }
    }

    fun add(view: View){
        val intentActivity2 = Intent(this, ActivityDB::class.java)
        startActivity(intentActivity2)
    }

    fun gurnal(view: View){
        val intentActivity2 = Intent(this, ActivityList::class.java)
        startActivity(intentActivity2)
    }
    fun gurnal2(){
        val intentActivity2 = Intent(this, ActivityList::class.java)
        startActivity(intentActivity2)
    }

    // отображаем диалоговое окно для выбора даты
    fun setDate(v: View?) {
//        DatePickerDialog(
//            this, d,
//            dateCalendar[Calendar.YEAR],
//            dateCalendar[Calendar.MONTH],
//            dateCalendar[Calendar.DAY_OF_MONTH]
//        )
//            .show()
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(supportFragmentManager,picker.toString())
        picker.addOnPositiveButtonClickListener {
            Log.d("DatePicker", "Date: ${picker.headerText}, Date epoch value = ${it}")
            dateCalendar.timeInMillis = it
            setInitialDate()
        }

    }

    // установка начальных даты
    private fun setInitialDate() {
        val bt = DateUtils.formatDateTime(
            this,
            dateCalendar.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
        )
        buttonDate.text = bt
        decada(decade)
        arrayToPoint(dbToArray())
    }

    // установка обработчика выбора даты
//    var d =
//        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            dateCalendar[Calendar.YEAR] = year
//            dateCalendar[Calendar.MONTH] = monthOfYear
//            dateCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
//            setInitialDate()
//        }

    // перевод из ДБ в массив
    fun dbToArray():Array<Pair<Long,Int>?>{
        val sqlHelper = DatabaseHelper(this)
        val db = sqlHelper.open()
        val beg = beginningOfThMonth(dateCalendar[Calendar.YEAR],dateCalendar[Calendar.MONTH]) // начало периода
        val en = endOfTheMonth(dateCalendar[Calendar.YEAR],dateCalendar[Calendar.MONTH]) // конец периода
        val cursor: Cursor = db.rawQuery("select * from ${DatabaseHelper.TABLE} where ${DatabaseHelper.COLUMN_DATE_TIME_INT} <= ?  and ${DatabaseHelper.COLUMN_DATE_TIME_INT} >= ? ORDER BY ${DatabaseHelper.COLUMN_DATE_TIME_INT}" , arrayOf(en.toString(), beg.toString()))
        val a =  arrayOfNulls<Pair<Long, Int>>(cursor.count) // пустой массив размерностью строк в запросе

       cursor.moveToFirst()
        for (i in 0 until cursor.count){
            a[i] =  cursor.getLong(2) to cursor.getInt(1)
           cursor.moveToNext()
        }
        cursor.close()
        return a
    }


    // перевод массива в точки
    fun arrayToPoint(a:Array<Pair<Long,Int>?>){
        val i = a.size
        psf.sizePaint1=i
        val d:Calendar = Calendar.getInstance()

        if (0<i){d.timeInMillis= a[0]?.first!!
            psf.x1d = d[Calendar.DAY_OF_MONTH]
            psf.x1h = d[Calendar.HOUR_OF_DAY]
            psf.y1 = a[0]?.second!!}
        if (1<i){d.timeInMillis= a[1]?.first!!
            psf.x2d = d[Calendar.DAY_OF_MONTH]
            psf.x2h = d[Calendar.HOUR_OF_DAY]
            psf.y2 = a[1]?.second!!}
        if (2<i){ d.timeInMillis= a[2]?.first!!
            psf.x3d = d[Calendar.DAY_OF_MONTH]
            psf.x3h = d[Calendar.HOUR_OF_DAY]
            psf.y3 = a[2]?.second!!}
        if (3<i){ d.timeInMillis= a[3]?.first!!
            psf.x4d = d[Calendar.DAY_OF_MONTH]
            psf.x4h = d[Calendar.HOUR_OF_DAY]
            psf.y4 = a[3]?.second!!}
        if (4<i){ d.timeInMillis= a[4]?.first!!
            psf.x5d = d[Calendar.DAY_OF_MONTH]
            psf.x5h = d[Calendar.HOUR_OF_DAY]
            psf.y5 = a[4]?.second!!}
        if (5<i){ d.timeInMillis= a[5]?.first!!
            psf.x6d = d[Calendar.DAY_OF_MONTH]
            psf.x6h = d[Calendar.HOUR_OF_DAY]
            psf.y6 = a[5]?.second!!}
        if (6<i){ d.timeInMillis= a[6]?.first!!
            psf.x7d = d[Calendar.DAY_OF_MONTH]
            psf.x7h = d[Calendar.HOUR_OF_DAY]
            psf.y7 = a[6]?.second!!}
        if (7<i){ d.timeInMillis= a[7]?.first!!
            psf.x8d = d[Calendar.DAY_OF_MONTH]
            psf.x8h = d[Calendar.HOUR_OF_DAY]
            psf.y8 = a[7]?.second!!}
        if (8<i){ d.timeInMillis= a[8]?.first!!
            psf.x9d = d[Calendar.DAY_OF_MONTH]
            psf.x9h = d[Calendar.HOUR_OF_DAY]
            psf.y9 = a[8]?.second!!}
        if (9<i){ d.timeInMillis= a[9]?.first!!
            psf.x10d = d[Calendar.DAY_OF_MONTH]
            psf.x10h = d[Calendar.HOUR_OF_DAY]
            psf.y10 = a[9]?.second!!}
        if (10<i){ d.timeInMillis= a[10]?.first!!
            psf.x11d = d[Calendar.DAY_OF_MONTH]
            psf.x11h = d[Calendar.HOUR_OF_DAY]
            psf.y11 = a[10]?.second!!}
        if (11<i){d.timeInMillis= a[11]?.first!!
            psf.x12d = d[Calendar.DAY_OF_MONTH]
            psf.x12h = d[Calendar.HOUR_OF_DAY]
            psf.y12 = a[11]?.second!!}
        if (12<i){d.timeInMillis= a[12]?.first!!
            psf.x13d = d[Calendar.DAY_OF_MONTH]
            psf.x13h = d[Calendar.HOUR_OF_DAY]
            psf.y13 = a[12]?.second!!}
        if (13<i){ d.timeInMillis= a[13]?.first!!
            psf.x14d = d[Calendar.DAY_OF_MONTH]
            psf.x14h = d[Calendar.HOUR_OF_DAY]
            psf.y14 = a[13]?.second!!}
        if (14<i){ d.timeInMillis= a[14]?.first!!
            psf.x15d = d[Calendar.DAY_OF_MONTH]
            psf.x15h = d[Calendar.HOUR_OF_DAY]
            psf.y15 = a[14]?.second!!}
        if (15<i){ d.timeInMillis= a[15]?.first!!
            psf.x16d = d[Calendar.DAY_OF_MONTH]
            psf.x16h = d[Calendar.HOUR_OF_DAY]
            psf.y16 = a[15]?.second!!}
        if (16<i){ d.timeInMillis= a[16]?.first!!
            psf.x17d = d[Calendar.DAY_OF_MONTH]
            psf.x17h = d[Calendar.HOUR_OF_DAY]
            psf.y17 = a[16]?.second!!}
        if (17<i){ d.timeInMillis= a[17]?.first!!
            psf.x18d = d[Calendar.DAY_OF_MONTH]
            psf.x18h = d[Calendar.HOUR_OF_DAY]
            psf.y18 = a[17]?.second!!}
        if (18<i){ d.timeInMillis= a[18]?.first!!
            psf.x19d = d[Calendar.DAY_OF_MONTH]
            psf.x19h = d[Calendar.HOUR_OF_DAY]
            psf.y19 = a[18]?.second!!}
        if (19<i){ d.timeInMillis= a[19]?.first!!
            psf.x20d = d[Calendar.DAY_OF_MONTH]
            psf.x20h = d[Calendar.HOUR_OF_DAY]
            psf.y20 = a[19]?.second!!}
        if (20<i){ d.timeInMillis= a[20]?.first!!
            psf.x21d = d[Calendar.DAY_OF_MONTH]
            psf.x21h = d[Calendar.HOUR_OF_DAY]
            psf.y21 = a[20]?.second!!}
        if (21<i){ d.timeInMillis= a[21]?.first!!
            psf.x22d = d[Calendar.DAY_OF_MONTH]
            psf.x22h = d[Calendar.HOUR_OF_DAY]
            psf.y22 = a[21]?.second!!}
        if (22<i){d.timeInMillis= a[22]?.first!!
            psf.x23d = d[Calendar.DAY_OF_MONTH]
            psf.x23h = d[Calendar.HOUR_OF_DAY]
            psf.y23 = a[22]?.second!!}
        if (23<i){d.timeInMillis= a[23]?.first!!
            psf.x24d = d[Calendar.DAY_OF_MONTH]
            psf.x24h = d[Calendar.HOUR_OF_DAY]
            psf.y24 = a[23]?.second!!}
        if (24<i){ d.timeInMillis= a[24]?.first!!
            psf.x25d = d[Calendar.DAY_OF_MONTH]
            psf.x25h = d[Calendar.HOUR_OF_DAY]
            psf.y25 = a[24]?.second!!}
        if (25<i){ d.timeInMillis= a[25]?.first!!
            psf.x26d = d[Calendar.DAY_OF_MONTH]
            psf.x26h = d[Calendar.HOUR_OF_DAY]
            psf.y26 = a[25]?.second!!}
        if (26<i){ d.timeInMillis= a[26]?.first!!
            psf.x27d = d[Calendar.DAY_OF_MONTH]
            psf.x27h = d[Calendar.HOUR_OF_DAY]
            psf.y27 = a[26]?.second!!}
        if (27<i){ d.timeInMillis= a[27]?.first!!
            psf.x28d = d[Calendar.DAY_OF_MONTH]
            psf.x28h = d[Calendar.HOUR_OF_DAY]
            psf.y28 = a[27]?.second!!}
        if (28<i){ d.timeInMillis= a[28]?.first!!
            psf.x29d = d[Calendar.DAY_OF_MONTH]
            psf.x29h = d[Calendar.HOUR_OF_DAY]
            psf.y29 = a[28]?.second!!}
        if (29<i){ d.timeInMillis= a[29]?.first!!
            psf.x30d = d[Calendar.DAY_OF_MONTH]
            psf.x30h = d[Calendar.HOUR_OF_DAY]
            psf.y30 = a[29]?.second!!}
        if (30<i){ d.timeInMillis= a[30]?.first!!
            psf.x31d = d[Calendar.DAY_OF_MONTH]
            psf.x31h = d[Calendar.HOUR_OF_DAY]
            psf.y31 = a[30]?.second!!}
        if (31<i){ d.timeInMillis= a[31]?.first!!
            psf.x32d = d[Calendar.DAY_OF_MONTH]
            psf.x32h = d[Calendar.HOUR_OF_DAY]
            psf.y32 = a[31]?.second!!}
        if (32<i){d.timeInMillis= a[32]?.first!!
            psf.x33d = d[Calendar.DAY_OF_MONTH]
            psf.x33h = d[Calendar.HOUR_OF_DAY]
            psf.y33 = a[32]?.second!!}
        if (33<i){d.timeInMillis= a[33]?.first!!
            psf.x34d = d[Calendar.DAY_OF_MONTH]
            psf.x34h = d[Calendar.HOUR_OF_DAY]
            psf.y34 = a[33]?.second!!}
        if (34<i){ d.timeInMillis= a[34]?.first!!
            psf.x35d = d[Calendar.DAY_OF_MONTH]
            psf.x35h = d[Calendar.HOUR_OF_DAY]
            psf.y35 = a[34]?.second!!}
        if (35<i){ d.timeInMillis= a[35]?.first!!
            psf.x36d = d[Calendar.DAY_OF_MONTH]
            psf.x36h = d[Calendar.HOUR_OF_DAY]
            psf.y36 = a[35]?.second!!}
        if (36<i){ d.timeInMillis= a[36]?.first!!
            psf.x37d = d[Calendar.DAY_OF_MONTH]
            psf.x37h = d[Calendar.HOUR_OF_DAY]
            psf.y37 = a[36]?.second!!}
        if (37<i){ d.timeInMillis= a[37]?.first!!
            psf.x38d = d[Calendar.DAY_OF_MONTH]
            psf.x38h = d[Calendar.HOUR_OF_DAY]
            psf.y38 = a[37]?.second!!}
        if (38<i){ d.timeInMillis= a[38]?.first!!
            psf.x39d = d[Calendar.DAY_OF_MONTH]
            psf.x39h = d[Calendar.HOUR_OF_DAY]
            psf.y39 = a[38]?.second!!}
        if (39<i){ d.timeInMillis= a[39]?.first!!
            psf.x40d = d[Calendar.DAY_OF_MONTH]
            psf.x40h = d[Calendar.HOUR_OF_DAY]
            psf.y40 = a[39]?.second!!}
        if (40<i){ d.timeInMillis= a[40]?.first!!
            psf.x41d = d[Calendar.DAY_OF_MONTH]
            psf.x41h = d[Calendar.HOUR_OF_DAY]
            psf.y41 = a[40]?.second!!}

        if (41<i){d.timeInMillis= a[41]?.first!!
            psf.x42d = d[Calendar.DAY_OF_MONTH]
            psf.x42h = d[Calendar.HOUR_OF_DAY]
            psf.y42 = a[41]?.second!!}
        if (42<i){d.timeInMillis= a[42]?.first!!
            psf.x43d = d[Calendar.DAY_OF_MONTH]
            psf.x43h = d[Calendar.HOUR_OF_DAY]
            psf.y43 = a[42]?.second!!}
        if (43<i){d.timeInMillis= a[43]?.first!!
            psf.x44d = d[Calendar.DAY_OF_MONTH]
            psf.x44h = d[Calendar.HOUR_OF_DAY]
            psf.y44 = a[43]?.second!!}
        if (44<i){d.timeInMillis= a[44]?.first!!
            psf.x45d = d[Calendar.DAY_OF_MONTH]
            psf.x45h = d[Calendar.HOUR_OF_DAY]
            psf.y45 = a[44]?.second!!}
        if (45<i){d.timeInMillis= a[45]?.first!!
            psf.x46d = d[Calendar.DAY_OF_MONTH]
            psf.x46h = d[Calendar.HOUR_OF_DAY]
            psf.y46 = a[45]?.second!!}
        if (46<i){d.timeInMillis= a[46]?.first!!
            psf.x47d = d[Calendar.DAY_OF_MONTH]
            psf.x47h = d[Calendar.HOUR_OF_DAY]
            psf.y47 = a[46]?.second!!}
        if (47<i){d.timeInMillis= a[47]?.first!!
            psf.x48d = d[Calendar.DAY_OF_MONTH]
            psf.x48h = d[Calendar.HOUR_OF_DAY]
            psf.y48 = a[47]?.second!!}
        if (48<i){d.timeInMillis= a[48]?.first!!
            psf.x49d = d[Calendar.DAY_OF_MONTH]
            psf.x49h = d[Calendar.HOUR_OF_DAY]
            psf.y49 = a[48]?.second!!}
        if (49<i){d.timeInMillis= a[49]?.first!!
            psf.x50d = d[Calendar.DAY_OF_MONTH]
            psf.x50h = d[Calendar.HOUR_OF_DAY]
            psf.y50 = a[49]?.second!!}
        if (50<i){d.timeInMillis= a[50]?.first!!
            psf.x51d = d[Calendar.DAY_OF_MONTH]
            psf.x41h = d[Calendar.HOUR_OF_DAY]
            psf.y51 = a[50]?.second!!}

        if (51<i){d.timeInMillis= a[51]?.first!!
            psf.x52d = d[Calendar.DAY_OF_MONTH]
            psf.x52h = d[Calendar.HOUR_OF_DAY]
            psf.y52 = a[51]?.second!!}
        if (52<i){d.timeInMillis= a[52]?.first!!
            psf.x53d = d[Calendar.DAY_OF_MONTH]
            psf.x53h = d[Calendar.HOUR_OF_DAY]
            psf.y53 = a[52]?.second!!}
        if (53<i){d.timeInMillis= a[53]?.first!!
            psf.x54d = d[Calendar.DAY_OF_MONTH]
            psf.x54h = d[Calendar.HOUR_OF_DAY]
            psf.y54 = a[53]?.second!!}
        if (54<i){d.timeInMillis= a[54]?.first!!
            psf.x55d = d[Calendar.DAY_OF_MONTH]
            psf.x55h = d[Calendar.HOUR_OF_DAY]
            psf.y55 = a[54]?.second!!}
        if (55<i){d.timeInMillis= a[55]?.first!!
            psf.x56d = d[Calendar.DAY_OF_MONTH]
            psf.x56h = d[Calendar.HOUR_OF_DAY]
            psf.y56 = a[55]?.second!!}
        if (56<i){d.timeInMillis= a[56]?.first!!
            psf.x57d = d[Calendar.DAY_OF_MONTH]
            psf.x57h = d[Calendar.HOUR_OF_DAY]
            psf.y57 = a[56]?.second!!}
        if (57<i){d.timeInMillis= a[57]?.first!!
            psf.x58d = d[Calendar.DAY_OF_MONTH]
            psf.x58h = d[Calendar.HOUR_OF_DAY]
            psf.y58 = a[57]?.second!!}
        if (58<i){d.timeInMillis= a[58]?.first!!
            psf.x59d = d[Calendar.DAY_OF_MONTH]
            psf.x59h = d[Calendar.HOUR_OF_DAY]
            psf.y59 = a[58]?.second!!}
        if (59<i){d.timeInMillis= a[59]?.first!!
            psf.x60d = d[Calendar.DAY_OF_MONTH]
            psf.x60h = d[Calendar.HOUR_OF_DAY]
            psf.y60 = a[59]?.second!!}
        if (60<i){d.timeInMillis= a[60]?.first!!
            psf.x61d = d[Calendar.DAY_OF_MONTH]
            psf.x61h = d[Calendar.HOUR_OF_DAY]
            psf.y61 = a[60]?.second!!}

        if (61<i){d.timeInMillis= a[61]?.first!!
            psf.x62d = d[Calendar.DAY_OF_MONTH]
            psf.x62h = d[Calendar.HOUR_OF_DAY]
            psf.y62 = a[61]?.second!!}
        if (62<i){d.timeInMillis= a[62]?.first!!
            psf.x63d = d[Calendar.DAY_OF_MONTH]
            psf.x63h = d[Calendar.HOUR_OF_DAY]
            psf.y63 = a[62]?.second!!}
        if (63<i){d.timeInMillis= a[63]?.first!!
            psf.x64d = d[Calendar.DAY_OF_MONTH]
            psf.x64h = d[Calendar.HOUR_OF_DAY]
            psf.y64 = a[63]?.second!!}
        if (64<i){d.timeInMillis= a[64]?.first!!
            psf.x65d = d[Calendar.DAY_OF_MONTH]
            psf.x65h = d[Calendar.HOUR_OF_DAY]
            psf.y65 = a[64]?.second!!}
        if (65<i){d.timeInMillis= a[65]?.first!!
            psf.x66d = d[Calendar.DAY_OF_MONTH]
            psf.x66h = d[Calendar.HOUR_OF_DAY]
            psf.y66 = a[65]?.second!!}
        if (66<i){d.timeInMillis= a[66]?.first!!
            psf.x67d = d[Calendar.DAY_OF_MONTH]
            psf.x67h = d[Calendar.HOUR_OF_DAY]
            psf.y67 = a[66]?.second!!}
        if (67<i){d.timeInMillis= a[67]?.first!!
            psf.x68d = d[Calendar.DAY_OF_MONTH]
            psf.x68h = d[Calendar.HOUR_OF_DAY]
            psf.y68 = a[67]?.second!!}
        if (68<i){d.timeInMillis= a[68]?.first!!
            psf.x69d = d[Calendar.DAY_OF_MONTH]
            psf.x69h = d[Calendar.HOUR_OF_DAY]
            psf.y69 = a[68]?.second!!}
        if (69<i){d.timeInMillis= a[69]?.first!!
            psf.x70d = d[Calendar.DAY_OF_MONTH]
            psf.x70h = d[Calendar.HOUR_OF_DAY]
            psf.y70 = a[69]?.second!!}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_activity_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.journalItem -> {
                gurnal2()
                return true
            }
            R.id.settingsItem -> {
                val settingActivity =  Intent(this, SettingsActivity::class.java)
                startActivity(settingActivity)
            }
        }
        return super.onOptionsItemSelected(item)

    }
}