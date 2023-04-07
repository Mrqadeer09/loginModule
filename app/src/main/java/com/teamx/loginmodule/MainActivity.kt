package com.teamx.loginmodule


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {
    var callbackManager: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val fragment = PaypalFragment()
//        val fragmentManager = supportFragmentManager
//        fragment.show(fragmentManager, "MyDialogFragment")
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = PaypalFragment()
        fragmentTransaction.replace(R.id.fragment_container, myFragment)
        fragmentTransaction.commit()
/*
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this@MainActivity.application)
        callbackManager = CallbackManager.Factory.create()

//        logger.logPurchase(BigDecimal.valueOf(4.32), Currency.getInstance("USD"))
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired


        val EMAIL = "email"

        val loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions(Arrays.asList(EMAIL))
        // If you are using in a fragment, call loginButton.setFragment(this)

        // Callback registration
        // If you are using in a fragment, call loginButton.setFragment(this)

        // Callback registration
        loginButton.registerCallback(callbackManager!!, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("123123", "onSuccess: ")

                val request = GraphRequest.newMeRequest(
                    loginResult.accessToken
                ) { a, response ->
                    try {
                        val email = a!!.getString("email")
                        Log.d("123123", "onSuccess:$email ")
                        // Do something with the user's email
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "email")
                request.parameters = parameters
                request.executeAsync()

                // App code
            }

            override fun onCancel() {
                Log.d("123123", "onCancel: ")
                // App code
            }

            override fun onError(exception: FacebookException) {
                Log.d("123123", "onError: ")
                // App code
            }
        })*/


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}