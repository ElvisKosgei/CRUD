
package com.elvis.crud
/*
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import java.util.prefs.PreferencesFactory
import android.content.ContentValues

class DBHelper(context:Context,factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,null, DB_VERSION) {

   */
/* companion object {
        private val DB_NAME = "Task"
        private val DB_VERSION = 1
        private val TABLE_NAME = "Task"
        private val ID = "id"
        private val TASK_NAME = "TaskName"
    }*//*



    override fun onCreate(db: SQLiteDatabase?) {
        //Creating the Table
    */
/*    val CREATE_TABLE: String =
            "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY,$TASK_NAME TEXT)"
        //Executing the query
        db?.execSQL(CREATE_TABLE)*//*

        val query = ("CREATE TABLE $TABLE_NAME" )

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)

    }

    @SuppressLint("Range")
    //fetch
    fun getAllTask(intExtra: Int): List<ClassListModel> {
        val taskList = ArrayList<ClassListModel>()
        val dB: SQLiteDatabase = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = dB.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val tasks = ClassListModel()
                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    tasks.name = cursor.getString(cursor.getColumnIndex(TABLE_NAME))
                    taskList.add(tasks)

                } while (cursor.moveToNext())
            }
            cursor.close()
            return taskList
        }
        //insert
        fun addTask(tasks: ClassListModel): Boolean {
            val dB: SQLiteDatabase = this.writableDatabase
            val values = ContentValues()
            values.put(TASK_NAME, tasks.name)
            val _success: Long = dB.insert(TABLE_NAME, null, values)
            dB.close()
            return (Integer.parseInt("$_success") != -1)

        }

        //select data of a particular id
        fun getTask(id: Int): ClassListModel {
            val tasks = ClassListModel()
            val dB: SQLiteDatabase = writableDatabase
            val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $_ID"
            val cursor: Cursor = dB.rawQuery(selectQuery, null)

            cursor?.moveToFirst()
            tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
            tasks.name = cursor.getString(cursor.getColumnIndex(TABLE_NAME))
            cursor.close()
            return tasks
        }

        //delete data
        fun deleteTask(_id: Int): Boolean {
            val dB: SQLiteDatabase = this.writableDatabase
            val _success: Int = dB.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString()))
            dB.close()
            return Integer.parseInt("$_success") != -1
        }

        //update
     */
/*   fun updateTask(task: ClassListModel) : Boolean {
            val dB : SQLiteDatabase = this.writableDatabase
            val values = ContentValues()
            values.put(TASK_NAME, task.name)
            val _success: Long = dB.update(TABLE_NAME,values, ID + "=?", arrayOf(_ID.toString())).toLong()
            dB.close()
            return Integer.parseInt("$_success") != -1

        }*//*


    }

    fun updateTask(tasks: ClassListModel): Boolean {
        val dB : SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME)
        val _success: Long = dB.update(TABLE_NAME,values, ID + "=?", arrayOf(_ID.toString())).toLong()
        dB.close()
        return Integer.parseInt("$_success") != -1

    }

    fun addTask(tasks: ClassListModel): Any {
        TODO("Not yet implemented")
    }

   */
/* fun deleteTask(intExtra: Any): Any {
        val dB: SQLiteDatabase = this.writableDatabase
        val _success: Int = dB.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString()))
        dB.close()
        return Integer.parseInt("$_success") != -1

    }*//*




}


*/
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DB_NAME, factory, DB_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE : String =
            "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY, $TASK_NAME TEXT)"

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    //insert
    fun addTask(tasks: ClassListModel): Boolean {
        val dB: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(TASK_NAME, tasks.name)
        val _success: Long = dB.insert(TABLE_NAME, null, values)
        dB.close()
        return (Integer.parseInt("$_success") != -1)

    }


    // below method is to get
    // all data from our database
    fun getTask(intExtra: Int): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }
  /*  fun getAllTask(intExtra: Int): List<ClassListModel> {
        val taskList = ArrayList<ClassListModel>()
        val dB: SQLiteDatabase = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = dB.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val tasks = ClassListModel()
                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    tasks.name = cursor.getString(cursor.getColumnIndex(TABLE_NAME))
                    taskList.add(tasks)

                } while (cursor.moveToNext())
            }
            cursor.close()
            return taskList
        }*/
  fun updateTask(task: ClassListModel) : Boolean {
      val dB : SQLiteDatabase = this.writableDatabase
      val values = ContentValues()
      values.put(TASK_NAME, task.name)
      val _success: Long = dB.update(TABLE_NAME,values, ID + "=?", arrayOf(_ID.toString())).toLong()
      dB.close()
      return Integer.parseInt("$_success") != -1

  }
    fun deleteTask(intExtra: Any): Any {
        val dB: SQLiteDatabase = this.writableDatabase
        val _success: Int = dB.delete(TABLE_NAME, ID + "=?", arrayOf(_ID.toString()))
        dB.close()
        return Integer.parseInt("$_success") != -1

    }

    companion object{
        /*// here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = ""

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "gfg_table"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "name"

        // below is the variable for age column
        val AGE_COL = "age"*/
        private val DB_NAME = "Task"
        private val DB_VERSION = 1
        private val TABLE_NAME = "Task"
        private val ID = "id"
        private val TASK_NAME = "TaskName"
    }
}
