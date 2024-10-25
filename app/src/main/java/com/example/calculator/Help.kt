package com.example.calculator


import androidx.constraintlayout.widget.ConstraintLayout

enum class Course{
    CS,MANAGEMANT,MATH
}

enum class Subject(val credits:Int, val course: Course){


    I2I(6,Course.CS),FOP(6,Course.CS),DS(9,Course.CS),I2CA(6,Course.CS),ENG(3,Course.CS,Course.MANAGEMANT);

    constructor(credits: Int, course:Course,course2: Course) : this(credits,course)
}


class Semester(val subjects: List<Subject>, val view: ConstraintLayout)