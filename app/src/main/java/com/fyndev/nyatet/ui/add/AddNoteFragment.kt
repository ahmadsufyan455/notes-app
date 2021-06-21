package com.fyndev.nyatet.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fyndev.nyatet.databinding.FragmentAddNoteBinding
import com.fyndev.nyatet.entity.NoteEntity
import com.fyndev.nyatet.helper.DateHelper
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val addNoteViewModel: AddNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            if (inputCheck(binding.edtTitle.text.toString(), binding.edtDescription.text.toString())) {
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = binding.edtTitle.text.toString(),
                        description = binding.edtDescription.text.toString(),
                        date = DateHelper.getCurrentDate()
                    )

                    addNoteViewModel.insertNote(note)
                    Toast.makeText(context, "Successfully added!", Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
            } else {
                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}