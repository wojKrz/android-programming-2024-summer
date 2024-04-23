package edu.androidprogrammingclasses

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.layout_second)

    findViewById<Button>(R.id.firstButton).setOnClickListener {
      setResultForMainActivity("First")
    }
    findViewById<Button>(R.id.secondButton).setOnClickListener {
      setResultForMainActivity("Second")
    }
  }

  private fun setResultForMainActivity(data: String) {
    setResult(RESULT_OK, createResultIntent(data))
    finishActivity(432)
  }


  private fun createResultIntent(data: String): Intent =
    Intent().apply { putExtra("resultData", data) }
}
