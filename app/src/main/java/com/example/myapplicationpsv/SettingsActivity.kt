package com.example.myapplicationpsv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.myapplicationpsv.myFun.k50

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.strip.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            // зеленая настройка
           // val greenPreference = preferenceManager.findPreference<Preference>("settings_top_of_the_green_line")
            val lp3 = preferenceManager.findPreference<Preference>("settings_top_of_the_green_line")
            // Желтая граница
           // val yellowPreference = preferenceManager.findPreference<Preference>("settings_top_of_the_yellow_line")
            val lp2 = preferenceManager.findPreference<Preference>("settings_top_of_the_yellow_line")
            // красна граница
           // val redPreference = preferenceManager.findPreference<Preference>("settings_top_of_the_red_line")
            val lp1 = preferenceManager.findPreference<Preference>("settings_top_of_the_red_line")
            // нижняя граница
         //   val limitRedPreference = preferenceManager.findPreference<Preference>("settings_lower_limit_of_the_red")
            val lp0=preferenceManager.findPreference<Preference>("settings_lower_limit_of_the_red")


            // разные настройки в начало
            val differentSettings = PreferenceManager.getDefaultSharedPreferences(context)
           // var customPrefGreen = differentSettings.getInt("settings_top_of_the_green_line", 0)
           // var customPrefYellw = differentSettings.getInt("settings_top_of_the_yellow_line", 0)
           // var customPrefRed = differentSettings.getInt("settings_top_of_the_red_line", 0)
          //  var customLimitRed = differentSettings.getInt("settings_lower_limit_of_the_red", 0)






            var n0 = 10
            var p0 = differentSettings.getInt("settings_lower_limit_of_the_red", 0)
            var p1 = differentSettings.getInt("settings_top_of_the_red_line", 0)
            var p2 = differentSettings.getInt("settings_top_of_the_yellow_line", 0)
            var p3 = differentSettings.getInt("settings_top_of_the_green_line", 0)
            var t0 = n0+p0*10
            var n1 = t0+10
            var t1 = n1+p1*10
            var n2 = t1+10
            var t2 = n2+p2*10
            var n3 = t2+10
            var t3 = n3+p3*10
            var max3 = 70 - n3/10
            var max2 = (t3-n2-10)/10
            var max1 = (t2-n1-10)/10
            var max0 = (t1-10)/10


            lp3?.title = t3.toString()//k50(customPrefGreen).toString()
            lp2?.title = t2.toString()//k50(customPrefYellw).toString()
            lp1?.title = t1.toString()//k50(customPrefRed+1).toString()
            lp0?.title = t0.toString()// k50(customLimitRed).toString()


            // зеленая работа
            lp3?.setOnPreferenceClickListener {
                val prefGet =PreferenceManager.getDefaultSharedPreferences(context)
                //var i =prefGet.getInt("settings_top_of_the_green_line", 250 )
                lp3.title = t3.toString()//k50(customPrefGreen).toString()

                val mDialogView =LayoutInflater.from(context).inflate(R.layout.strip,null)
                val mBillder = context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setView(mDialogView)
                        .setPositiveButton("Ok"){dialog, id ->
                            lp3.title = t3.toString()//k50(customPrefGreen).toString()
                            val pref = PreferenceManager.getDefaultSharedPreferences(context).edit()
                            pref.putInt("settings_top_of_the_green_line", p3)
                            pref.apply()
                        }
                        .setNegativeButton("Cancel"){dialog, id ->
                        }.show()
                }

                if (mBillder != null) {
                    max3 = 70 - n3/10
                    max2 = (t3-n2-10)/10
                    max1 = (t2-n1-10)/10
                    max0 = (t1-10)/10
                    mBillder.seekBarStrip.progress=p3//customPrefGreen
                    mBillder.textStrip.text = t3.toString()//k50(customPrefGreen).toString()
                    mBillder.seekBarStrip.max = max3

                }

                mBillder?.seekBarStrip?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        p3 = progress //customPrefGreen = progress
                        t3 = n3 + p3 *10
                        mBillder.textStrip.text =  t3.toString()//k50(progress).toString()


                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) { }
                    override fun onStopTrackingTouch(seekBar: SeekBar?) { }
                })
                true
            }

            // желтая работа
            lp2?.setOnPreferenceClickListener {
               // val prefGet =PreferenceManager.getDefaultSharedPreferences(context)
               // val i =prefGet.getInt("settings_top_of_the_yellow_line", 20 )
               lp2.title = t2.toString()//k50(customPrefYellw).toString()

                val mDialogView =LayoutInflater.from(context).inflate(R.layout.strip,null)
                val mBillder = context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setView(mDialogView)
                        .setPositiveButton("Ok"){dialog, id ->
                            lp2.title = t2.toString()//k50(customPrefYellw).toString()
                            val pref = PreferenceManager.getDefaultSharedPreferences(context).edit()
                            pref.putInt("settings_top_of_the_yellow_line", p2)
                            pref.apply()
                        }
                        .setNegativeButton("Cancel"){dialog, id ->
                        }.show()
                }

                if (mBillder != null) {
                    max3 = 70 - n3/10
                    max2 = (t3-n2-10)/10
                    max1 = (t2-n1-10)/10
                    max0 = (t1-10)/10
                    mBillder.textStrip.text = t2.toString()//k50(customPrefYellw).toString()
                    mBillder.seekBarStrip.progress=p2//customPrefYellw
                    mBillder.seekBarStrip.max = max2
                }

                mBillder?.seekBarStrip?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                       // customPrefYellw = progress
                        p2 = progress
                        t2 = n2+p2*10
                        n3 = t2+10
                        mBillder.textStrip.text =  t2.toString()//k50(progress).toString()

                       // i = progress
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) { }
                    override fun onStopTrackingTouch(seekBar: SeekBar?) { }
                })
                true
            }

            // красная работа
            lp1?.setOnPreferenceClickListener {
              //  val prefGet =PreferenceManager.getDefaultSharedPreferences(context)
               // var i =prefGet.getInt("settings_top_of_the_red_line", 15 )
               // val iLimitRed =prefGet.getInt("settings_lower_limit_of_the_red", 10 )
               // var iYelloy =prefGet.getInt("settings_top_of_the_yellow_line", 20 )
                lp1.title = t1.toString()//k50(customPrefRed+1).toString()
                val mDialogView =LayoutInflater.from(context).inflate(R.layout.strip,null)
                val mBillder = context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setView(mDialogView)
                        .setPositiveButton("Ok"){dialog, id ->
                            lp1.title = t1.toString()//k50(customPrefRed+1).toString()
                            val pref = PreferenceManager.getDefaultSharedPreferences(context).edit()
                            pref.putInt("settings_top_of_the_red_line", p1)
                            pref.apply()
                        }
                        .setNegativeButton("Cancel"){dialog, id ->
                        }.show()
                }

                if (mBillder != null) {
                   // n1 = t0+10
                    max3 = 70 - n3/10
                    max2 = (t3-n2-10)/10
                    max1 = (t2-n1-10)/10
                    max0 = (t1-10)/10
                    mBillder.textStrip.text = t1.toString()//k50(customPrefRed+1).toString()
                    mBillder.seekBarStrip.progress=p1//customPrefRed
                    mBillder.seekBarStrip.max = max1//10//iYelloy - iLimitRed

                }

                mBillder?.seekBarStrip?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        p1 = progress
                        t1 = n1+p1*10
                        //customPrefRed = progress
                        n2 = t2+10
                        mBillder.textStrip.text = t1.toString()// k50(progress+1).toString()


                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) { }
                    override fun onStopTrackingTouch(seekBar: SeekBar?) { }
                })
                true
            }

            // низ графика работа
            lp0?.setOnPreferenceClickListener {
              //  val prefGet =PreferenceManager.getDefaultSharedPreferences(context)
              //  var i = prefGet.getInt("settings_lower_limit_of_the_red", 10 )
              //  var g=0
               // val iRedTop =prefGet.getInt("settings_top_of_the_red_line", 15 )
                lp0.title = t0.toString()//k50(customLimitRed).toString()
                val mDialogView =LayoutInflater.from(context).inflate(R.layout.strip,null)
                val mBillder = context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setView(mDialogView)
                        .setPositiveButton("Ok"){dialog, id ->
                            lp0.title = t0.toString()//k50(customLimitRed).toString() // сохранени цифры
                            val pref = PreferenceManager.getDefaultSharedPreferences(context).edit()
                            pref.putInt("settings_lower_limit_of_the_red", p0)
                            pref.apply()
                        }
                        .setNegativeButton("Cancel"){dialog, id ->
                        }.show()
                }

                if (mBillder != null) {
                    max3 = 70 - n3/10
                    max2 = (t3-n2-10)/10
                    max1 = (t2-n1-10)/10
                    max0 = (t1-10)/10
                    mBillder.textStrip.text = t0.toString()//k50(customLimitRed).toString() // динамика цифры
                    mBillder.seekBarStrip.progress=p0//customLimitRed
                    mBillder.seekBarStrip.max=max0//iRedTop+customLimitRed

                }

                mBillder?.seekBarStrip?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        p0 = progress// customLimitRed = progress
                        t0 = n0+p0*10
                        n1 = t0+10
                        mBillder.textStrip.text = t0.toString() //k50(progress).toString() // динамика цифры


                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) { }
                    override fun onStopTrackingTouch(seekBar: SeekBar?) { }
                })
                true
            }
        }

        // кратность 50и
//        public fun k50(i:Int = 0) = (i+1)*10

    }
}