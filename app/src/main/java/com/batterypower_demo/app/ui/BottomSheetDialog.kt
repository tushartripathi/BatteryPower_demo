package com.batterypower_demo.app.ui

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.batterypower_demo.app.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*


class BottomSheetDialog : BottomSheetDialogFragment() {


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var radiocheck=false;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.bottom_sheet, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view?.parent as View).setBackgroundColor(Color.TRANSPARENT)
        val resources = resources

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            assert(view != null)
            val parent = view?.parent as View
            val layoutParams = parent.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.setMargins(
                50, // LEFT 16dp
                -100,
               50, // RIGHT 16dp
                0
            )
            parent.layoutParams = layoutParams
            (view?.parent as View).setBackgroundColor(Color.TRANSPARENT)
        }

        button1.setOnClickListener{
            amountET.setText("2000")
        }

        button2.setOnClickListener{
            amountET.setText("5000")
        }

        button3.setOnClickListener{
            amountET.setText("7000")
        }

        cancelButton.setOnClickListener{
            this.dismiss()
        }
        recharge.setOnClickListener{
            var count =0
            if(radiocheck){
                count++
                radioGroupError.visibility = GONE
            }
            else {
              //  Toast.makeText(context,"not checked ", Toast.LENGTH_SHORT).show()
                radioGroupError.visibility = VISIBLE
                }

            if(amountET.text.toString().isEmpty()){
              //  Toast.makeText(context,"enter amount ", Toast.LENGTH_SHORT).show()
                amountError.visibility = VISIBLE
            }
            else{
                count++
              //  Toast.makeText(context,"amount is "+amountET.text.toString(), Toast.LENGTH_SHORT).show()
                amountError.visibility = GONE
            }
            if(count==2)
                this.dismiss()
        }

        radio_group.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                radiocheck=true
            }

        } )
    }
}
