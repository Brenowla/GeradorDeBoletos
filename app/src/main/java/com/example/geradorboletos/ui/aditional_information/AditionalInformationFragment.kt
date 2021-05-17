package com.example.geradorboletos.ui.aditional_information

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.AditionalInformationFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import com.example.geradorboletos.ui.utils.Mask
import com.example.geradorboletos.ui.utils.validators.DateValidator
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AditionalInformationFragment : Fragment() {

    lateinit var binding : AditionalInformationFragmentBinding

    private val controler by lazy {
        findNavController()
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AditionalInformationViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({ activity as MainActivity }) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.aditionalItensComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AditionalInformationFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.toSendCharge = View.OnClickListener { toSendCharge() }
        binding.backToAddItens = View.OnClickListener { backToAddItens() }
        binding.datePicker = View.OnClickListener { generateDatePicker() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateTotal(mainViewModel.items)

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun toSendCharge() {
        mainViewModel.expireAt = DateValidator.remountDate(viewModel.expireAt.value ?: "")
        AditionalInformationFragmentDirections.actionAditionalInformationFragmentToSendChargeFragment().run {
            controler.navigate(this)
        }
    }

    private fun backToAddItens(){
        mainViewModel.expireAt = Mask.replaceChars(viewModel.expireAt.value ?: "")
        AditionalInformationFragmentDirections.actionAditionalInformationFragmentToAddItemsFragment().run {
            controler.navigate(this)
        }
    }

    private fun generateDatePicker(){
        val actualDate = Calendar.getInstance();
        val datePicked = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                val myCalendar = Calendar.getInstance()
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat("ddMMyyyy")
                viewModel.updateExpireAt(sdf.format(myCalendar.time))
            }
        }
        val datePickerDialog = DatePickerDialog(
            requireContext(), datePicked, actualDate.get(Calendar.YEAR), actualDate.get(Calendar.MONTH), actualDate.get(Calendar.DAY_OF_MONTH)
        )
        val datePicker = datePickerDialog.datePicker
        datePicker.minDate = actualDate.timeInMillis
        actualDate.add(Calendar.YEAR, 5)
        datePicker.maxDate = actualDate.timeInMillis
        datePickerDialog.show()
    }

}