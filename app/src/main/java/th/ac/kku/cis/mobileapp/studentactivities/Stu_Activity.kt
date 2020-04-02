package th.ac.kku.cis.mobileapp.studentactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_stu_.*
import th.ac.kku.cis.mobileapp.studentactivities.Adapter.StudentAdapter
import th.ac.kku.cis.mobileapp.studentactivities.Model.Student_T


class Stu_Activity : AppCompatActivity() {

    private val TAG: String = "List Students"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stu_)

        Firebase.database.reference.child("students").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val listView: ListView = findViewById(R.id.listView_stu)
                val code_list = mutableListOf<Student_T>()
                for(postSnapshot in dataSnapshot.children){
                    code_list.add(Student_T(postSnapshot.key.toString()
                        ,postSnapshot.child("name").getValue().toString()
                        ,postSnapshot.child("program").getValue().toString()
                        ,postSnapshot.child("faculty").getValue().toString())
                    )

                }


                listView.adapter = StudentAdapter(
                    this@Stu_Activity,
                    R.layout.activity_pattern__stu_,
                    code_list

                )
                listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as Student_T

                    val i = Intent(this@Stu_Activity,StudentShowActivity::class.java)
                    i.putExtra("send_id_student",selectedItem.id_student)
                    i.putExtra("send_name",selectedItem.name)
                    i.putExtra("send_program",selectedItem.program)
                    i.putExtra("send_faculty",selectedItem.faculty)
                    startActivity(i)

                    //Toast.makeText(this@HtmlActivity, selectedItem.topic, Toast.LENGTH_SHORT).show()
                }





            }
        }
        )


        btn_back_menu.setOnClickListener { onBackPressed()}
    }
}
