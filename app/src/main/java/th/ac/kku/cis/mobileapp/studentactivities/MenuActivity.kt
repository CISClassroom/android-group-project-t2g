package th.ac.kku.cis.mobileapp.studentactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btn_to_activity.setOnClickListener{ startActivity(Intent(this@MenuActivity, Act_Activity::class.java))}
        btn_to_stu.setOnClickListener{ startActivity(Intent(this@MenuActivity, Stu_Activity::class.java))}
        btn_add_act.setOnClickListener{ startActivity(Intent(this@MenuActivity, Add_Act_Activity::class.java))}
        btn_add_stu.setOnClickListener{ startActivity(Intent(this@MenuActivity, Add_Act_Activity::class.java))}
    }
}
