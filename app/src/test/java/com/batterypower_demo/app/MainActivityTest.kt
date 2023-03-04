package com.batterypower_demo.app

import com.batterypower_demo.app.ui.MainActivity
import org.junit.Test

internal class MainActivityTest{
    @Test
    fun test(){
       val active = MainActivity()
        active.initAllVar();
    }
}