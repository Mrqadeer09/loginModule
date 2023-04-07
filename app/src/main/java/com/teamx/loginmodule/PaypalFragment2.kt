package com.teamx.loginmodule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.PayPalCheckout.setConfig
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.*
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PaymentButtonContainer
import com.paypal.checkout.shipping.OnShippingChange


class PaypalFragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    var paymentButtonContainer: PaymentButtonContainer? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*setConfig(
            this,
            "YOUR_CLIENT_ID",
            PayPalConfiguration.ENVIRONMENT_SANDBOX // or ENVIRONMENT_PRODUCTION
        );*/
        /*  paymentButtonContainer = view.findViewById(R.id.payment_button_container)
          paymentButtonContainer?.setup(createOrder = CreateOrder { createOrderActions ->
              Log.d("123123", "onViewCreated1:$createOrderActions ")
              Log.d("123123", "onViewCreated1:$createOrderActions ")
              val order = Order(
                  intent = OrderIntent.CAPTURE,
                  appContext = AppContext(userAction = UserAction.PAY_NOW),
                  purchaseUnitList = listOf(
                      PurchaseUnit(
                          amount = Amount(currencyCode = CurrencyCode.USD, value = "10.00")
                      )
                  )
              )
  //            createOrderActions.create(order)
              createOrderActions.create(order, object : CreateOrderActions.OnOrderCreated {
                  override fun onCreated(orderId: String) {
                      Log.d("123123", "orderId:$orderId ")
                  }

              })
              Log.d("123123", "onCreated: ")
          }, onApprove = OnApprove { approval ->
              Log.d("123123", "OnApprove:$approval ")
              approval.orderActions.capture { captureOrderResult ->
                  Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
              }
          }, onCancel = OnCancel {
              Log.d("123123", "OnCancel: ")
              Log.d("OnCancel", "Buyer canceled the PayPal experience.")
          }, onError = OnError { errorInfo ->
              Log.d("123123", "OnError:$errorInfo ")
              Log.d("OnError", "Error: $errorInfo")
          })*/




    }

}