package com.example.testdemohyperlink.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testdemohyperlink.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_create_account.etEmail
import kotlinx.android.synthetic.main.fragment_login.*


class FragmentCreateAccount : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<AppCompatTextView>(R.id.btnCreateAccount).setOnClickListener(this)
        view.findViewById<AppCompatImageView>(R.id.img_back).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnCreateAccount -> validation()
            R.id.img_back -> activity?.onBackPressed()
        }
    }

    fun validation() {

        if (TextUtils.isEmpty(etName.text.toString()))
            onSNACK(etName, "please input name")
        else if (TextUtils.isEmpty(etPhoneNumber.text.toString()))
            onSNACK(etPhoneNumber, "please input mobile number")
        else if (etPhoneNumber.text.toString().length != 10)
            onSNACK(etPhoneNumber, "please input valid mobile number")
        else if (TextUtils.isEmpty(etEmail.text.toString()))
            onSNACK(etEmail, "please input email")
        else if (!isEmailValid(etEmail.text.toString()))
            onSNACK(etEmail, "please input valid email")
        else if (TextUtils.isEmpty(etPass.text.toString()))
            onSNACK(etPass, "please input password")
        else if (etPass.text.toString().length < 6)
            onSNACK(etName, "please input atleast 6 char password")
        else if (TextUtils.isEmpty(etLocation.text.toString()))
            onSNACK(etName, "please input valid location")
        else
            navController!!.navigate(R.id.action_fragmentCreateAccount_to_fragmentVerifyOTP)
    }

    fun isEmailValid(email: String): Boolean {
        Log.e("AMIT_", " TEST : " + android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onSNACK(view: View, msg: String) {
        //Snackbar(view)
        val snackbar = Snackbar.make(
            view, msg,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar.setActionTextColor(Color.RED)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.BLACK)
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.RED)
        textView.textSize = 14f
        snackbar.show()
    }

}