package com.example.myapplicationmo1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationmo1.db.Notes
import com.example.myapplicationmo1.R
import com.example.myapplicationmo1.clickListener.ItemClickListener

class NotesAdapter ( val list: List<Notes>, val itemClickListener: ItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
       val notes = list[position]
        val title = notes.title
        val description = notes.description
        holder.textViewTitle.text = title
        holder.textViewDescription.text = description
        holder.checkBoxMarkStatus.isChecked = notes.isTaskCompleted
        Glide.with(holder.itemView).load(notes.imagePath).into(holder.imageView)
        holder.itemView.setOnClickListener(object  :View.OnClickListener{
            override fun onClick(v: View?) {
                itemClickListener.onClick(notes)
            }

        })

        holder.checkBoxMarkStatus.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                notes.isTaskCompleted = isChecked
                itemClickListener.onUpdate(notes)

            }

        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout,parent,false)
        return ViewHolder(view)
    }
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
       val textViewTitle:TextView =itemView.findViewById(R.id.textViewTitle)
        val textViewDescription:TextView = itemView.findViewById(R.id.textViewDescription)
        val checkBoxMarkStatus : CheckBox = itemView.findViewById(R.id.checkboxMarkStatus)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
    }

}