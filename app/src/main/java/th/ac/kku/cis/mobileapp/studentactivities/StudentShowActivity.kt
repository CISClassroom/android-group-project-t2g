package th.ac.kku.cis.mobileapp.studentactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_student_show.*

class StudentShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_show)

        val i = intent
        var get_id_student = i.getStringExtra("send_id_student")
        var get_name = i.getStringExtra("send_name")
        var get_program = i.getStringExtra("send_program")
        var get_faculty = i.getStringExtra("send_faculty")


        txt_id_student.text = get_id_student
        txt_name.text = get_name
        txt_program.text =get_program
        txt_faculty.text =get_faculty
        btn_back_menu.setOnClickListener { onBackPressed() }

    }
}
