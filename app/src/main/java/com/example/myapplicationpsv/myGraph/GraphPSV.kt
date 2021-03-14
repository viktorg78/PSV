package com.example.myapplicationpsv.myGraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.example.myapplicationpsv.R
import com.example.myapplicationpsv.myFun.h24
import com.example.myapplicationpsv.myFun.pointsToLine


class GraphPSV(context: Context, attributeSet: AttributeSet) :View(context, attributeSet){
    companion object{
        private const val DEFAULT_FON_COLOR = Color.WHITE // цвет фона
        private const val DEFAULT_LINE_COLOR = Color.GRAY // цвет линий
        private const val DEFAULT_TEXT_COLOR = Color.BLACK // цвет текста

        private const val DEFAULT_NUMBER_OF_DAYS = 30 // количество линий дней
        private const val DEFAULT_START_DAYS = 1 // отсчитывать дни от этой цифры

        private const val DEFAULT_NUMBER_OF_PFM = 10 // колличестов линий ПСВ
        private const val DEFAULT_START_PFM = 110 // отчет от это цтфры
        private const val DEFAULT_INTERVAL_PFM = 10 // интэрвал между цифрами

        private const val DEFAULT_TEXT_SIZE = 30f

        private const val DEFAULT_COLOR_GREEN = Color.GREEN
        private const val DEFAULT_COLOR_YELLOW = Color.YELLOW
        private const val DEFAULT_COLOR_RED = Color.RED

        private const val DEFAULT_START_GREEN_LINE = 1
        private const val DEFAULT_FINISH_GREEN_LINE = 6
        private const val DEFAULT_START_YELLOW_LINE = 6
        private const val DEFAULT_FINISH_YELLOW_LINE = 11
        private const val DEFAULT_START_RED_LINE = 11
        private const val DEFAULT_FINISH_RED_LINE = 15


        const val YES_SPAN = 1L //есть цветной график
        const val NO_SPAN = 0L //Нет цветнеово графика

        // координаты точек
        private const val DEFAULT_XD = 1 // день
        private const val DEFAULT_XH = 1 //час
        private const val DEFAULT_Y = 1 //показания

        private const val DEFAULT_SIZE_POINT = 0

    }

    private val paint = Paint()
    private var fonColor = DEFAULT_FON_COLOR
    private var lineColor = DEFAULT_LINE_COLOR
    private var textColor = DEFAULT_TEXT_COLOR
    var numberOfDays = DEFAULT_NUMBER_OF_DAYS
        set(value) {
            field = value
            invalidate()
        }
    private var textSize = DEFAULT_TEXT_SIZE
    var startDays = DEFAULT_START_DAYS
        set(value) {
            field = value
            invalidate()
        }
    var numberOfPFM = DEFAULT_NUMBER_OF_PFM
        set(value)  {
            field = value
            invalidate()
        }
    var startPFM = DEFAULT_START_PFM
    private var intervalPFM = DEFAULT_INTERVAL_PFM

    var colorGraph = NO_SPAN
        set(state){
            field = state
            invalidate()
        }
    var startGreenL = DEFAULT_START_GREEN_LINE  // начало зеленой линии
        set(value) {
         field = value
            invalidate()
        }
    var finishGreenL = DEFAULT_FINISH_GREEN_LINE
        set(value) {
            field = value
            invalidate()
        }
    var startYellowL = DEFAULT_START_YELLOW_LINE
        set(value) {
            field = value
            invalidate()
        }
    var finishYellowL = DEFAULT_FINISH_YELLOW_LINE
        set(value) {
            field = value
            invalidate()
        }
    var startRedL = DEFAULT_START_RED_LINE
        set(value) {
            field = value
            invalidate()
        }
    var finishRedL = DEFAULT_FINISH_RED_LINE
        set(value) {
            field = value
            invalidate()
        }




    var sizeH = 0
    var sizeW = 0

    var x1d = DEFAULT_XD
    var x1h = DEFAULT_XH
    var y1 = DEFAULT_Y

    var x2d = DEFAULT_XD
    var x2h = DEFAULT_XH
    var y2 = DEFAULT_Y

    var x3d = DEFAULT_XD
    var x3h = DEFAULT_XH
    var y3 = DEFAULT_Y

    var x4d = DEFAULT_XD
    var x4h = DEFAULT_XH
    var y4 = DEFAULT_Y

    var x5d = DEFAULT_XD
    var x5h = DEFAULT_XH
    var y5 = DEFAULT_Y

    var x6d = DEFAULT_XD
    var x6h = DEFAULT_XH
    var y6 = DEFAULT_Y

    var x7d = DEFAULT_XD
    var x7h = DEFAULT_XH
    var y7 = DEFAULT_Y

    var x8d = DEFAULT_XD
    var x8h = DEFAULT_XH
    var y8 = DEFAULT_Y

    var x9d = DEFAULT_XD
    var x9h = DEFAULT_XH
    var y9 = DEFAULT_Y

    var x10d = DEFAULT_XD
    var x10h = DEFAULT_XH
    var y10 = DEFAULT_Y

    var x11d = DEFAULT_XD
    var x11h = DEFAULT_XH
    var y11 = DEFAULT_Y

    var x12d = DEFAULT_XD
    var x12h = DEFAULT_XH
    var y12 = DEFAULT_Y

    var x13d = DEFAULT_XD
    var x13h = DEFAULT_XH
    var y13 = DEFAULT_Y

    var x14d = DEFAULT_XD
    var x14h = DEFAULT_XH
    var y14 = DEFAULT_Y

    var x15d = DEFAULT_XD
    var x15h = DEFAULT_XH
    var y15 = DEFAULT_Y

    var x16d = DEFAULT_XD
    var x16h = DEFAULT_XH
    var y16 = DEFAULT_Y

    var x17d = DEFAULT_XD
    var x17h = DEFAULT_XH
    var y17 = DEFAULT_Y

    var x18d = DEFAULT_XD
    var x18h = DEFAULT_XH
    var y18 = DEFAULT_Y

    var x19d = DEFAULT_XD
    var x19h = DEFAULT_XH
    var y19 = DEFAULT_Y

