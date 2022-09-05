package com.elvis.crud

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
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
    var taskList : List<ClassListModel> = ArrayList<ClassListModel>()
    var linearLayoutManager : LinearLayoutManager ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvList : RecyclerView = findViewById(R.id.rvList)
        val btnAddTask : Button = findViewById(R.id.btnAddTask)

        dbHandler = DBHelper(this, null)
        fetchList()

        btnAddTask.setOnClickListener {
            val intent = Intent(applicationContext,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchList() {
        dbHandler!!.getTask(intent.getIntExtra("Id", 0)).also { taskList  }
        taskListAdapter = TaskListAdapter(taskList, applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        rvList.layoutManager = linearLayoutManager
        rvList.adapter = taskListAdapter
        taskListAdapter?.notifyDataSetChanged()


    }
}

