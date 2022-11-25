package androidsamples.java.journalapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var mCalendar: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mCalendar = Calendar.getInstance()
        mCalendar.isLenient = false
        val currentYear = mCalendar[Calendar.YEAR]
        val currentMonth = mCalendar[Calendar.MONTH]
        val currentDay = mCalendar[Calendar.DAY_OF_MONTH]
        val datePicker =
            DatePickerDialog(requireContext(), this, currentYear, currentMonth, currentDay)
        datePicker.datePicker.minDate = mCalendar.timeInMillis
        return datePicker
    }

    companion object {
        fun newInstance(date: Date?): DatePickerFragment {
            return DatePickerFragment()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, date: Int) {
        mCalendar.set(year, month + 1, date)

        val mBtnEntryDate: Button = requireActivity().findViewById(R.id.btn_entry_date)
        val fullFormat = mCalendar.time.toString()
        val dayDate = fullFormat.substring(0, 3) + "," + fullFormat.substring(
            3,
            10
        ) + " ," + fullFormat.substring(30, 34)
        mBtnEntryDate.text = dayDate
    }
}