package th.ac.kku.cis.mobileapp.studentactivities


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add__act_.*
import th.ac.kku.cis.mobileapp.studentactivities.Model.Acti


import java.util.*

class Add_Act_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__act_)
        btn_bb.setOnClickListener { onBackPressed()}
        btn_add_act.setOnClickListener {
            saveData()
        }


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        btn_pickdate.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                btn_pickdate.setText(""+mDay+"/"+mMonth+"/"+mYear)
                    },year,month,day)
            dpd.show()
        }
    }

    private fun saveData(){

        val name_act= txt_name_act.text.toString().trim()
        val date_act = btn_pickdate.text.toString().trim()

        if (name_act.isEmpty()){
            Toast.makeText(this,"กรุณากรอกข้อมูล ชื่อกิจกรรม",Toast.LENGTH_LONG).show()
            return
        }
        if (date_act.isEmpty()){
            Toast.makeText(this,"กรุณากรอกข้อมูล วันเวลาที่จัดกิจกรรม",Toast.LENGTH_LONG).show()
            return
        }

        val myDataBase = FirebaseDatabase.getInstance().getReference("activities")
        val name = name_act
        val acti = Acti(date_act)

        myDataBase.child(name).setValue(acti).addOnCompleteListener{
            Toast.makeText(this,"บันทึกเรียบร้อย :) ",Toast.LENGTH_LONG).show()

        }
    }
}
