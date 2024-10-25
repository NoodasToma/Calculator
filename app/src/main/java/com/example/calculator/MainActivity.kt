package com.example.gpacalculator

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.gpacalculator.databinding.ActivityMainBinding





class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: Activity


    private lateinit var cs1Sem:Semester

    private var choosingCourse:Boolean = false

    private var choosingSemester:Boolean = false

    private var currentCourse:Course = Course.CS

    private var currentSemester:Int = 1

    private lateinit var currentView:ConstraintLayout

    private lateinit var currentSubjects:List<Subject>

    private lateinit var gradeList: List<EditText>

    private var subGradeMap: MutableMap<Subject, Float> = mapOf<Subject,Float>() as MutableMap<Subject, Float>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
        cs1Sem = Semester(listOf(Subject.I2I,Subject.DS,Subject.I2CA,Subject.FOP,Subject.ENG),mainBinding.cs1SemesterLayout.cs1SemesterLayout)
        currentSubjects = cs1Sem.subjects
        gradeList = listOf(mainBinding.score1,mainBinding.score2,mainBinding.score3,mainBinding.score4,mainBinding.score5)

        mainBinding.main.setOnClickListener{
            choosingCourse = false
            showCourses()
            mainBinding.Gpa.text = calcGpa().toString()
        }

        val regex = Regex("([1234567890])*")

        gradeList.forEach { editText ->
            editText.doOnTextChanged { text, _, _, _ ->
                if (!regex.matches(text.toString())) {
                    // Show an error message instead of throwing an exception
                    editText.error = "Invalid input"
                }
                calcGpa()
            }
        }


        mainBinding.chooseCourse.setOnClickListener {
            choosingCourse = true
            showCourses()
            choosingCourse=!choosingCourse
        }

        mainBinding.courseNum.setOnClickListener {
            choosingSemester = true
            showSemesters()
            choosingSemester=!choosingSemester
        }

        mainBinding.CompScience.setOnClickListener {
            currentCourse = Course.CS
            mainBinding.chooseCourse.text = mainBinding.CompScience.text
            choosingCourse = false
            showCourses()
            showSemesterLayout()
        }

        mainBinding.Managemant.setOnClickListener {
            currentCourse = Course.MANAGEMANT
            mainBinding.chooseCourse.text = mainBinding.Managemant.text
            choosingCourse = false
            showCourses()
            showSemesterLayout()
        }

        mainBinding.Math.setOnClickListener {
            currentCourse = Course.MATH
            mainBinding.chooseCourse.text = mainBinding.Math.text
            choosingCourse = false
            showCourses()
            showSemesterLayout()
        }

        mainBinding.semester1.setOnClickListener {
            currentSemester=1
            mainBinding.courseNum.text = mainBinding.semester1.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester2.setOnClickListener {
            currentSemester=2
            mainBinding.courseNum.text = mainBinding.semester2.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester3.setOnClickListener {
            currentSemester=3
            mainBinding.courseNum.text = mainBinding.semester3.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester4.setOnClickListener {
            currentSemester=4
            mainBinding.courseNum.text = mainBinding.semester4.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester5.setOnClickListener {
            currentSemester=5
            mainBinding.courseNum.text = mainBinding.semester5.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester6.setOnClickListener {
            currentSemester=6
            mainBinding.courseNum.text = mainBinding.semester6.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester7.setOnClickListener {
            currentSemester=7
            mainBinding.courseNum.text = mainBinding.semester7.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }
        mainBinding.semester8.setOnClickListener {
            currentSemester=8
            mainBinding.courseNum.text = mainBinding.semester8.text
            choosingSemester = false
            showSemesters()
            showSemesterLayout()
        }


    }


    private fun showCourses(){
        if (choosingCourse){
            mainBinding.CompScience.visibility = View.VISIBLE
            mainBinding.Managemant.visibility = View.VISIBLE
            mainBinding.Math.visibility = View.VISIBLE
        }
        else{
            mainBinding.CompScience.visibility = View.INVISIBLE
            mainBinding.Managemant.visibility = View.INVISIBLE
            mainBinding.Math.visibility = View.INVISIBLE
        }
        calcGpa()
    }


    private fun showSemesters(){
        if (choosingSemester) {
            mainBinding.semester1.visibility = View.VISIBLE
            mainBinding.semester2.visibility = View.VISIBLE
            mainBinding.semester3.visibility = View.VISIBLE
            mainBinding.semester4.visibility = View.VISIBLE
            mainBinding.semester5.visibility = View.VISIBLE
            mainBinding.semester6.visibility = View.VISIBLE
            mainBinding.semester7.visibility = View.VISIBLE
            mainBinding.semester8.visibility = View.VISIBLE
        }
        else {
            mainBinding.semester1.visibility = View.INVISIBLE
            mainBinding.semester2.visibility = View.INVISIBLE
            mainBinding.semester3.visibility = View.INVISIBLE
            mainBinding.semester4.visibility = View.INVISIBLE
            mainBinding.semester5.visibility = View.INVISIBLE
            mainBinding.semester6.visibility = View.INVISIBLE
            mainBinding.semester7.visibility = View.INVISIBLE
            mainBinding.semester8.visibility = View.INVISIBLE
        }
        calcGpa()
    }

    private fun showSemesterLayout(){
        currentView.visibility = View.INVISIBLE
        when(currentSemester){
            1 -> when(currentCourse){
                Course.CS -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            2 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            3 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            4 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            5 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            6 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            7 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }
            8 -> when(currentCourse){
                Course.CS -> mainBinding.cs1SemesterLayout.cs1SemesterLayout.visibility = View.VISIBLE
                Course.MANAGEMANT -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
                Course.MATH -> {
                    cs1Sem.view.visibility= View.VISIBLE
                    currentSubjects = cs1Sem.subjects
                    currentView = mainBinding.cs1SemesterLayout.cs1SemesterLayout
                }
            }

        }

        calcGpa()
    }


    private fun calcGpa():Float{
        mapSubjects()

        var gpSum = 0.0f
        var creditSum = 0.0f
        for (n in  subGradeMap.keys){
            if(subGradeMap[n]==null) continue
            if (subGradeMap[n]!! >=0.5){
                creditSum+=n.credits
                gpSum+=n.credits* subGradeMap[n]!!
            }
        }
        if(creditSum<=0) return 0.0f

        return gpSum/creditSum
    }


    private fun mapSubjects(){
        for ((index, n) in currentSubjects.withIndex()){
            subGradeMap[n] = gradeToGp()[index]

        }

    }


    private fun gradeToGp():MutableList<Float>{
        val gpList:MutableList<Float> = emptyList<Float>() as MutableList
        for ((index, n) in gradeList.withIndex()){
            val x = n.text.toString().toIntOrNull() ?: 0
            if (x>=94) gpList.add(index,4.0F)
            else if (91<=x) gpList.add(index,3.7f)
            else if (88<=x) gpList.add(index,3.4f)
            else if (85<=x) gpList.add(index,3.1f)
            else if (81<=x) gpList.add(index,2.8f)
            else if (78<=x) gpList.add(index,2.5f)
            else if (74<=x) gpList.add(index,2.2f)
            else if (71<=x) gpList.add(index,1.9f)
            else if (68<=x) gpList.add(index,1.6f)
            else if (64<=x) gpList.add(index,1.3f)
            else if (61<=x) gpList.add(index,1.0f)
            else if (56<=x) gpList.add(index,0.8f)
            else if (51<=x) gpList.add(index,0.5f)
            else  gpList.add(index,0.0f)

        }

        return gpList
    }








}