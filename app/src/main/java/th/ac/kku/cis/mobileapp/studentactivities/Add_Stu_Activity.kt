package th.ac.kku.cis.mobileapp.studentactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add__stu_.*
import th.ac.kku.cis.mobileapp.studentactivities.Model.Student

class Add_Stu_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__stu_)
        btn_bb.setOnClickListener { onBackPressed()}
        btn_add_stu.setOnClickListener {
            saveData()
        }

    }



    private fun saveData(id_stu:String,name_stu:String){

        val myDataBase = FirebaseDatabase.getInstance().getReference("students")
        val id = id_stu
        val name = name_stu

        myDataBase.child(id).child("name").setValue(name).addOnCompleteListener{
            Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveData(){

        val id_stu   = Etxt_stu_number.text.toString().trim()
        val name_stu = txt_name_stu.text.toString().trim()
        val pro_stu = txt_pro_stu.text.toString().trim()
        val fac_stu = txt_fac_stu.text.toString().trim()

        if (id_stu.isEmpty()){
            Toast.makeText(this,"กรุณากรอกข้อมูล รหัสนักศึกษา",Toast.LENGTH_LONG).show()
            return
        }
        if (name_stu.isEmpty()){
            Toast.makeText(this,"กรุณากรอกข้อมูล ชื่อ-นามสกุล",Toast.LENGTH_LONG).show()
            return
        }
        if (pro_stu.isEmpty()){
            Toast.makeText(this,"กรุณากรอกข้อมูล สาขาวิชา",Toast.LENGTH_LONG).show()
            return
        }
        if (fac_stu.isEmpty()){
            Toast.makeText(this,"กรุณากรอกข้อมูล คณะ",Toast.LENGTH_LONG).show()
            return
        }

        val myDataBase = FirebaseDatabase.getInstance().getReference("students")
        val id = id_stu
        val student = Student(name_stu,pro_stu,fac_stu)

        myDataBase.child(id).setValue(student).addOnCompleteListener{
            Toast.makeText(this,"บันทึกเรียบร้อย :) ",Toast.LENGTH_LONG).show()

        }
    }

}
