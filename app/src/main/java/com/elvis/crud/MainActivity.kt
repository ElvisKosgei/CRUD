package com.elvis.crud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvList : RecyclerView
    lateinit var btnAddTask : Button
    var taskListAdapter : TaskListAdapter ?= null
    var dbHandler : DBHelper ?= null
    var taskList : ArrayList<Task> = ArrayList()
    var linearLayoutManager : LinearLayoutManager ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList = findViewById(R.id.rvList)
        btnAddTask  = findViewById(R.id.btnAddTask)

        dbHandler = DBHelper(this, null)
        fetchList()

        btnAddTask.setOnClickListener {
            val intent = Intent(applicationContext,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchList() {

        val taskCursor = dbHandler!!.getTasks()

        taskCursor?.moveToFirst()
        if (taskCursor != null) {
            while (taskCursor.moveToNext())
            {
               val id  = taskCursor.getInt(0)
               val title = taskCursor.getString(1)
               val task = Task(id, title)
               taskList.add(task)
            }
        }
         taskListAdapter = TaskListAdapter(taskList, applicationContext)
         linearLayoutManager = LinearLayoutManager(applicationContext)
         rvList.layoutManager = linearLayoutManager
         rvList.adapter = taskListAdapter
         taskListAdapter?.notifyDataSetChanged()
    }
}