    var x20d = DEFAULT_XD
    var x20h = DEFAULT_XH
    var y20 = DEFAULT_Y

    var x21d = DEFAULT_XD
    var x21h = DEFAULT_XH
    var y21 = DEFAULT_Y

    var x22d = DEFAULT_XD
    var x22h = DEFAULT_XH
    var y22 = DEFAULT_Y

    var x23d = DEFAULT_XD
    var x23h = DEFAULT_XH
    var y23 = DEFAULT_Y

    var x24d = DEFAULT_XD
    var x24h = DEFAULT_XH
    var y24 = DEFAULT_Y

    var x25d = DEFAULT_XD
    var x25h = DEFAULT_XH
    var y25 = DEFAULT_Y

    var x26d = DEFAULT_XD
    var x26h = DEFAULT_XH
    var y26 = DEFAULT_Y

    var x27d = DEFAULT_XD
    var x27h = DEFAULT_XH
    var y27 = DEFAULT_Y

    var x28d = DEFAULT_XD
    var x28h = DEFAULT_XH
    var y28 = DEFAULT_Y

    var x29d = DEFAULT_XD
    var x29h = DEFAULT_XH
    var y29 = DEFAULT_Y

    var x30d = DEFAULT_XD
    var x30h = DEFAULT_XH
    var y30 = DEFAULT_Y

    var x31d = DEFAULT_XD
    var x31h = DEFAULT_XH
    var y31 = DEFAULT_Y

    var x32d = DEFAULT_XD
    var x32h = DEFAULT_XH
    var y32 = DEFAULT_Y

    var x33d = DEFAULT_XD
    var x33h = DEFAULT_XH
    var y33 = DEFAULT_Y

    var x34d = DEFAULT_XD
    var x34h = DEFAULT_XH
    var y34 = DEFAULT_Y

    var x35d = DEFAULT_XD
    var x35h = DEFAULT_XH
    var y35 = DEFAULT_Y

    var x36d = DEFAULT_XD
    var x36h = DEFAULT_XH
    var y36 = DEFAULT_Y

    var x37d = DEFAULT_XD
    var x37h = DEFAULT_XH
    var y37 = DEFAULT_Y

    var x38d = DEFAULT_XD
    var x38h = DEFAULT_XH
    var y38 = DEFAULT_Y

    var x39d = DEFAULT_XD
    var x39h = DEFAULT_XH
    var y39 = DEFAULT_Y

    var x40d = DEFAULT_XD
    var x40h = DEFAULT_XH
    var y40 = DEFAULT_Y

    var x41d = DEFAULT_XD
    var x41h = DEFAULT_XH
    var y41 = DEFAULT_Y

    var x42d = DEFAULT_XD
    var x42h = DEFAULT_XH
    var y42 = DEFAULT_Y

    var x43d = DEFAULT_XD
    var x43h = DEFAULT_XH
    var y43 = DEFAULT_Y

    var x44d = DEFAULT_XD
    var x44h = DEFAULT_XH
    var y44 = DEFAULT_Y

    var x45d = DEFAULT_XD
    var x45h = DEFAULT_XH
    var y45 = DEFAULT_Y

    var x46d = DEFAULT_XD
    var x46h = DEFAULT_XH
    var y46 = DEFAULT_Y

    var x47d = DEFAULT_XD
    var x47h = DEFAULT_XH
    var y47 = DEFAULT_Y

    var x48d = DEFAULT_XD
    var x48h = DEFAULT_XH
    var y48 = DEFAULT_Y

    var x49d = DEFAULT_XD
    var x49h = DEFAULT_XH
    var y49 = DEFAULT_Y

    var x50d = DEFAULT_XD
    var x50h = DEFAULT_XH
    var y50 = DEFAULT_Y

    var x51d = DEFAULT_XD
    var x51h = DEFAULT_XH
    var y51 = DEFAULT_Y

    var x52d = DEFAULT_XD
    var x52h = DEFAULT_XH
    var y52 = DEFAULT_Y

    var x53d = DEFAULT_XD
    var x53h = DEFAULT_XH
    var y53 = DEFAULT_Y

    var x54d = DEFAULT_XD
    var x54h = DEFAULT_XH
    var y54 = DEFAULT_Y

    var x55d = DEFAULT_XD
    var x55h = DEFAULT_XH
    var y55 = DEFAULT_Y

    var x56d = DEFAULT_XD
    var x56h = DEFAULT_XH
    var y56 = DEFAULT_Y

    var x57d = DEFAULT_XD
    var x57h = DEFAULT_XH
    var y57 = DEFAULT_Y

    var x58d = DEFAULT_XD
    var x58h = DEFAULT_XH
    var y58 = DEFAULT_Y

    var x59d = DEFAULT_XD
    var x59h = DEFAULT_XH
    var y59 = DEFAULT_Y

    var x60d = DEFAULT_XD
    var x60h = DEFAULT_XH
    var y60 = DEFAULT_Y

    var x61d = DEFAULT_XD
    var x61h = DEFAULT_XH
    var y61 = DEFAULT_Y

    var x62d = DEFAULT_XD
    var x62h = DEFAULT_XH
    var y62 = DEFAULT_Y

    var x63d = DEFAULT_XD
    var x63h = DEFAULT_XH
    var y63 = DEFAULT_Y

    var x64d = DEFAULT_XD
    var x64h = DEFAULT_XH
    var y64 = DEFAULT_Y

    var x65d = DEFAULT_XD
    var x65h = DEFAULT_XH
    var y65 = DEFAULT_Y

    var x66d = DEFAULT_XD
    var x66h = DEFAULT_XH
    var y66 = DEFAULT_Y

    var x67d = DEFAULT_XD
    var x67h = DEFAULT_XH
    var y67 = DEFAULT_Y

    var x68d = DEFAULT_XD
    var x68h = DEFAULT_XH
    var y68 = DEFAULT_Y

    var x69d = DEFAULT_XD
    var x69h = DEFAULT_XH
    var y69 = DEFAULT_Y

    var x70d = DEFAULT_XD
    var x70h = DEFAULT_XH
    var y70 = DEFAULT_Y

