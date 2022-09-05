package com.elvis.crud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTaskActivity : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var btnDelete: Button
    lateinit var etTask: EditText
    var dbHandler: DBHelper? = null
    var isEditMode: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        etTask = findViewById(R.id.etTask)

        dbHandler = DBHelper(this, null)
        etTask.setText(intent.getStringExtra("Name"))
        val taskId = intent.getIntExtra("Id", 0)
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            //Update data
            isEditMode = true
            btnSave.text = "Update Data"
            btnDelete.visibility = View.VISIBLE

        } else {
            //Insert new data
            isEditMode = false
            btnSave.text = "Save Data"
            btnDelete.visibility = View.GONE
        }
        btnSave.setOnClickListener {
            var success: Boolean = false
            if (isEditMode) {
                //Update

                val id = intent.getIntExtra("Id", 0)
                val name = etTask.text.toString()
                val task = Task(id, name)
                success = dbHandler?.updateTask(task) as Boolean
            } else {
                //insert
                val name = etTask.text.toString()
                val task = Task(0, name)
                success = dbHandler?.addTask(task) as Boolean
            }
            if (success) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Oops Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnDelete.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Delete Product")
            builder.setMessage("Deleting is a permanent task. Do you want to proceed?")
            builder.setPositiveButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.setNegativeButton("Delete") { _, _ ->
                // builder.dismiss()
                dbHandler!!.deleteTask(taskId)
                Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            builder.show()
        }
    }
}
