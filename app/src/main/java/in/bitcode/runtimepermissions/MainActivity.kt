package `in`.bitcode.runtimepermissions

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            readWriteExternalStorage()
        } else {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_SMS,
                ),
                1
            );
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(i in 0..(permissions.size-1)) {
            mt("${permissions[i]} --> ${grantResults[i]} ")
        }

        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0].equals(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            readWriteExternalStorage()
        }
    }

    private fun readWriteExternalStorage() {
        Log.e("tag", "Read/Write external storage...")
    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}