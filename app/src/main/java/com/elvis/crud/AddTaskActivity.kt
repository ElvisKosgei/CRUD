package com.elvis.crud

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTaskActivity : AppCompatActivity() {
    lateinit var btnSave : Button
    lateinit var btnDelete : Button
    lateinit var etTask : EditText
    var dbHandler : DBHelper ?= null
    var isEditMode : Boolean = false
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        etTask = findViewById(R.id.etTask)

        dbHandler = DBHelper(this, null)

        if (intent != null && intent.getStringExtra("Mode") == "E") {
            //Update data
            isEditMode = true
            btnSave.text = "Update Data"
            btnDelete.visibility = View.VISIBLE
            val tasks : Cursor? = dbHandler!!.getTask(intent.getIntExtra("Id", 0))
            etTask.setText(tasks)
        }else {
            //Insert new data
            isEditMode = false
            btnSave.text = "Save Data"
            btnDelete.visibility = View.GONE
        }
        btnSave.setOnClickListener {
            var success : Boolean = false
            val tasks : ClassListModel = ClassListModel()
            if (isEditMode){
                //Update
                tasks.id = intent.getIntExtra("Id",0)
                tasks.name = etTask.text.toString()

                success = dbHandler?.updateTask(tasks) as Boolean
            }else{
                //insert
                tasks.name = etTask.text.toString()

                success = dbHandler?.addTask(tasks) as Boolean

            }
            if (success){
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(applicationContext,"Oops Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
/*
        btnDelete.setOnClickListener {
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click Yes if you want to delete the task")
                .setPositiveButton("YES", {dialog, intent ->
                    val success = dbHandler?.deleteTask(intent.getIntExtra("id",0)) as Boolean
                    if (success){
                        finish()
                        dialog.dismiss()
                    }
                })
                .setNegativeButton("NO", {dialog, intent ->
                    dialog.dismiss()
                })
            dialog.show()
        }*/
        btnDelete.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Delete Product")
            builder.setMessage("Deleting is a permanent task. Do you want to proceed?")
            builder.setPositiveButton("Cancel") {dialog,_,->
                dialog.dismiss()
            }
            builder.setNegativeButton("Delete") {_,_ ->
               // builder.dismiss()
                Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
            builder.show()
        }
    }
}

private fun EditText.setText(tasks: Cursor?) {

}

private fun EditText.setText(tasks: List<ClassListModel>) {

}
