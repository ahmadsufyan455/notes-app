package com.fyndev.nyatet.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.fyndev.nyatet.R
import com.fyndev.nyatet.databinding.NoteItemBinding
import com.fyndev.nyatet.entity.NoteEntity
import java.util.*
import kotlin.collections.ArrayList

class ListNotesAdapter : RecyclerView.Adapter<ListNotesAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemCLickCallback

    private val listNotes = ArrayList<NoteEntity>()

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemCLickCallback) {
        this.onItemClickCallback = onItemClickCallBack
    }

    fun setData(notes: List<NoteEntity>) {
        listNotes.clear()
        listNotes.addAll(notes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNotes[holder.adapterPosition]) }
        holder.bgCard.setCardBackgroundColor(holder.itemView.resources.getColor(getRandomColor()))
    }

    private fun getRandomColor(): Int {
        val colors = ArrayList<Int>()
        colors.add(R.color.blue_500)
        colors.add(R.color.green_500)
        colors.add(R.color.orange_700)
        colors.add(R.color.red_400)

        val random = Random()
        val number = random.nextInt(colors.size)
        return colors[number]
    }

    override fun getItemCount(): Int = listNotes.size

    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val bgCard: CardView = itemView.findViewById(R.id.card_view)
        fun bind(note: NoteEntity) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description
            binding.tvDate.text = note.date
        }
    }

    interface OnItemCLickCallback {
        fun onItemClicked(noteEntity: NoteEntity)
    }
}