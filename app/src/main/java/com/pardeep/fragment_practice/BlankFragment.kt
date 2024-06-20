package com.pardeep.fragment_practice

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.pardeep.fragment_practice.databinding.FragmentBlankBinding
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Month
import java.time.Year
import java.util.Calendar
import java.util.Locale
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment(), ActivityInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var frag_btn1 : Button?= null
    var frag_btn2 : Button? =null
    var frag_btn3 : Button? =null
    var mainActivity : MainActivity? = null
    var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
    var timeFormat = SimpleDateFormat("HH:mm:ss a",Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity?.activityInterface = this

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_btn1 = view.findViewById(R.id.frag_btn1)
        frag_btn2 = view.findViewById(R.id.frag_btn2)
        frag_btn3 = view.findViewById(R.id.frag_btn3)

        frag_btn1?.setOnClickListener {
            mainActivity?.changeButtonText()
        }

        frag_btn2?.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayofmonth ->
                    val calender = Calendar.getInstance()
                    calender.set(year, month, dayofmonth)
                    var formatDate = simpleDateFormat.format(calender.time)
                    frag_btn2?.setText(formatDate)
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONDAY),
                Calendar.getInstance().get(Calendar.DATE),
            ).show()

        }
        // timepicker
        frag_btn3?.setOnClickListener {
            TimePickerDialog(
                requireContext(),
                { _, hour , minute ->
                    val calendar = Calendar.getInstance()
                    calendar.set(Calendar.MINUTE , minute)
                    calendar.set(Calendar.HOUR_OF_DAY ,hour)
                    var formatedTime = timeFormat.format(calendar.time)
                    frag_btn3?.setText(formatedTime)
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),

                false
            ).show()
        }
}

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun changeFragmentText(){
        frag_btn1?.setText("Change from Activity")
    }
}