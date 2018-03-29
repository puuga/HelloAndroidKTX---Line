package space.siwawesw.app.helloandroidktx

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.net.URISyntaxException


class MainActivity : AppCompatActivity() {

    private val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1234

    private lateinit var tvLineId: TextView
    private lateinit var edtLineId: EditText
    private lateinit var btnGoToLine: Button
    private lateinit var btnRequestLocation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.d(javaClass.name, "permission was granted")

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d(javaClass.name, "permission was denied")
                }
                return
            }

        // Add other 'when' lines to check for other
        // permissions this app might request.

            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun bindViews() {
        tvLineId = findViewById(R.id.tvLineId)
        edtLineId = findViewById(R.id.edtLineId)
        btnGoToLine = findViewById(R.id.btnGoToLine)
        btnRequestLocation = findViewById(R.id.btnRequestLocation)

        btnGoToLine.setOnClickListener(View.OnClickListener {
            if (!edtLineId.text.toString().isBlank()) {
                val lineId = edtLineId.text.toString()
                goToLine(id = lineId)
                return@OnClickListener
            }
            goToLine()
        })

        btnRequestLocation.setOnClickListener(View.OnClickListener {
            requestLocation()
        })
    }

    private fun goToLine(id: String = "@generali365") {
        try {
            val sendText = "line://ti/p/~" + id
            val intent = Intent.parseUri(sendText, Intent.URI_INTENT_SCHEME)
            startActivity(intent)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun requestLocation() {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.d(javaClass.name, "shouldShowRequestPermissionRationale: true")
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
//                requestLocation()
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                Log.d(javaClass.name, "shouldShowRequestPermissionRationale: false")
            }
        }
    }
}
