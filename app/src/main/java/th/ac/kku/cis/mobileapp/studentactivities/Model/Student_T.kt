package th.ac.kku.cis.mobileapp.studentactivities.Model

class Student_T (val id_student :String,
                 val name :String,
                 val program:String,
                 val faculty:String)
{
    constructor():this("", "","","")
}