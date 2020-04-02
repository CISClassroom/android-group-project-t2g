package th.ac.kku.cis.mobileapp.studentactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_delete.*
import th.ac.kku.cis.mobileapp.studentactivities.Adapter.Activity2Adapter
import th.ac.kku.cis.mobileapp.studentactivities.Adapter.ActivityAdapter
import th.ac.kku.cis.mobileapp.studentactivities.Model.Acti3


class DeleteActivity : AppCompatActivity() {
    val TAG = "DeleteActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        Firebase.database.reference.child("activities").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val listView: ListView = findViewById(R.id.listView_act2)
                val activities_list = mutableListOf<Acti3>()

                for (postSnapshot in p0.children) {

                    Log.d(TAG,postSnapshot.key.toString())
                    Log.d(TAG,postSnapshot.child("date_act").toString())
                    activities_list.add(Acti3(postSnapshot.key.toString(),postSnapshot.child("date_act").getValue().toString()))
                }

                listView.adapter = Activity2Adapter(
                    this@DeleteActivity,
                    R.layout.pattern2_act,
                    activities_list
                )
                /*listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as Activity2
                    //Toast.makeText(this@ListActivity, selectedItem.nickname, Toast.LENGTH_SHORT).show()
                    val i = Intent(this@ListActivity2,activityAttendees::class.java)
                    i.putExtra("activityNameS",selectedItem.nickname)
                    i.putExtra("activityNameL",selectedItem.name)
                    startActivity(i)
                }*/
            }
        })

        btn_back_menu.setOnClickListener { onBackPressed()}

    }
}
