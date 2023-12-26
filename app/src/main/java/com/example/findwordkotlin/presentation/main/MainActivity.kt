package com.example.findwordkotlin.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findwordbyphoto.presentation.main.MainContract
import com.example.findwordkotlin.R
import com.example.findwordkotlin.presentation.Finish
import com.example.findwordkotlin.presentation.dialog.MyDialog
import com.example.findwordkotlin.presentation.dialog.SelectListener


class MainActivity : AppCompatActivity(), MainContract.View {
    private var money: TextView? = null
    private var images: MutableList<ImageView>? = null
    private var answers: MutableList<Button> = ArrayList()
    private var variants: MutableList<Button> = ArrayList()
    private var level: TextView? = null
    private var presenter: MainContract.Presenter? = null
    private var backButton: ImageView? = null
    private var resetButton: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        val s = intent.getBooleanExtra("game",false)
        presenter = MainPresenter(this) as MainContract.Presenter
        presenter!!.setQuestion(s)
    }

    private fun initialize() {
        money = findViewById(R.id.money)
        level = findViewById(R.id.level)
        backButton = findViewById(R.id.back)
        resetButton = findViewById(R.id.restart)
        backButton!!.setOnClickListener(View.OnClickListener { v: View? -> presenter!!.menu() })
        resetButton!!.setOnClickListener(View.OnClickListener { v: View? -> presenter!!.restart() })
        images = ArrayList()
        images!!.add(findViewById(R.id.imgQuestion1))
        images!!.add(findViewById(R.id.imgQuestion2))
        images!!.add(findViewById(R.id.imgQuestion3))
        images!!.add(findViewById(R.id.imgQuestion4))
        answers.addAll(findButtons(
            R.id.linerAnswers, 0
        ) { view: View -> clickAnswer(view) })
        variants.addAll(findButtons(
            R.id.linerVariant1, 0
        ) { view: View -> clickVariant(view) })
        variants.addAll(findButtons(
            R.id.linerVariant2, variants.size
        ) { view: View -> clickVariant(view) })
    }

    private fun clickAnswer(view: View) {
        presenter!!.clickAnswer((view.tag as Int))
    }
    private fun clickVariant(view: View) {
        presenter!!.clickVariant((view.tag as Int))
    }

    private fun findButtons(
        linerId: Int,
        position: Int,
        listener: View.OnClickListener
    ): List<Button> {
        val buttons: MutableList<Button> = ArrayList()
        val layout = findViewById<LinearLayout>(linerId)
        for (i in 0 until layout.childCount) {
            val button = layout.getChildAt(i) as Button
            button.tag = i + position
            button.setOnClickListener(listener)
            buttons.add(button)
        }
        return buttons
    }

    override fun showDialogNext() {
        val dialog = MyDialog()
        dialog.setSelectListener(object : SelectListener {
            override operator fun next() {
                presenter!!.nextLevel()
            }

            override fun menu() {
                presenter!!.menu()
            }
        })
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "test")
    }

    override fun setImages(images: List<Int>) {
        for (i in images.indices) {
            this.images!![i].setImageResource(images[i]!!)
        }
    }

    override fun clearAnswer() {
        for (i in answers.indices) {
            answers[i].visibility = View.VISIBLE
            answers[i].text = ""
        }
    }

    override fun setLevel(level: Int) {
        this.level!!.text = level.toString()
    }
    override fun setMoney(money: Int) {
        this.money!!.text = "Score: $money"
    }
    override fun setVariants(variants: String) {
        for (i in this.variants.indices) {
            this.variants[i].visibility = View.VISIBLE
            this.variants[i].text = variants[i].toString()
        }
    }

    override fun setVisibleVariant(index: Int) {
        variants[index].visibility = View.VISIBLE
    }

    override fun setInvisibleVariant(index: Int) {
        variants[index].visibility = View.INVISIBLE
    }

    override fun setAnswer(index: Int, answer: String?) {
        answers[index].text = answer
    }

    override fun deleteAnswer(index: Int) {
        if (index >= 0 && index < answers.size) {
            answers[index].visibility = View.GONE
        }
    }

    override fun showResult(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun exit() {
        finish()
    }

    override fun onStop() {
        presenter!!.saveMoney()
        super.onStop()
    }

    override fun startFinish() {
        val intent = Intent(this@MainActivity, Finish::class.java)
        startActivity(intent)
        finish()
    }
}

