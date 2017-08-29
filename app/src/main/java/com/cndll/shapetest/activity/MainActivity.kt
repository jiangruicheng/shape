package com.cndll.shapetest.activity

import com.cndll.shapetest.R
import com.cndll.shapetest.tools.AppManager

class MainActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(android.content.Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onResume() {
        super.onResume()
        AppManager.getAppManager().addActivity(this)
    }
}