    var sizePaint1 = DEFAULT_SIZE_POINT



//=======================================================

//    val poi1=Pair(toDays(1,10,0),toPFM(200))
//    val poi2=Pair(toDays(1,18,0),toPFM(210))
//    val poi3=Pair(toDays(2,10,0),toPFM(200))
//    val poi4=Pair(toDays(2,18,0),toPFM(220))
//    val poi5=Pair(toDays(3,8,0),toPFM(221))
//    val poi6=Pair(toDays(3,18,0),toPFM(220))
//    val poi7=Pair(toDays(4,8,0),toPFM(200))
//    val poi8=Pair(toDays(4,18,0),toPFM(250))
//    val poi9=Pair(toDays(5,8,0),toPFM(210))
//    val poi10=Pair(toDays(5,18,0),toPFM(190))
//    val pV1 = arrayOf(poi1, poi2, poi3, poi4, poi5, poi6, poi7, poi8, poi9, poi10)
//=====================================================

    init {
        paint.isAntiAlias = true
        setupAttributes(attributeSet)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawGraphLine(canvas)
        drawGraph(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        sizeH = measuredHeight
        sizeW = measuredWidth
        setMeasuredDimension(sizeW, sizeH)
    }

    private fun setupAttributes(attributeSet: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.GraphPSV,0,0)
        fonColor = typedArray.getColor(R.styleable.GraphPSV_fonColor, DEFAULT_FON_COLOR)
        lineColor = typedArray.getColor(R.styleable.GraphPSV_lineColor, DEFAULT_LINE_COLOR)
        numberOfDays = typedArray.getInt(R.styleable.GraphPSV_numberOfDays, DEFAULT_NUMBER_OF_DAYS)
        textColor = typedArray.getColor(R.styleable.GraphPSV_textColor, DEFAULT_TEXT_COLOR)
        textSize = typedArray.getFloat(R.styleable.GraphPSV_textSize, DEFAULT_TEXT_SIZE)
        startDays = typedArray.getInt(R.styleable.GraphPSV_startDays, DEFAULT_START_DAYS)
        numberOfPFM = typedArray.getInt(R.styleable.GraphPSV_numberOfPFM, DEFAULT_NUMBER_OF_PFM)
        startPFM = typedArray.getInt(R.styleable.GraphPSV_startPFM, DEFAULT_START_PFM)
        intervalPFM = typedArray.getInt(R.styleable.GraphPSV_intervalPFM, DEFAULT_INTERVAL_PFM)
        colorGraph = typedArray.getInt(R.styleable.GraphPSV_colorGraph, NO_SPAN.toInt()).toLong()

        startGreenL = typedArray.getInt(R.styleable.GraphPSV_startGreenLine, DEFAULT_START_GREEN_LINE)
        finishGreenL = typedArray.getInt(R.styleable.GraphPSV_finishGreenLine, DEFAULT_FINISH_GREEN_LINE)
        startYellowL = typedArray.getInt(R.styleable.GraphPSV_startYellowLine, DEFAULT_START_YELLOW_LINE)
        finishYellowL = typedArray.getInt(R.styleable.GraphPSV_finishYellowLine, DEFAULT_FINISH_YELLOW_LINE)
        startRedL = typedArray.getInt(R.styleable.GraphPSV_startRedLine, DEFAULT_START_RED_LINE)
        finishRedL = typedArray.getInt(R.styleable.GraphPSV_finishRedLine, DEFAULT_FINISH_RED_LINE)

        x1d = typedArray.getInt(R.styleable.GraphPSV_x1d, DEFAULT_XD)
        x1h = typedArray.getInt(R.styleable.GraphPSV_x1h, DEFAULT_XH)
        y1 = typedArray.getInt(R.styleable.GraphPSV_y1, DEFAULT_Y)

        x2d = typedArray.getInt(R.styleable.GraphPSV_x2d, DEFAULT_XD)
        x2h = typedArray.getInt(R.styleable.GraphPSV_x2h, DEFAULT_XH)
        y2 = typedArray.getInt(R.styleable.GraphPSV_y2, DEFAULT_Y)

        x3d = typedArray.getInt(R.styleable.GraphPSV_x3d, DEFAULT_XD)
        x3h = typedArray.getInt(R.styleable.GraphPSV_x3h, DEFAULT_XH)
        y3 = typedArray.getInt(R.styleable.GraphPSV_y3, DEFAULT_Y)

        x4d = typedArray.getInt(R.styleable.GraphPSV_x4d, DEFAULT_XD)
        x4h = typedArray.getInt(R.styleable.GraphPSV_x4h, DEFAULT_XH)
        y4 = typedArray.getInt(R.styleable.GraphPSV_y4, DEFAULT_Y)

        x5d = typedArray.getInt(R.styleable.GraphPSV_x5d, DEFAULT_XD)
        x5h = typedArray.getInt(R.styleable.GraphPSV_x5h, DEFAULT_XH)
        y5 = typedArray.getInt(R.styleable.GraphPSV_y5, DEFAULT_Y)

        x6d = typedArray.getInt(R.styleable.GraphPSV_x6d, DEFAULT_XD)
        x6h = typedArray.getInt(R.styleable.GraphPSV_x6h, DEFAULT_XH)
        y6 = typedArray.getInt(R.styleable.GraphPSV_y6, DEFAULT_Y)

        x7d = typedArray.getInt(R.styleable.GraphPSV_x7d, DEFAULT_XD)
        x7h = typedArray.getInt(R.styleable.GraphPSV_x7h, DEFAULT_XH)
        y7 = typedArray.getInt(R.styleable.GraphPSV_y7, DEFAULT_Y)

        x8d = typedArray.getInt(R.styleable.GraphPSV_x8d, DEFAULT_XD)
        x8h = typedArray.getInt(R.styleable.GraphPSV_x8h, DEFAULT_XH)
        y8 = typedArray.getInt(R.styleable.GraphPSV_y8, DEFAULT_Y)

        x9d = typedArray.getInt(R.styleable.GraphPSV_x9d, DEFAULT_XD)
        x9h = typedArray.getInt(R.styleable.GraphPSV_x9h, DEFAULT_XH)
        y9 = typedArray.getInt(R.styleable.GraphPSV_y9, DEFAULT_Y)

        x10d = typedArray.getInt(R.styleable.GraphPSV_x10d, DEFAULT_XD)
        x10h = typedArray.getInt(R.styleable.GraphPSV_x10h, DEFAULT_XH)
        y10 = typedArray.getInt(R.styleable.GraphPSV_y10, DEFAULT_Y)

        x11d = typedArray.getInt(R.styleable.GraphPSV_x11d, DEFAULT_XD)
        x11h = typedArray.getInt(R.styleable.GraphPSV_x11h, DEFAULT_XH)
        y11 = typedArray.getInt(R.styleable.GraphPSV_y11, DEFAULT_Y)

        x12d = typedArray.getInt(R.styleable.GraphPSV_x12d, DEFAULT_XD)
        x12h = typedArray.getInt(R.styleable.GraphPSV_x12h, DEFAULT_XH)
        y12 = typedArray.getInt(R.styleable.GraphPSV_y12, DEFAULT_Y)

        x13d = typedArray.getInt(R.styleable.GraphPSV_x13d, DEFAULT_XD)
        x13h = typedArray.getInt(R.styleable.GraphPSV_x13h, DEFAULT_XH)
        y13 = typedArray.getInt(R.styleable.GraphPSV_y13, DEFAULT_Y)

        x14d = typedArray.getInt(R.styleable.GraphPSV_x14d, DEFAULT_XD)
        x14h = typedArray.getInt(R.styleable.GraphPSV_x14h, DEFAULT_XH)
        y14 = typedArray.getInt(R.styleable.GraphPSV_y14, DEFAULT_Y)

        x15d = typedArray.getInt(R.styleable.GraphPSV_x15d, DEFAULT_XD)
        x15h = typedArray.getInt(R.styleable.GraphPSV_x15h, DEFAULT_XH)
        y15 = typedArray.getInt(R.styleable.GraphPSV_y15, DEFAULT_Y)

        x16d = typedArray.getInt(R.styleable.GraphPSV_x16d, DEFAULT_XD)
        x16h = typedArray.getInt(R.styleable.GraphPSV_x16h, DEFAULT_XH)
        y16 = typedArray.getInt(R.styleable.GraphPSV_y16, DEFAULT_Y)

        x17d = typedArray.getInt(R.styleable.GraphPSV_x17d, DEFAULT_XD)
        x17h = typedArray.getInt(R.styleable.GraphPSV_x17h, DEFAULT_XH)
        y17 = typedArray.getInt(R.styleable.GraphPSV_y17, DEFAULT_Y)

        x18d = typedArray.getInt(R.styleable.GraphPSV_x18d, DEFAULT_XD)
        x18h = typedArray.getInt(R.styleable.GraphPSV_x18h, DEFAULT_XH)
        y18 = typedArray.getInt(R.styleable.GraphPSV_y18, DEFAULT_Y)

        x19d = typedArray.getInt(R.styleable.GraphPSV_x19d, DEFAULT_XD)
        x19h = typedArray.getInt(R.styleable.GraphPSV_x19h, DEFAULT_XH)
        y19 = typedArray.getInt(R.styleable.GraphPSV_y19, DEFAULT_Y)

        x20d = typedArray.getInt(R.styleable.GraphPSV_x20d, DEFAULT_XD)
        x20h = typedArray.getInt(R.styleable.GraphPSV_x20h, DEFAULT_XH)
        y20 = typedArray.getInt(R.styleable.GraphPSV_y20, DEFAULT_Y)

        x21d = typedArray.getInt(R.styleable.GraphPSV_x21d, DEFAULT_XD)
        x21h = typedArray.getInt(R.styleable.GraphPSV_x21h, DEFAULT_XH)
        y21 = typedArray.getInt(R.styleable.GraphPSV_y21, DEFAULT_Y)

        x22d = typedArray.getInt(R.styleable.GraphPSV_x22d, DEFAULT_XD)
        x22h = typedArray.getInt(R.styleable.GraphPSV_x22h, DEFAULT_XH)
        y22 = typedArray.getInt(R.styleable.GraphPSV_y22, DEFAULT_Y)

        x23d = typedArray.getInt(R.styleable.GraphPSV_x23d, DEFAULT_XD)
        x23h = typedArray.getInt(R.styleable.GraphPSV_x23h, DEFAULT_XH)
        y23 = typedArray.getInt(R.styleable.GraphPSV_y23, DEFAULT_Y)

        x24d = typedArray.getInt(R.styleable.GraphPSV_x24d, DEFAULT_XD)
        x24h = typedArray.getInt(R.styleable.GraphPSV_x24h, DEFAULT_XH)
        y24 = typedArray.getInt(R.styleable.GraphPSV_y24, DEFAULT_Y)

        x25d = typedArray.getInt(R.styleable.GraphPSV_x25d, DEFAULT_XD)
        x25h = typedArray.getInt(R.styleable.GraphPSV_x25h, DEFAULT_XH)
        y25 = typedArray.getInt(R.styleable.GraphPSV_y25, DEFAULT_Y)

        x26d = typedArray.getInt(R.styleable.GraphPSV_x26d, DEFAULT_XD)
        x26h = typedArray.getInt(R.styleable.GraphPSV_x26h, DEFAULT_XH)
        y26 = typedArray.getInt(R.styleable.GraphPSV_y26, DEFAULT_Y)

        x27d = typedArray.getInt(R.styleable.GraphPSV_x27d, DEFAULT_XD)
        x27h = typedArray.getInt(R.styleable.GraphPSV_x27h, DEFAULT_XH)
        y27 = typedArray.getInt(R.styleable.GraphPSV_y27, DEFAULT_Y)

        x28d = typedArray.getInt(R.styleable.GraphPSV_x28d, DEFAULT_XD)
        x28h = typedArray.getInt(R.styleable.GraphPSV_x28h, DEFAULT_XH)
        y28 = typedArray.getInt(R.styleable.GraphPSV_y28, DEFAULT_Y)

        x29d = typedArray.getInt(R.styleable.GraphPSV_x29d, DEFAULT_XD)
        x29h = typedArray.getInt(R.styleable.GraphPSV_x29h, DEFAULT_XH)
        y29 = typedArray.getInt(R.styleable.GraphPSV_y29, DEFAULT_Y)

        x30d = typedArray.getInt(R.styleable.GraphPSV_x30d, DEFAULT_XD)
        x30h = typedArray.getInt(R.styleable.GraphPSV_x30h, DEFAULT_XH)
        y30 = typedArray.getInt(R.styleable.GraphPSV_y30, DEFAULT_Y)

        x31d = typedArray.getInt(R.styleable.GraphPSV_x31d, DEFAULT_XD)
        x31h = typedArray.getInt(R.styleable.GraphPSV_x31h, DEFAULT_XH)
        y31 = typedArray.getInt(R.styleable.GraphPSV_y31, DEFAULT_Y)

        x32d = typedArray.getInt(R.styleable.GraphPSV_x32d, DEFAULT_XD)
        x32h = typedArray.getInt(R.styleable.GraphPSV_x32h, DEFAULT_XH)
        y32 = typedArray.getInt(R.styleable.GraphPSV_y32, DEFAULT_Y)

        x33d = typedArray.getInt(R.styleable.GraphPSV_x33d, DEFAULT_XD)
        x33h = typedArray.getInt(R.styleable.GraphPSV_x33h, DEFAULT_XH)
        y33 = typedArray.getInt(R.styleable.GraphPSV_y33, DEFAULT_Y)

        x34d = typedArray.getInt(R.styleable.GraphPSV_x34d, DEFAULT_XD)
        x34h = typedArray.getInt(R.styleable.GraphPSV_x34h, DEFAULT_XH)
        y34 = typedArray.getInt(R.styleable.GraphPSV_y34, DEFAULT_Y)

        x35d = typedArray.getInt(R.styleable.GraphPSV_x35d, DEFAULT_XD)
        x35h = typedArray.getInt(R.styleable.GraphPSV_x35h, DEFAULT_XH)
        y35 = typedArray.getInt(R.styleable.GraphPSV_y35, DEFAULT_Y)

        x36d = typedArray.getInt(R.styleable.GraphPSV_x36d, DEFAULT_XD)
        x36h = typedArray.getInt(R.styleable.GraphPSV_x36h, DEFAULT_XH)
        y36 = typedArray.getInt(R.styleable.GraphPSV_y36, DEFAULT_Y)

        x37d = typedArray.getInt(R.styleable.GraphPSV_x37d, DEFAULT_XD)
        x37h = typedArray.getInt(R.styleable.GraphPSV_x37h, DEFAULT_XH)
        y37 = typedArray.getInt(R.styleable.GraphPSV_y37, DEFAULT_Y)

        x38d = typedArray.getInt(R.styleable.GraphPSV_x38d, DEFAULT_XD)
        x38h = typedArray.getInt(R.styleable.GraphPSV_x38h, DEFAULT_XH)
        y38 = typedArray.getInt(R.styleable.GraphPSV_y38, DEFAULT_Y)

        x39d = typedArray.getInt(R.styleable.GraphPSV_x39d, DEFAULT_XD)
        x39h = typedArray.getInt(R.styleable.GraphPSV_x39h, DEFAULT_XH)
        y39 = typedArray.getInt(R.styleable.GraphPSV_y39, DEFAULT_Y)

        x40d = typedArray.getInt(R.styleable.GraphPSV_x40d, DEFAULT_XD)
        x40h = typedArray.getInt(R.styleable.GraphPSV_x40h, DEFAULT_XH)
        y40 = typedArray.getInt(R.styleable.GraphPSV_y40, DEFAULT_Y)

        x41d = typedArray.getInt(R.styleable.GraphPSV_x41d, DEFAULT_XD)
        x41h = typedArray.getInt(R.styleable.GraphPSV_x41h, DEFAULT_XH)
        y41 = typedArray.getInt(R.styleable.GraphPSV_y41, DEFAULT_Y)

        x42d = typedArray.getInt(R.styleable.GraphPSV_x42d, DEFAULT_XD)
        x42h = typedArray.getInt(R.styleable.GraphPSV_x42h, DEFAULT_XH)
        y42 = typedArray.getInt(R.styleable.GraphPSV_y42, DEFAULT_Y)

        x43d = typedArray.getInt(R.styleable.GraphPSV_x43d, DEFAULT_XD)
        x43h = typedArray.getInt(R.styleable.GraphPSV_x43h, DEFAULT_XH)
        y43 = typedArray.getInt(R.styleable.GraphPSV_y43, DEFAULT_Y)

        x44d = typedArray.getInt(R.styleable.GraphPSV_x44d, DEFAULT_XD)
        x44h = typedArray.getInt(R.styleable.GraphPSV_x44h, DEFAULT_XH)
        y44 = typedArray.getInt(R.styleable.GraphPSV_y44, DEFAULT_Y)

        x45d = typedArray.getInt(R.styleable.GraphPSV_x45d, DEFAULT_XD)
        x45h = typedArray.getInt(R.styleable.GraphPSV_x45h, DEFAULT_XH)
        y45 = typedArray.getInt(R.styleable.GraphPSV_y45, DEFAULT_Y)

        x46d = typedArray.getInt(R.styleable.GraphPSV_x46d, DEFAULT_XD)
        x46h = typedArray.getInt(R.styleable.GraphPSV_x46h, DEFAULT_XH)
        y46 = typedArray.getInt(R.styleable.GraphPSV_y46, DEFAULT_Y)

        x47d = typedArray.getInt(R.styleable.GraphPSV_x47d, DEFAULT_XD)
        x47h = typedArray.getInt(R.styleable.GraphPSV_x47h, DEFAULT_XH)
        y47 = typedArray.getInt(R.styleable.GraphPSV_y47, DEFAULT_Y)

        x48d = typedArray.getInt(R.styleable.GraphPSV_x48d, DEFAULT_XD)
        x48h = typedArray.getInt(R.styleable.GraphPSV_x48h, DEFAULT_XH)
        y48 = typedArray.getInt(R.styleable.GraphPSV_y48, DEFAULT_Y)

        x49d = typedArray.getInt(R.styleable.GraphPSV_x49d, DEFAULT_XD)
        x49h = typedArray.getInt(R.styleable.GraphPSV_x49h, DEFAULT_XH)
        y49 = typedArray.getInt(R.styleable.GraphPSV_y49, DEFAULT_Y)

        x50d = typedArray.getInt(R.styleable.GraphPSV_x50d, DEFAULT_XD)
        x50h = typedArray.getInt(R.styleable.GraphPSV_x50h, DEFAULT_XH)
        y50 = typedArray.getInt(R.styleable.GraphPSV_y50, DEFAULT_Y)

        x51d = typedArray.getInt(R.styleable.GraphPSV_x51d, DEFAULT_XD)
        x51h = typedArray.getInt(R.styleable.GraphPSV_x51h, DEFAULT_XH)
        y51 = typedArray.getInt(R.styleable.GraphPSV_y51, DEFAULT_Y)

        x52d = typedArray.getInt(R.styleable.GraphPSV_x52d, DEFAULT_XD)
        x52h = typedArray.getInt(R.styleable.GraphPSV_x52h, DEFAULT_XH)
        y52 = typedArray.getInt(R.styleable.GraphPSV_y52, DEFAULT_Y)

        x53d = typedArray.getInt(R.styleable.GraphPSV_x53d, DEFAULT_XD)
        x53h = typedArray.getInt(R.styleable.GraphPSV_x53h, DEFAULT_XH)
        y53 = typedArray.getInt(R.styleable.GraphPSV_y53, DEFAULT_Y)

        x54d = typedArray.getInt(R.styleable.GraphPSV_x54d, DEFAULT_XD)
        x54h = typedArray.getInt(R.styleable.GraphPSV_x54h, DEFAULT_XH)
        y54 = typedArray.getInt(R.styleable.GraphPSV_y54, DEFAULT_Y)

        x55d = typedArray.getInt(R.styleable.GraphPSV_x55d, DEFAULT_XD)
        x55h = typedArray.getInt(R.styleable.GraphPSV_x55h, DEFAULT_XH)
        y55 = typedArray.getInt(R.styleable.GraphPSV_y55, DEFAULT_Y)

        x56d = typedArray.getInt(R.styleable.GraphPSV_x56d, DEFAULT_XD)
        x56h = typedArray.getInt(R.styleable.GraphPSV_x56h, DEFAULT_XH)
        y56 = typedArray.getInt(R.styleable.GraphPSV_y56, DEFAULT_Y)

        x57d = typedArray.getInt(R.styleable.GraphPSV_x57d, DEFAULT_XD)
        x57h = typedArray.getInt(R.styleable.GraphPSV_x57h, DEFAULT_XH)
        y57 = typedArray.getInt(R.styleable.GraphPSV_y57, DEFAULT_Y)

        x58d = typedArray.getInt(R.styleable.GraphPSV_x58d, DEFAULT_XD)
        x58h = typedArray.getInt(R.styleable.GraphPSV_x58h, DEFAULT_XH)
        y58 = typedArray.getInt(R.styleable.GraphPSV_y58, DEFAULT_Y)

        x59d = typedArray.getInt(R.styleable.GraphPSV_x59d, DEFAULT_XD)
        x59h = typedArray.getInt(R.styleable.GraphPSV_x59h, DEFAULT_XH)
        y59 = typedArray.getInt(R.styleable.GraphPSV_y59, DEFAULT_Y)

        x60d = typedArray.getInt(R.styleable.GraphPSV_x60d, DEFAULT_XD)
        x60h = typedArray.getInt(R.styleable.GraphPSV_x60h, DEFAULT_XH)
        y60 = typedArray.getInt(R.styleable.GraphPSV_y60, DEFAULT_Y)

        x61d = typedArray.getInt(R.styleable.GraphPSV_x61d, DEFAULT_XD)
        x61h = typedArray.getInt(R.styleable.GraphPSV_x61h, DEFAULT_XH)
        y61 = typedArray.getInt(R.styleable.GraphPSV_y61, DEFAULT_Y)

        x62d = typedArray.getInt(R.styleable.GraphPSV_x62d, DEFAULT_XD)
        x62h = typedArray.getInt(R.styleable.GraphPSV_x62h, DEFAULT_XH)
        y62 = typedArray.getInt(R.styleable.GraphPSV_y62, DEFAULT_Y)

        x63d = typedArray.getInt(R.styleable.GraphPSV_x63d, DEFAULT_XD)
        x63h = typedArray.getInt(R.styleable.GraphPSV_x63h, DEFAULT_XH)
        y63 = typedArray.getInt(R.styleable.GraphPSV_y63, DEFAULT_Y)

        x64d = typedArray.getInt(R.styleable.GraphPSV_x64d, DEFAULT_XD)
        x64h = typedArray.getInt(R.styleable.GraphPSV_x64h, DEFAULT_XH)
        y64 = typedArray.getInt(R.styleable.GraphPSV_y64, DEFAULT_Y)

        x65d = typedArray.getInt(R.styleable.GraphPSV_x65d, DEFAULT_XD)
        x65h = typedArray.getInt(R.styleable.GraphPSV_x65h, DEFAULT_XH)
        y65 = typedArray.getInt(R.styleable.GraphPSV_y65, DEFAULT_Y)

        x66d = typedArray.getInt(R.styleable.GraphPSV_x66d, DEFAULT_XD)
        x66h = typedArray.getInt(R.styleable.GraphPSV_x66h, DEFAULT_XH)
        y66 = typedArray.getInt(R.styleable.GraphPSV_y66, DEFAULT_Y)

        x67d = typedArray.getInt(R.styleable.GraphPSV_x67d, DEFAULT_XD)
        x67h = typedArray.getInt(R.styleable.GraphPSV_x67h, DEFAULT_XH)
        y67 = typedArray.getInt(R.styleable.GraphPSV_y67, DEFAULT_Y)

        x68d = typedArray.getInt(R.styleable.GraphPSV_x68d, DEFAULT_XD)
        x68h = typedArray.getInt(R.styleable.GraphPSV_x68h, DEFAULT_XH)
        y68 = typedArray.getInt(R.styleable.GraphPSV_y68, DEFAULT_Y)

        x69d = typedArray.getInt(R.styleable.GraphPSV_x69d, DEFAULT_XD)
        x69h = typedArray.getInt(R.styleable.GraphPSV_x69h, DEFAULT_XH)
        y69 = typedArray.getInt(R.styleable.GraphPSV_y69, DEFAULT_Y)

        x70d = typedArray.getInt(R.styleable.GraphPSV_x70d, DEFAULT_XD)
        x70h = typedArray.getInt(R.styleable.GraphPSV_x70h, DEFAULT_XH)
        y70 = typedArray.getInt(R.styleable.GraphPSV_y70, DEFAULT_Y)

        sizePaint1 = typedArray.getInt(R.styleable.GraphPSV_sizePoint, DEFAULT_SIZE_POINT)
        typedArray.recycle()
    }

