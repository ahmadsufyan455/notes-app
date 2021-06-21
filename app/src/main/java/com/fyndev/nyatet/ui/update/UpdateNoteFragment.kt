package com.fyndev.nyatet.ui.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.fyndev.nyatet.R
import com.fyndev.nyatet.databinding.FragmentUpdateNoteBinding
import com.fyndev.nyatet.entity.NoteEntity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private val updateNoteViewModel: UpdateNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtTitle.setText(args.note.title)
        binding.edtDescription.setText(args.note.description)

        binding.btnUpdate.setOnClickListener {
            updateNote()
        }
    }

    private fun updateNote() {
        if (inputCheck(binding.edtTitle.text.toString(), binding.edtDescription.text.toString())) {
            lifecycleScope.launch {
                val note = NoteEntity(
                    id = args.note.id,
                    title = binding.edtTitle.text.toString(),
                    description = binding.edtDescription.text.toString()
                )
                updateNoteViewModel.updateNote(note)
                Toast.makeText(context, "Successfully update!", Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
        } else {
            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        lifecycleScope.launch {
            val note = NoteEntity(
                args.note.id,
                args.note.title,
                args.note.description
            )
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                updateNoteViewModel.deleteNote(note)
                Toast.makeText(context, "Note deleted!", Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.note.title}")
            builder.setMessage("Are you sure want to delete ${args.note.title}?")
            builder.create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}