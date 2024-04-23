package edu.androidprogrammingclasses

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private var button: Button? = null
  private var text: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.layout_main)
    setupViews()

    Log.d("Lifecycle", "OnCreate $intent")
  }

  private fun setupViews() {
    button = findViewById<Button>(R.id.button)
    button?.setOnClickListener { navigateToSecondActivity() }

    text = findViewById<TextView>(R.id.text)

  }

  private fun navigateToSecondActivity() {
    val intent = Intent(this, SecondActivity::class.java)
    intent.putExtra("someData", "Some test data")
    startActivityForResult(intent, 432)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    Log.d("Result", "We have request code: $requestCode, result code: $resultCode and data $data")
    if (requestCode == 432) {
      if (resultCode == RESULT_OK) {
        text?.text = data?.getStringExtra("resultData") ?: ""
      }
    }
  }

  override fun onStart() {
    super.onStart()
    Log.d("Lifecycle", "OnStart")
  }

  override fun onResume() {
    super.onResume()
    Log.d("Lifecycle", "OnResune")
  }

  override fun onPause() {
    super.onPause()
    Log.d("Lifecycle", "OnPause")
  }

  override fun onStop() {
    super.onStop()
    Log.d("Lifecycle", "OnStop")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("Lifecycle", "OnDestroy")
  }
}
