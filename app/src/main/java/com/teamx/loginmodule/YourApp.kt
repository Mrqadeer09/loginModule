package com.teamx.loginmodule

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction


class YourApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val YOUR_CLIENT_ID: String =
           "AdvlcnQvcP-9V2-2q6eW5htoG0Z3GjLuAVnY0e1nxD-AjVte_BBNlvJvFSZ59hnqfnIDPJEYSq4p34aE"
        //myId
//            "AYhqwOVlI7qD3d_blV2yF6no1XCYgpz8lPTuGNLkUaopBc5z5nlNelmKDozOaRbL6esX_9ASXslEL9e0"
        val config = CheckoutConfig(
            application = this,
            clientId =  YOUR_CLIENT_ID,
            environment = Environment.SANDBOX,
            returnUrl = "nativexo://paypalpay",
            currencyCode = CurrencyCode.USD,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )

//config.ur
        PayPalCheckout.setConfig(config)

    }
}