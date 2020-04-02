package th.ac.kku.cis.mobileapp.studentactivities.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.database.FirebaseDatabase
import th.ac.kku.cis.mobileapp.studentactivities.Model.Acti3
import th.ac.kku.cis.mobileapp.studentactivities.R

class Activity2Adapter (var mCtx: Context, var resource:Int, var items:List<Acti3>)
    : ArrayAdapter<Acti3>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var tvActivityName : TextView = view.findViewById(R.id.txt_act_name)
        var tvActivityDate : TextView = view.findViewById(R.id.txt_act_date)


        var activity: Acti3 = items[position]
        tvActivityName.text = activity.actname
        tvActivityDate.text = activity.date

        var btnDel : ImageView = view.findViewById(R.id.btn_delete)


        btnDel.setOnClickListener {
            Toast.makeText(context,activity.actname,Toast.LENGTH_LONG).show()
            val id = activity.actname
            val myDatabase = FirebaseDatabase.getInstance().getReference("activities")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("ยืนยันการลบ?")
            builder.setMessage("ยืนยันการลบกิจกรรม "+id)
            builder.setPositiveButton("ยืนยัน"){dialog, which ->
                myDatabase.child(id).removeValue().addOnSuccessListener {
                    Toast.makeText(context,"ลบกิจกรรม "+id+" เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
                }

            }
            builder.setNegativeButton("กลับ"){dialog,which ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return view
    }
}