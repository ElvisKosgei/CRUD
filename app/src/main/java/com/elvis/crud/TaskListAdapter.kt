package com.elvis.crud

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskListAdapter(taskList: List<Task>, internal var context: Context) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private var taskList: List<Task> = ArrayList()

    init {
        this.taskList = taskList
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtname: TextView = view.findViewById(R.id.txtName)
        var btnEdit: ImageView = view.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_task_list, parent, false)
        return TaskViewHolder(view)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.txtname.text = task.name
        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, AddTaskActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("Mode", "E")
            intent.putExtra("Name", task.name)
            intent.putExtra("Id", task.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}