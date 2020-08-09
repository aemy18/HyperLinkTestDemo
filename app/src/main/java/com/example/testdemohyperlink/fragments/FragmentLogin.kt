package com.example.testdemohyperlink.fragments

import android.content.Intent
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
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testdemohyperlink.ImagesActivity
import com.example.testdemohyperlink.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.regex.Pattern

class FragmentLogin : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<RelativeLayout>(R.id.btnCreateNewAccount).setOnClickListener(this)
        view.findViewById<AppCompatTextView>(R.id.btnLogin).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnCreateNewAccount -> navController!!.navigate(R.id.action_fragmentLogin_to_fragmentCreateAccount)
            R.id.btnLogin -> Validation()
            /*activity?.let {
                val intent = Intent(it, ImagesActivity::class.java)
                it.startActivity(intent)
            }*/

        }
    }

    fun Validation() {
        if (TextUtils.isEmpty(etEmailAddress.text.toString()))
            onSNACK(etEmailAddress, "please input email")
        else if (!isEmailValid(etEmailAddress.text.toString()))
            onSNACK(etEmailAddress, "please input valid email")
        else if (TextUtils.isEmpty(etPassword.text.toString()))
            onSNACK(etPassword, "please input password")
        else if (etPassword.text.toString().length < 6)
            onSNACK(etPassword, "please input atleast 6 char password")
        else
            activity?.let {
                val intent = Intent(it, ImagesActivity::class.java)
                it.startActivity(intent)
            }

        /*if (!TextUtils.isEmpty(etEmail.text.toString()) && etEmail.text.toString().isEmailValid()
            && TextUtils.isEmpty(etPassword.text.toString()) && etPassword.text.toString().length >= 6
        )
            activity?.let {
                val intent = Intent(it, ImagesActivity::class.java)
                it.startActivity(intent)
            }*/
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

    /*fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }*/
}