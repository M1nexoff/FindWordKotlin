package com.example.findwordkotlin.presentation.start

import android.content.Context
import com.example.findwordkotlin.domain.AppController


class StartModel(context: Context) : StartContract.Model{
    private val appController: AppController
    init {
        appController = AppController.getInstance(context)
    }
    override fun getScore(): Int {
        return appController.getMoney()
    }

    override fun getLevel(): Int {
        return appController.getLastLevel()
    }

}

