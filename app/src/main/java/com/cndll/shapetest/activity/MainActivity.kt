package com.cndll.shapetest.activity

import com.cndll.shapetest.R

class MainActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(android.content.Intent(this, LoginActivity::class.java))
    }
}
