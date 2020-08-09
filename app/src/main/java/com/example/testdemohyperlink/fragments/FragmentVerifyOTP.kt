package com.example.testdemohyperlink.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chaos.view.PinView
import com.example.testdemohyperlink.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_verify_o_t_p.*


class FragmentVerifyOTP : Fragment(), View.OnClickListener {
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<AppCompatTextView>(R.id.btnVerify).setOnClickListener(this)
        view.findViewById<AppCompatImageView>(R.id.btnCross).setOnClickListener(this)
    }


    private fun initView() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_o_t_p, container, false)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnVerify -> validation()
            R.id.btnCross -> activity?.onBackPressed()
        }
    }

    fun validation() {
        if (TextUtils.isEmpty(pinOTP.text.toString()))
            onSNACK(pinOTP, "please input pin")
        else if (pinOTP.text.toString().length != 4)
            onSNACK(pinOTP, "please input valid pin")
        else
            navController!!.navigate(R.id.action_fragmentVerifyOTP_to_fragmentLogin)
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