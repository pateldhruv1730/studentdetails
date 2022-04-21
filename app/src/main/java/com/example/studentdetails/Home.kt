package com.example.studentdetails

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
        var rs: Cursor = db.rawQuery("SELECT * FROM TEACHERINFO WHERE USERID=?", check)
        rs.requery()
        if (rs.moveToNext()) {
            tname.text = rs.getString(1)
            tdesi.text = rs.getString(2)
            tcourse.text = rs.getString(3)
            tsubject.text = rs.getString(4)
            tmobile.text = rs.getString(5)
            temail.text = rs.getString(6)

        }
        else {
            tname.text = "No Record"
            tdesi.text = "No Record"
            tcourse.text = "No Record"
            tsubject.text = "No Record"
            tmobile.text = "No Record"
            temail.text = "No Record"


        }

        friendsbtn.setOnClickListener {
            var intent = Intent(this,AllStudents::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)



        }
        logoutbtn.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
        addstudbtn.setOnClickListener { var intent = Intent(this,AddStudentInfo::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)  }
    }



}