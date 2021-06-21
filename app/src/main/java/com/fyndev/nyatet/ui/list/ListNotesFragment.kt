package com.fyndev.nyatet.ui.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fyndev.nyatet.R
import com.fyndev.nyatet.databinding.FragmentListNotesBinding
import com.fyndev.nyatet.entity.NoteEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListNotesFragment : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!

    private val listNotesViewModel: ListNotesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listNoteAdapter = ListNotesAdapter()
        listNotesViewModel.getAllNotes().observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                listNoteAdapter.setData(it)
                binding.rvNotes.visibility = View.VISIBLE
                binding.lottieEmpty.visibility = View.GONE
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.lottieEmpty.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvNotes.visibility = View.GONE
            }
        })

        with(binding.rvNotes) {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = listNoteAdapter
        }

        binding.fabAddNote.setOnClickListener {
            val directions = ListNotesFragmentDirections.actionListNotesFragmentToAddNoteFragment()
            findNavController().navigate(directions)
        }

        listNoteAdapter.setOnItemClickCallBack(object : ListNotesAdapter.OnItemCLickCallback {
            override fun onItemClicked(noteEntity: NoteEntity) {
                val directions =
                    ListNotesFragmentDirections.actionListNotesFragmentToUpdateNoteFragment(
                        noteEntity
                    )
                findNavController().navigate(directions)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                listNotesViewModel.deleteAll()
                Toast.makeText(context, "Successfully delete all notes!", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete All")
            builder.setMessage("Are you sure want to delete all notes?")
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}