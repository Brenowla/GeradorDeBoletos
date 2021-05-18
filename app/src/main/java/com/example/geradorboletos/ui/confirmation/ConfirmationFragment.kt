package com.example.geradorboletos.ui.confirmation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.databinding.ConfirmationFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class ConfirmationFragment : Fragment() {

    lateinit var binding: ConfirmationFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<ConfirmationViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfirmationFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.chargeresponse = mainViewModel.chargeResponse
        binding.person = mainViewModel.costumer
        binding.copy = View.OnClickListener { copyBarcodeToClipboard() }
        binding.backToFormPerson = View.OnClickListener { backToFormPerson() }
        binding.seePDF = View.OnClickListener { openPDF() }
        binding.share = View.OnClickListener { shareOnSocialMedia() }
        binding.isJuridical = mainViewModel.costumer?.juridicalPerson != null
        return binding.root
    }

    fun backToFormPerson(){
        mainViewModel.resetData()
        ConfirmationFragmentDirections.actionConfirmationFragmentToFormPersonFragment().run {
            controler.navigate(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.confirmationComponent().create().inject(this)
    }

    private fun copyBarcodeToClipboard(){
        val clipboardManager : ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("barcode", mainViewModel.chargeResponse?.data?.barcode)
        clipboardManager.setPrimaryClip(clipData)
    }

    private fun openPDF() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mainViewModel.chargeResponse?.data?.pdf?.charge ?: ""))
        startActivity(browserIntent)
    }

    private fun shareOnSocialMedia(){
        val text = viewModel.makeSharedText(mainViewModel.chargeResponse)
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}
