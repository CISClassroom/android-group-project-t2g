package th.ac.kku.cis.mobileapp.studentactivities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import th.ac.kku.cis.mobileapp.studentactivities.Model.Acti2
import th.ac.kku.cis.mobileapp.studentactivities.R

class ActivityAdapter (var mCtx: Context, var resource:Int, var item:List<Acti2>)
    : ArrayAdapter<Acti2>( mCtx , resource , item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource,null)
        var tv_nameAct : TextView = view.findViewById(R.id.txt_act_name)
        var tv_dateAct : TextView = view.findViewById(R.id.txt_act_date)

        var activities: Acti2 = item[position]
        tv_nameAct.text = activities.actname
        tv_dateAct.text = activities.date
        return view
    }
}