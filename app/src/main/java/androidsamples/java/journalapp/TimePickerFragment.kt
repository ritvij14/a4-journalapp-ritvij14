package androidsamples.java.journalapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import java.util.*


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var mCalendar: Calendar
    private var mEntryDetailsViewModel: EntryDetailsViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEntryDetailsViewModel =
            ViewModelProvider(requireActivity())[EntryDetailsViewModel::class.java]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mCalendar = Calendar.getInstance()
        mCalendar.isLenient = false
        val hour: Int = mCalendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = mCalendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(), this, hour, minute, false)
    }

    companion object {
        fun newInstance(time: Date?): TimePickerFragment {
            return TimePickerFragment()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker, hour: Int, minute: Int) {
        mEntryDetailsViewModel!!.setHours(hour)
        mEntryDetailsViewModel!!.setMinutes(minute)
        val mBtnTime: Button = if (mEntryDetailsViewModel!!.getIsStartTime()) {
            requireActivity().findViewById(R.id.btn_start_time)
        } else {
            requireActivity().findViewById(R.id.btn_end_time)
        }

        var hourText = hour.toString()
        var minuteText = minute.toString()
        if (hourText.length == 1) {
            hourText = "0$hourText"
        }
        if (minuteText.length == 1) {
            minuteText = "0$minuteText"
        }
        mBtnTime.text = "$hourText: $minuteText"
    }
}