@file:Suppress("DEPRECATION")

package com.adithya.vote4future

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.CancellationSignal
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity




class FingerprintHandler(private val appContext: Context) : FingerprintManager.AuthenticationCallback() {


    private var cancellationSignal: CancellationSignal? = null


    fun startAuth(manager: FingerprintManager,
                   cryptoObject: FingerprintManager.CryptoObject) {

        cancellationSignal = CancellationSignal()

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.USE_FINGERPRINT) !=
            PackageManager.PERMISSION_GRANTED) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }
    override fun onAuthenticationError(errMsgId: Int,
                                        errString: CharSequence) {
        Toast.makeText(appContext,
            "Authentication error\n" + errString,
            Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationHelp(helpMsgId: Int,
                                      helpString: CharSequence) {
        Toast.makeText(appContext,
            "Authentication help\n" + helpString,
            Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationFailed() {
        Toast.makeText(appContext,
            "Authentication failed.",
            Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationSucceeded(
        result: FingerprintManager.AuthenticationResult) {

        Toast.makeText(appContext,
            "Authentication succeeded.",
            Toast.LENGTH_LONG).show()
        appContext.startActivity(
            Intent(
                appContext,
                MainActivity::class.java
            )
        )
    }

}