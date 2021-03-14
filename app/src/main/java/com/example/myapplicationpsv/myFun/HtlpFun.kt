package com.example.myapplicationpsv.myFun

import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

// Пример текущей даты времени
//    editTextData.setText(currentDateText(formatDate("dd.mm.yyyy")))
//    editTextTime.setText(currentDateText(formatDate("HH:mm")))

/**
 * formatDate Формат даты
 * @param s dd.mm.yyyy или HH:mm
 * @return DateFormat
 */
fun formatDate(s: String): DateFormat = SimpleDateFormat(s, Locale.getDefault())

/**
 * currentDateText Текущая дата в строку
 * @param format Формат даты
 * @return String
 */
fun currentDateText(format: DateFormat):String{
    val currentDate = Date()
    val dateText: String = format.format(currentDate)
    return dateText
}

/** h24 сколько процентов дня прошо
 * @param h час
 * @param m минута
 * @return Int от 0% до 100%
 */
fun h24(h: Int, m: Int):Int{
    val m24h = 1440
    val m1h = 60
    return ((h*m1h+m)*100)/m24h
}

/**
 * pointsToLine массив точек в массив лини
 * @param coordinate массив точек графика
 */
fun pointsToLine(coordinate: Array<Pair<Float, Float>>):Array<Pair<Float, Float>>{
    val otv = Array(pointsInLine(coordinate.size)) { Pair(1f, 1f) } // забили массив значениями
    var j = 0
    var t = 0
    for (i in otv.indices){
        if (i==0)
            otv[i] =  coordinate[j]
        else if (i > 0  && t==0 && i!=otv.size){
            otv[i] =  coordinate[j]
            t=1
            j--
        }else if (i>0 && t==1 && i!=otv.size){
            otv[i] =  coordinate[j]
            t=0
        }
        else if (i==otv.size)
            otv[i] = coordinate[j]
        j++
    }
    return otv
}

/**
 * pointsInLine точек в линии
 * @param x колличество точек в линиях
 * @return Int
 */
fun pointsInLine(x: Int):Int{
    var otv =0
    for(i in 1..x){
        when(i){
            1 -> otv = 1
            2 -> otv = 2
            else -> otv += 2
        }
    }
    return otv
}

/**
 * convertDate Конвертация милисикунд в дату
 * @param dateInMilliseconds милисикунды
 * @param dateFormat формат получаемой даты "dd/MM/yyyy hh:mm:ss"
 */
fun convertDate(dateInMilliseconds: String, dateFormat: String?): String {
    return android.text.format.DateFormat.format(dateFormat, dateInMilliseconds.toLong()).toString()
}

/**
 * spreadOutDate узнаем диапозон дат
 * @param dateAndTime дата типа календарь
 * @param keyDate ключ 7 дней - 1, 10 дней - 2, месяц - 3
 * @return IntRange
 */
fun spreadOutDate(dateAndTime:Calendar=Calendar.getInstance(), keyDate:Int = 1):IntRange{
  //  val dateAndTime = Calendar.getInstance() // getInstance() текущее время
    val maxDey = dateAndTime.getActualMaximum(Calendar.DAY_OF_MONTH)// Колличество дней в месяце
    val dateformat = SimpleDateFormat("dd")
    val startDate:Int = dateformat.format(dateAndTime.time).toInt() // число дня в dateAndTime
    var otv:IntRange =1..2
    // по 7 дней
    if (maxDey==28 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1..7
            8,9,10,11,12,13,14 -> otv = 8..14
            15,16,17,18,19,20,21 -> otv = 15..21
            22,23,24,25,26,27,28 -> otv = 22..28
        }
    if (maxDey==29 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1..7
            8,9,10,11,12,13,14 -> otv = 8..14
            15,16,17,18,19,20,21 -> otv = 15..21
            22,23,24,25,26,27,28 -> otv = 22..28
            29 -> otv = 23..29
        }
    if (maxDey==30 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1..7
            8,9,10,11,12,13,14 -> otv = 8..14
            15,16,17,18,19,20,21 -> otv = 15..21
            22,23,24,25,26,27,28 -> otv = 22..28
            29,30 -> otv = 24..30
        }
    if (maxDey==31 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1..7
            8,9,10,11,12,13,14 -> otv = 8..14
            15,16,17,18,19,20,21 -> otv = 15..21
            22,23,24,25,26,27,28 -> otv = 22..28
            29,30,31 -> otv = 25..31
        }
    // по 10 дней
    if (maxDey==28 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1..10
            11,12,13,14,15,16,17,18,19,20 -> otv = 11..20
            21,22,23,24,25,26,27,28 -> otv = 21..28
        }
    if (maxDey==29 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1..10
            11,12,13,14,15,16,17,18,19,20 -> otv = 11..20
            21,22,23,24,25,26,27,28,29 -> otv = 21..29
        }
    if (maxDey==30 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1..10
            11,12,13,14,15,16,17,18,19,20 -> otv = 11..20
            21,22,23,24,25,26,27,28,29,30 -> otv = 21..30
        }
    if (maxDey==31 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1..10
            11,12,13,14,15,16,17,18,19,20 -> otv = 11..20
            21,22,23,24,25,26,27,28,29,30 -> otv = 21..30
            31->otv = 22..31
        }

    // по 1 месяцу
    if (maxDey==28 && keyDate==3)
          otv = 1..28
    if (maxDey==29 && keyDate==3)
        otv = 1..29
    if (maxDey==30 && keyDate==3)
        otv = 1..30
    if (maxDey==31 && keyDate==3)
        otv = 1..31

return otv
}
/**
 * spreadOutDate2 узнаем диапозон дат
 * @param dateAndTime дата типа календарь
 * @param keyDate ключ 7 дней - 1, 10 дней - 2, месяц - 3
 * @return IntRange
 */
