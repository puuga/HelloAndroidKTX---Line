package space.siwawesw.app.helloandroidktx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Intent
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {

    private lateinit var tvLineId: TextView
    private lateinit var edtLineId: EditText
    private lateinit var btnGoToLine: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
    }

    private fun bindViews() {
        tvLineId = findViewById(R.id.tvLineId)
        edtLineId = findViewById(R.id.edtLineId)
        btnGoToLine = findViewById(R.id.btnGoToLine)

        btnGoToLine.setOnClickListener(View.OnClickListener {
            if (!edtLineId.text.toString().isBlank()) {
                val lineId = edtLineId.text.toString()
                goToLine(id = lineId)
                return@OnClickListener
            }
            goToLine()
        })
    }

    private fun goToLine(id:String = "@generali365") {
        try {
            val sendText = "line://ti/p/~" + id
            val intent = Intent.parseUri(sendText, Intent.URI_INTENT_SCHEME)
            startActivity(intent)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }


    }
}
