package th.ac.kku.cis.mobileapp.studentactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_show_act.*
import th.ac.kku.cis.mobileapp.studentactivities.Adapter.ActivityAdapter
import th.ac.kku.cis.mobileapp.studentactivities.Model.Acti2

class ShowActActivity : AppCompatActivity() {
    val TAG = "ListShowACt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_act)


        Firebase.database.reference.child("activities").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val listView: ListView = findViewById(R.id.listView_act)
                val activities_list = mutableListOf<Acti2>()

                for (postSnapshot in p0.children) {

                    Log.d(TAG,postSnapshot.key.toString())
                    Log.d(TAG,postSnapshot.child("date_act").toString())
                    activities_list.add(Acti2(postSnapshot.key.toString(),postSnapshot.child("date_act").getValue().toString()))
                }

                listView.adapter = ActivityAdapter(
                    this@ShowActActivity,
                    R.layout.pattern_act,
                    activities_list
                )
                listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as Acti2
                    //Toast.makeText(this@ListActivity, selectedItem.nickname, Toast.LENGTH_SHORT).show()
                    val i = Intent(this@ShowActActivity,ActDetailActivity::class.java)
                    i.putExtra("activityName",selectedItem.actname)
                    i.putExtra("activityDate",selectedItem.date)
                    startActivity(i)
                }
            }
        })

        btn_back_menu.setOnClickListener { onBackPressed()}
    }
}