fun spreadOutDate2(dateAndTime:Calendar=Calendar.getInstance(), keyDate:Int = 1):Int{
    //  val dateAndTime = Calendar.getInstance() // getInstance() текущее время
    val maxDey = dateAndTime.getActualMaximum(Calendar.DAY_OF_MONTH)// Колличество дней в месяце
    val dateformat = SimpleDateFormat("dd")
    val startDate:Int = dateformat.format(dateAndTime.time).toInt() // число дня в dateAndTime
    var otv =1
    // по 7 дней
    if (maxDey==28 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1
            8,9,10,11,12,13,14 -> otv = 8
            15,16,17,18,19,20,21 -> otv = 15
            22,23,24,25,26,27,28 -> otv = 22
        }
    if (maxDey==29 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1
            8,9,10,11,12,13,14 -> otv = 8
            15,16,17,18,19,20,21 -> otv = 15
            22,23,24,25,26,27,28 -> otv = 22
            29 -> otv = 23
        }
    if (maxDey==30 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1
            8,9,10,11,12,13,14 -> otv = 8
            15,16,17,18,19,20,21 -> otv = 15
            22,23,24,25,26,27,28 -> otv = 22
            29,30 -> otv = 24
        }
    if (maxDey==31 && keyDate==1)
        when(startDate){
            1,2,3,4,5,6,7 -> otv = 1
            8,9,10,11,12,13,14 -> otv = 8
            15,16,17,18,19,20,21 -> otv = 15
            22,23,24,25,26,27,28 -> otv = 22
            29,30,31 -> otv = 25
        }
    // по 10 дней
    if (maxDey==28 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1
            11,12,13,14,15,16,17,18,19,20 -> otv = 11
            21,22,23,24,25,26,27,28 -> otv = 21
        }
    if (maxDey==29 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1
            11,12,13,14,15,16,17,18,19,20 -> otv = 11
            21,22,23,24,25,26,27,28,29 -> otv = 21
        }
    if (maxDey==30 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1
            11,12,13,14,15,16,17,18,19,20 -> otv = 11
            21,22,23,24,25,26,27,28,29,30 -> otv = 21
        }
    if (maxDey==31 && keyDate==2)
        when(startDate){
            1,2,3,4,5,6,7,8,9,10 -> otv = 1
            11,12,13,14,15,16,17,18,19,20 -> otv = 11
            21,22,23,24,25,26,27,28,29,30 -> otv = 21
            31->otv = 22
        }

    // по 1 месяцу
    if (keyDate==3)
        otv = 1


    return otv
}

// установка начальных даты
fun setInitialDate2(context: Context, dateCalendar:Calendar = Calendar.getInstance()):String {
    val bt = DateUtils.formatDateTime(
        context,
        dateCalendar.timeInMillis,
        DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
    )
    return  bt
}
// установка времени
fun setInitialTime2(context: Context, dateCalendar:Calendar = Calendar.getInstance()):String {
    val bt = DateUtils.formatDateTime(
        context,
        dateCalendar.timeInMillis,
        DateUtils.FORMAT_SHOW_TIME
    )
    return  bt
}
//начало месяца для сортировки БД
fun beginningOfThMonth(year:Int, month:Int):Long{
    val dateCalendar: Calendar = Calendar.getInstance()
    dateCalendar[Calendar.YEAR] = year
    dateCalendar[Calendar.MONTH] = month
    dateCalendar[Calendar.DAY_OF_MONTH] = 1
    dateCalendar[Calendar.HOUR_OF_DAY] = 0
    dateCalendar[Calendar.MINUTE] = 0
    return dateCalendar.timeInMillis
}
//Конец месяца для сортировки БД
fun endOfTheMonth(year:Int, month:Int):Long{
    val dateCalendar: Calendar = Calendar.getInstance()
    dateCalendar[Calendar.YEAR] = year
    dateCalendar[Calendar.MONTH] = month
    dateCalendar[Calendar.DAY_OF_MONTH] = dateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)// Колличество дней в месяце
    dateCalendar[Calendar.HOUR_OF_DAY] = 23
    dateCalendar[Calendar.MINUTE] = 59
    return dateCalendar.timeInMillis
}

// кратность 50и
fun k50(i:Int = 0) = (i+1)*10



/**
 * getPFMOfSetting получить ПСВ для настройки
 * @param i: данные бегунка
 * @param X: Данные настройи ПСВ 100, 200 или 800
 */
fun getPFMOfSetting(i:Int =0, X:Int=0) =  ((i+1)*10)+X


/**
 * barCalculation расчет смещения на палоске
 * @param x -было
 * @param X -стало
 * @param y -было на вычисляемой полоске
 */
fun barCalculation(x:Int, X:Int, y:Int) = x-X+y






