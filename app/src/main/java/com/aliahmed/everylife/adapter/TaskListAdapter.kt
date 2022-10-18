package com.aliahmed.everylife.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliahmed.everylife.R
import com.aliahmed.everylife.model.Events
import com.aliahmed.everylife.utils.getIcon

class TaskListAdapter(private val tasks: List<Events>) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgType = itemView.findViewById(R.id.imgType) as ImageView
        val txtHeading = itemView.findViewById(R.id.txtHeading) as TextView
        val txtDescription = itemView.findViewById(R.id.txtDescription) as TextView
        fun bindItems(events: Events) {
            txtHeading.text = events.name
            txtDescription.text = events.description
            events.type?.getIcon()?.let { imgType.setImageResource(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(tasks[position])
    }

    override fun getItemCount(): Int {
       return tasks.size
    }

}