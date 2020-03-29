package th.ac.kku.cis.mobileapp.studentactivities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import th.ac.kku.cis.mobileapp.studentactivities.Model.Student_T

import th.ac.kku.cis.mobileapp.studentactivities.R

class StudentAdapter (var mCtx: Context, var resource:Int, var item:List<Student_T>)
    : ArrayAdapter<Student_T>( mCtx , resource , item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource,null)
        var tv_student : TextView = view.findViewById(R.id.txt_id_student)
        var tv_stu_name : TextView = view.findViewById(R.id.txt_stu_name)

        var student: Student_T = item[position]
        tv_student.text = student.id_student
        tv_stu_name.text = student.name
        return view
    }
}