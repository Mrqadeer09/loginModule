package com.teamx.loginmodule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.*
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.*
import com.paypal.checkout.shipping.OnShippingChange


class PaypalFragment : Fragment() {


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
        view.findViewById<TextView>(R.id.btnPaypal).setOnClickListener {
            PayPalCheckout.startCheckout(CreateOrder { createOrderActions ->
                val order = Order(
                    intent = OrderIntent.CAPTURE, appContext = AppContext(
                        userAction = UserAction.PAY_NOW
                    ), purchaseUnitList = listOf(
                        PurchaseUnit(
                            amount = Amount(
                                currencyCode = CurrencyCode.USD, value = "10.00"
                            )
                        )
                    )
                )
                //                createOrderActions.create(order)
                createOrderActions.create(order, object : CreateOrderActions.OnOrderCreated {
                    override fun onCreated(orderId: String) {
                        Log.d("123123", "orderId:$orderId ")
                    }

                })
                Log.d("123123", "onCreated: ")
            })


            PayPalCheckout.registerCallbacks(
                onApprove = OnApprove { approval ->
                    Log.d("123123", "registerCallbacksOrder successfully captured:1 $approval")
                    approval.orderActions.capture { captureOrderResult ->
                        Log.d(
                            "123123",
                            "registerCallbacksOrder successfully captured:2 $captureOrderResult"
                        )
                    }
                },
                onCancel = OnCancel {
                    Log.d("123123", "registerCallbacksOrder successfully captured:3 OnCancel ")
                    // Optional callback for when a buyer cancels the paysheet
                },
                onError = OnError { errorInfo ->
                    Log.d("123123", "registerCallbacksOrder successfully captured:4 $errorInfo")
                    // Optional error callback
                },
                onShippingChange = OnShippingChange { shippingChangeData,   shippingChangeActions ->
                    Log.d(
                        "123123",
                        "registerCallbacksOrder successfully captured:5 $shippingChangeData"
                    )
                    // Optional onShippingChange callback. See update shipping section for more details.
                })

        }
        paymentButtonContainer = view.findViewById(R.id.payment_button_container)
        paymentButtonContainer?.setup(createOrder = CreateOrder { createOrderActions ->
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
                    Log.d("setup", "orderId:$orderId ")
                }

            })
            Log.d("123123", "onCreated: ")

//            createOrderActions.create(order)
        },
            onApprove = OnApprove { approval ->
                Log.d("123123", "registerCallbacksOrder successfully captured:1 $approval")
                approval.orderActions.capture { captureOrderResult ->
                    Log.d(
                        "123123",
                        "registerCallbacksOrder successfully captured:2 $captureOrderResult"
                    )
                }
            },
            onCancel = OnCancel {
                Log.d("123123", "registerCallbacksOrder successfully captured:3 OnCancel ")
                // Optional callback for when a buyer cancels the paysheet
            },
            onError = OnError { errorInfo ->
                Log.d("123123", "registerCallbacksOrder successfully captured:4 $errorInfo")
                // Optional error callback
            },
            onShippingChange = OnShippingChange { shippingChangeData, shippingChangeActions ->
                Log.d(
                    "123123",
                    "registerCallbacksOrder successfully captured:5 $shippingChangeData"
                )
                // Optional onShippingChange callback. See update shipping section for more details.
            })

        paymentButtonContainer!!.viewState = PaymentButtonContainerViewState.invoke(
            onLoading = {
                Log.d("123123", "Loading")
            },
            onFinish = { fundingEligibilityState, exception ->
                fundingEligibilityState?.let { state ->
                    state.paymentsButtonMap?.let { map ->
                        map.entries.forEach {
                            val paymentFundingType = it.key
                            val fundingEligibilityItem = it.value
                            val eligibility = fundingEligibilityItem.eligible
                            val reasons = fundingEligibilityItem.reasons
                            val eligibilityStatus = if (eligibility) "is eligible" else "is not eligible"
                            Log.d(
                                "123123",
                                "$paymentFundingType $eligibilityStatus. $reasons"
                            )
                        }
                    }
                }
                exception?.let { e ->
                    Log.e("123213", "Error", e)
                }
            }
        )


        /* paymentButtonContainer = view.findViewById(R.id.payment_button_container)
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
                 Log.d("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
             }
         }, onCancel = OnCancel {
             Log.d("123123", "OnCancel: ")
             Log.d("OnCancel", "Buyer canceled the PayPal experience.")
         }, onError = OnError { errorInfo ->
             Log.d("123123", "OnError:$errorInfo ")
             Log.d("OnError", "Error: $errorInfo")
         })

         //new paycontroller
         paymentButtonContainer?.setPayPalButtonUi(
             paypalButtonUi = PayPalButtonUi(
                 PayPalButtonColor.BLUE, PayPalButtonLabel.CHECKOUT, PaymentButtonAttributes(
                     PaymentButtonShape.ROUNDED, PaymentButtonSize.LARGE, isEnabled = true
                 )
             )
         )*/
        /* paymentButtonContainer?.setup(createOrder = CreateOrder { createOrderActions ->
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

 //            createOrderActions.create(order)
         }, onApprove = OnApprove { approval ->
             approval.orderActions.capture { captureOrderResult ->
                 Log.d("123123", "CaptureOrderResult: $captureOrderResult")
             }
         }, onError = OnError { errorInfo ->
             Log.d("123123", errorInfo.reason, errorInfo.error)
         }, onCancel = OnCancel {
             Log.d("123123", "cancelled")
         }, onShippingChange = OnShippingChange { shippingChangeData, shippingChangeActions ->
             Log.d("123123", "Shipping type change: ${shippingChangeData.shippingChangeType}")
         })*/

        //


    }


}