    /**
     * drawGraphLine рисуем разметку графика
     */
    private fun drawGraphLine(canvas: Canvas?){
        paint.color = fonColor
        canvas?.drawRect(0f,0f,sizeW.toFloat(),sizeH.toFloat(),paint)
        val dW = sizeW/numberOfDays.toFloat() //дэльта ширины
        val dH = sizeH/numberOfPFM.toFloat() // дэльта высоты
        if(colorGraph== YES_SPAN) {
            color3(canvas, dH*startGreenL, dH * finishGreenL, DEFAULT_COLOR_GREEN)
            color3(canvas, dH * startYellowL, dH * finishYellowL, DEFAULT_COLOR_YELLOW)
            color3(canvas, dH * startRedL, dH * finishRedL, DEFAULT_COLOR_RED)
        }
        paint.color = lineColor


        for (i in 1..numberOfDays)  // дата
            canvas?.drawLine(dW * i, 0f, dW * i, sizeH.toFloat(), paint)

        for (i in 1..numberOfPFM) // ПСВ
            canvas?.drawLine(0f,dH*i, sizeW.toFloat(), dH*i,paint)

        paint.textSize = textSize
        paint.color = textColor
        var startDaysN = startDays
        for (i in 1..numberOfDays) {
            canvas?.drawText(
                startDaysN.toString(),
                (dW * i) - (dW / 2 + textSize / 2),
                sizeH.toFloat() - 3,
                paint
            )
            startDaysN++
        }
        var startPFMN = startPFM
        for (i in numberOfPFM downTo 1) {
            canvas?.drawText(
                startPFMN.toString(),
                3f,
                (i*dH)-(dH/2-textSize/2),
                paint
            )
            startPFMN+=intervalPFM
        }
    }

