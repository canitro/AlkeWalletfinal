package bravo.carlos.alkewalletfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RequestMoneyActivity : AppCompatActivity() {

    val TAG ="RequestMoneyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_request_money)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBackToHome1 = findViewById<ImageView>(R.id.btn_back_add_to_home)
        val btn2 = findViewById<Button>(R.id.addmoney_222)

        btnBackToHome1.setOnClickListener {goToHomeOfRequest()}
        btn2.setOnClickListener { goToHomeOfRequest() }


    }

    private fun goToHomeOfRequest() {
        val i = Intent(this, HomePageActivity::class.java)
        startActivity(i)
    }
}