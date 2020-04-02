package th.ac.kku.cis.mobileapp.studentactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_act_.*

class Act_Activity : AppCompatActivity() {

    val TAG = "Act_activity"
    var idStu :String? = ""
    var nameStudent :String? = ""
    var selectAct :String? = ""
    var AllAct = arrayOf("==เลือกกิจกรรม==")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_)

        Firebase.database.reference.child("activities").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    AllAct += postSnapshot.key.toString()
//                        Log.d(TAG,"========>"+postSnapshot.key.toString())
                }

                spin_act.adapter = ArrayAdapter(this@Act_Activity,android.R.layout.simple_spinner_item,AllAct)

                spin_act.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        var activity = AllAct[position]
                        if(activity=="==เลือกกิจกรรม=="){
                            selectAct=""
                        }else{
                            selectAct=activity
                        }
                        Log.d(TAG,"Activity Select = "+selectAct.toString())
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selectAct=""
                    }

                }

                btn_con.setOnClickListener {

                    //======================================================================================
                    Firebase.database.reference.child("students").child(txt_num_stu.text.toString()).addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {}
                        override fun onDataChange(p0: DataSnapshot) {
                            idStu = txt_num_stu.text.toString()
                            nameStudent = p0.child("name").getValue().toString()

                            if(nameStudent=="null"){
                                Toast.makeText(this@Act_Activity,"ไม่พบรหัสนักศึกษาในระบบ",Toast.LENGTH_SHORT).show()
                            }

                            else if(idStu==""||selectAct==""||nameStudent==""){
                                Toast.makeText(this@Act_Activity,"ข้อมูลไม่ครบถ้วน",Toast.LENGTH_SHORT).show()
                            }

                            else{
                                Toast.makeText(this@Act_Activity,"ข้อมูลครบถ้วน",Toast.LENGTH_SHORT).show()
                                AddtoFirebase(idStu!!,nameStudent!!,selectAct!!)
                            }

                        }
                    })


                    //=======================================================================================

                    /*if(idStudent==""||selectActivity==""||nameStudent==""){
                        Toast.makeText(this@Regis,"ข้อมูลไม่ครบถ้วน",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@Regis,"ข้อมูลครบถ้วน",Toast.LENGTH_SHORT).show()
                        AddtoFirebase(idStudent!!,nameStudent!!,selectActivity!!)
                        //AddtoFirebase(subId!!,subName!!,selectRoom!!,selectDay!!,selectMonth!!,selectYear!!,selectTime!!,subSeatstart!!)
                    }*/

                }
            }


            override fun onCancelled(p0: DatabaseError) {}
        })

    }

    private fun AddtoFirebase(idStudent:String,nameStudent:String,selectActivity:String){

        val idStudent = idStudent
        val nameStudent= nameStudent
        val nameActivity= selectActivity
        val myDatabase = FirebaseDatabase.getInstance().getReference("act_stu")

        myDatabase.child(nameActivity).child(idStudent).setValue(nameStudent).addOnCompleteListener{
            Toast.makeText(this,"เพิ่มข้อมูลสำเร็จ",Toast.LENGTH_SHORT).show()
            val intent = intent
            finish()
            startActivity(intent)
        }
    }
}

