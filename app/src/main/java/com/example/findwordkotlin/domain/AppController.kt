package com.example.findwordkotlin.domain

import android.content.Context
import com.example.findwordkotlin.R
import com.example.findwordkotlin.data.model.QuestionData
import com.example.findwordkotlin.data.source.Pref

class AppController private constructor(context: Context) {
    companion object{
        lateinit var pref: Pref
        @Volatile
        private var instance:AppController? = null;
        fun getInstance(context: Context): AppController{
            if (instance == null){
                synchronized(this){
                    instance = AppController(context)
                }
            }
            pref = Pref.getInstance(context)
            return instance!!;
        }
    }
    fun saveMoney(score: Int) = pref.saveMoney(score)
    fun getMoney(): Int = pref.getMoney()
    fun saveAnswers(answers: List<String>) = pref.saveAnswers(answers!!)
    fun getAnswers(): List<String> = pref.getAnswers()
    fun saveVariants(variants: List<Boolean?>) = pref.saveVariants(variants)
    fun getVariants(): List<Boolean> = pref.getVariants()
    fun saveLastLevel(level: Int) = pref.saveLastLevel(level)
    fun getLastLevel(): Int = pref.getLastLevel()
    fun getQuestionByLevel(level: Int): QuestionData? {
        return when (level) {
            0 -> QuestionData(
                "java",
                "dikvbacajfeg",
                R.drawable.level3_1,
                R.drawable.level3_2,
                R.drawable.level3_3,
                R.drawable.level3_4
            )

            1 -> QuestionData(
                "bed",
                "vgdfaijakecb",
                R.drawable.level6_1,
                R.drawable.level6_2,
                R.drawable.level6_3,
                R.drawable.level6_4
            )

            2 -> QuestionData(
                "bank",
                "ndjfgkbaleic",
                R.drawable.level9_1,
                R.drawable.level9_2,
                R.drawable.level9_3,
                R.drawable.level9_4
            )

            3 -> QuestionData(
                "time",
                "fgijmatcedkb",
                R.drawable.level8_1,
                R.drawable.level8_2,
                R.drawable.level8_3,
                R.drawable.level8_4
            )

            4 -> QuestionData(
                "fruit",
                "guaebjdritfc",
                R.drawable.level1_1,
                R.drawable.level1_2,
                R.drawable.level1_3,
                R.drawable.level1_4
            )

            5 -> QuestionData(
                "hacker",
                "ajdrkiefhbcg",
                R.drawable.level2_1,
                R.drawable.level2_2,
                R.drawable.level2_3,
                R.drawable.level2_4
            )

            6 -> QuestionData(
                "mouse",
                "famobiuedscg",
                R.drawable.level7_1,
                R.drawable.level7_2,
                R.drawable.level7_3,
                R.drawable.level7_4
            )

            7 -> QuestionData(
                "cactus",
                "tecusfcdaibg",
                R.drawable.level5_1,
                R.drawable.level5_2,
                R.drawable.level5_3,
                R.drawable.level5_4
            )

            8 -> QuestionData(
                "android",
                "cofdidrebagn",
                R.drawable.level4_1,
                R.drawable.level4_2,
                R.drawable.level4_3,
                R.drawable.level4_4
            )
            else -> null
        }
    }
}