    /**
     * color3 Рисуем 3 области показаний
     *  @param top Верх закращенной области
     *  @param bottom Низ закрашенной области
     *  @param color Цвет области
     */
    private fun color3(canvas: Canvas?, top:Float, bottom:Float, color: Int){
        paint.color = color
        canvas?.drawRect(0f,top,sizeW.toFloat(),bottom,paint)
    }


//    fun ttttttt():Array<Pair<Float, Float>> {
//        val poi1=Pair(toDays(1,10,0),toPFM(200))
//        val poi2=Pair(toDays(1,18,0),toPFM(210))
//        val poi3=Pair(toDays(2,10,0),toPFM(200))
//        val poi4=Pair(toDays(2,18,0),toPFM(220))
//        val poi5=Pair(toDays(3,8,0),toPFM(221))
//        return arrayOf(poi1, poi2, poi3, poi4, poi5)
//    }

    /**
     * drawGraph рисуем график
     */
    fun drawGraph(canvas: Canvas?){
        paint.color = Color.BLUE
        paint.strokeWidth = 5f



        // точки графика
//        val poi1=Pair(toDays(1,10,0),toPFM(200))
//        val poi2=Pair(toDays(1,18,0),toPFM(210))
//        val poi3=Pair(toDays(2,10,0),toPFM(200))
//        val poi4=Pair(toDays(2,18,0),toPFM(220))
//        val poi5=Pair(toDays(3,8,0),toPFM(221))
//        val poi6=Pair(toDays(3,18,0),toPFM(220))
//        val poi7=Pair(toDays(4,8,0),toPFM(200))
//        val poi8=Pair(toDays(4,18,0),toPFM(250))
//        val poi9=Pair(toDays(5,8,0),toPFM(210))
//        val poi10=Pair(toDays(5,18,0),toPFM(190))

        val poiArray2 = arrayOf(Pair(toDays(x1d,x1h,0),toPFM(y1)),
       Pair(toDays(x2d,x2h,0),toPFM(y2)),
            Pair(toDays(x3d,x3h,0),toPFM(y3)),
        Pair(toDays(x4d,x4h,0),toPFM(y4)),
        Pair(toDays(x5d,x5h,0),toPFM(y5)),
        Pair(toDays(x6d,x6h,0),toPFM(y6)),
        Pair(toDays(x7d,x7h,0),toPFM(y7)),
        Pair(toDays(x8d,x8h,0),toPFM(y8)),
        Pair(toDays(x9d,x9h,0),toPFM(y9)),
        Pair(toDays(x10d,x10h,0),toPFM(y10)),
        Pair(toDays(x11d,x11h,0),toPFM(y11)),
        Pair(toDays(x12d,x12h,0),toPFM(y12)),
        Pair(toDays(x13d,x13h,0),toPFM(y13)),
        Pair(toDays(x14d,x14h,0),toPFM(y14)),
        Pair(toDays(x15d,x15h,0),toPFM(y15)),
        Pair(toDays(x16d,x16h,0),toPFM(y16)),
        Pair(toDays(x17d,x17h,0),toPFM(y17)),
            Pair(toDays(x18d,x18h,0),toPFM(y18)),
            Pair(toDays(x19d,x19h,0),toPFM(y19)),
            Pair(toDays(x20d,x20h,0),toPFM(y20)),
        Pair(toDays(x21d,x21h,0),toPFM(y21)),
       Pair(toDays(x22d,x22h,0),toPFM(y22)),
        Pair(toDays(x23d,x23h,0),toPFM(y23)),
        Pair(toDays(x24d,x24h,0),toPFM(y24)),
       Pair(toDays(x25d,x25h,0),toPFM(y25)),
        Pair(toDays(x26d,x26h,0),toPFM(y26)),
       Pair(toDays(x27d,x27h,0),toPFM(y27)),
       Pair(toDays(x28d,x28h,0),toPFM(y28)),
        Pair(toDays(x29d,x29h,0),toPFM(y29)),
        Pair(toDays(x30d,x30h,0),toPFM(y30)),
        Pair(toDays(x31d,x31h,0),toPFM(y31)),
       Pair(toDays(x32d,x32h,0),toPFM(y32)),
       Pair(toDays(x33d,x33h,0),toPFM(y33)),
       Pair(toDays(x34d,x34h,0),toPFM(y34)),
       Pair(toDays(x35d,x35h,0),toPFM(y35)),
       Pair(toDays(x36d,x36h,0),toPFM(y36)),
       Pair(toDays(x37d,x37h,0),toPFM(y37)),
       Pair(toDays(x38d,x38h,0),toPFM(y38)),
        Pair(toDays(x39d,x39h,0),toPFM(y39)),
     Pair(toDays(x40d,x40h,0),toPFM(y40)),
       Pair(toDays(x41d,x41h,0),toPFM(y41)),
      Pair(toDays(x42d,x42h,0),toPFM(y42)),
       Pair(toDays(x43d,x43h,0),toPFM(y43)),
     Pair(toDays(x44d,x44h,0),toPFM(y44)),
      Pair(toDays(x45d,x45h,0),toPFM(y45)),
       Pair(toDays(x46d,x46h,0),toPFM(y46)),
       Pair(toDays(x47d,x47h,0),toPFM(y47)),
      Pair(toDays(x48d,x48h,0),toPFM(y48)),
      Pair(toDays(x49d,x49h,0),toPFM(y49)),
       Pair(toDays(x50d,x50h,0),toPFM(y50)),
       Pair(toDays(x51d,x51h,0),toPFM(y51)),
     Pair(toDays(x52d,x52h,0),toPFM(y52)),
      Pair(toDays(x53d,x53h,0),toPFM(y53)),
       Pair(toDays(x54d,x54h,0),toPFM(y54)),
      Pair(toDays(x55d,x55h,0),toPFM(y55)),
      Pair(toDays(x56d,x56h,0),toPFM(y56)),
       Pair(toDays(x57d,x57h,0),toPFM(y57)),
        Pair(toDays(x58d,x58h,0),toPFM(y58)),
       Pair(toDays(x59d,x59h,0),toPFM(y59)),
        Pair(toDays(x60d,x60h,0),toPFM(y60)),
       Pair(toDays(x61d,x61h,0),toPFM(y61)),
     Pair(toDays(x62d,x62h,0),toPFM(y62)),
        Pair(toDays(x63d,x63h,0),toPFM(y63)),
       Pair(toDays(x64d,x64h,0),toPFM(y64)),
        Pair(toDays(x65d,x65h,0),toPFM(y65)),
        Pair(toDays(x66d,x66h,0),toPFM(y66)),
       Pair(toDays(x67d,x67h,0),toPFM(y67)),
        Pair(toDays(x68d,x68h,0),toPFM(y68)),
       Pair(toDays(x69d,x69h,0),toPFM(y69)),
    Pair(toDays(x70d,x70h,0),toPFM(y70))
        )

        val poisArray = Array<Pair<Float,Float>>(sizePaint1) {poiArray2[0]}
//
        for (i in 0 until sizePaint1)
            poisArray[i] =poiArray2[i]

        // массив точек графика
//       val pois = pointsToLine(arrayOf(poi1, poi2, poi3, poi4, poi5, poi6, poi7, poi8, poi9, poi10,
//           poi11, poi12, poi13, poi14, poi15, poi16, poi17, poi18, poi19, poi20,
//           poi21, poi22, poi23, poi24, poi25, poi26, poi27, poi28, poi29, poi30,
//           poi31, poi32, poi33, poi34, poi35, poi36, poi37, poi38, poi39, poi40,
//           poi41, poi42, poi43, poi44, poi45, poi46, poi47, poi48, poi49, poi50,
//           poi51, poi52, poi53, poi54, poi55, poi56, poi57, poi58, poi59, poi60,
//           poi61, poi62, poi63, poi64, poi65, poi66, poi67, poi68, poi69, poi70))


        val pois = pointsToLine(poisArray)
        // рисуем точки
        for (i in pois.indices)
            canvas?.drawPoint(pois[i].first, pois[i].second,paint)

        // рисуем линии
        if (pois.size >=2) { // проверка на наличия 2 точек. если точка 1 то и линию не начертить
           paint.strokeWidth = 2f
            var a =0
            while (a != pois.size) {
                canvas?.drawLine(
                    pois[a].first,
                    pois[a].second,
                    pois[a + 1].first,
                    pois[a + 1].second,
                    paint
                )
                a += 2
            }
        }
    }
    /**
     * toPFM перевод показаний в координаты
     * @param x показания пукфлиметра
     */
    fun toPFM(x:Int):Float{
        val dH = sizeH/numberOfPFM.toFloat() // дэльта высоты
        val maxWindow = startPFM + (intervalPFM*numberOfPFM)-intervalPFM
        return ((maxWindow - x)/intervalPFM+1 ) * dH
    }

    /**
     * toDays перевод показаний в координаты
     * @param day день
     * @param hour час
     * @param minute минут
     */
    fun toDays(day: Int, hour:Int, minute:Int):Float{
        val dW = sizeW/numberOfDays.toFloat() //дэльта ширины
        val h24d = (h24(hour,minute)*dW)/100
        return dW*(day-startDays+1)+h24d-dW
    }

    // Запоминаем колличество дней.
    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putInt("numberOfDaysState",numberOfDays)
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    // Вспоминаем колличество дней
    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state
        if (viewState is Bundle) {
            numberOfDays = viewState.getInt("numberOfDaysState",DEFAULT_NUMBER_OF_DAYS)
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }
}