package com.example.calculator

import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {




    lateinit var mainBinding:ActivityMainBinding
    var number : String? = null

    var firstNumber:Double = 0.0

    var lastNumber:Double = 0.0

    var status:String? = null

    var operator:Boolean = false

    var history : String = ""

    var currentResult : String = ""

    var dotControll : Boolean = true


    @SuppressLint("NewApi")
    var myFormatter = DecimalFormat("######.######")

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

        mainBinding.textViewResult.text = "0"

        mainBinding.btnZero.setOnClickListener{
            onNumberClicked("0")
        }
        mainBinding.btnOne.setOnClickListener{
            onNumberClicked("1")
        }
        mainBinding.btnTwo.setOnClickListener{
            onNumberClicked("2")
        }
        mainBinding.btnThree.setOnClickListener{
            onNumberClicked("3")
        }
        mainBinding.btnFour.setOnClickListener{
            onNumberClicked("4")
        }
        mainBinding.btnFive.setOnClickListener{
            onNumberClicked("5")
        }
        mainBinding.btnSix.setOnClickListener{
            onNumberClicked("6")
        }

        mainBinding.btnSeven.setOnClickListener{
            onNumberClicked("7")
        }

        mainBinding.btnEight.setOnClickListener{
            onNumberClicked("8")
        }

        mainBinding.btnNine.setOnClickListener{
            onNumberClicked("9")
        }

        mainBinding.btnAC.setOnClickListener{
            aC()
        }

        mainBinding.btnDelete.setOnClickListener{

            number?.let{
                if(it.length==0){
                    mainBinding.textViewResult.text = "Cant delete anymore you fat lesbian"
                }
                if (it.length==1){
                    aC()
                }
             number=it.substring(0,it.length-1)
             mainBinding.textViewResult.text=number
             dotControll = !number!!.contains(".")
          }
        }

        mainBinding.btnDivive.setOnClickListener{

            history = mainBinding.textViewHistory.text.toString()
            currentResult =  mainBinding.textViewResult.text.toString()

            if(status=="equals") operator =true


            if (operator){
                when(status){
                    "Mul"->Mul()
                    "Div"->Divide()
                    "plus"->add()
                    "minus"->Minus()
                    else ->firstNumber=mainBinding.textViewResult.text.toString().toDouble()
                }
                mainBinding.textViewHistory.text = history.plus("/")
            }
            status="Div"
            operator=false
            number=null
            dotControll=true

        }

        mainBinding.btnMul.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult =  mainBinding.textViewResult.text.toString()

            if(status=="equals") operator =true

            if (operator){
                when(status){
                    "Mul"->Mul()
                    "Div"->Divide()
                    "plus"->add()
                    "minus"->Minus()
                    else ->firstNumber=mainBinding.textViewResult.text.toString().toDouble()
                }
                mainBinding.textViewHistory.text = history.plus("*")
            }
            status="Mul"
            operator=false
            number=null
            dotControll=true

        }

        mainBinding.btnMinus.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult =  mainBinding.textViewResult.text.toString()

            if(status=="equals") operator =true

            if (operator){
                when(status){
                    "Mul"->Mul()
                    "Div"->Divide()
                    "plus"->add()
                    "minus"->Minus()
                    else ->firstNumber=mainBinding.textViewResult.text.toString().toDouble()
                }
                mainBinding.textViewHistory.text = history.plus("-")
            }
            status="minus"
            operator=false
            number=null
            dotControll=true

        }

        mainBinding.btnPlus.setOnClickListener{
            history = mainBinding.textViewHistory.text.toString()
            currentResult =  mainBinding.textViewResult.text.toString()

            if(status=="equals") operator =true

            if (operator){
                when(status){
                    "Mul"->Mul()
                    "Div"->Divide()
                    "plus"->add()
                    "minus"->Minus()

                    else ->firstNumber=mainBinding.textViewResult.text.toString().toDouble()
                }
                mainBinding.textViewHistory.text = history.plus("+")
            }
            status="plus"
            operator=false
            number=null
            dotControll=true
        }

        mainBinding.btnDot.setOnClickListener{
            if (dotControll){
                if (number==null) number = "0."
                else number= "$number."

                mainBinding.textViewResult.text = number
            }
            dotControll=false

        }

        mainBinding.btnEquals.setOnClickListener{

            history = mainBinding.textViewHistory.text.toString()
            currentResult =  mainBinding.textViewResult.text.toString()



            if (operator){
                when(status){
                    "Mul"->Mul()
                    "Div"->Divide()
                    "plus"->add()
                    "minus"->Minus()
                    "equals"-> mainBinding.textViewHistory.text = history
                    else ->firstNumber=mainBinding.textViewResult.text.toString().toDouble()
                }
                mainBinding.textViewHistory.text = history.plus("=").plus(mainBinding.textViewResult.text.toString())
            }
            status = "equals"
            operator=false
            dotControll=true
        }

    }

    fun onNumberClicked (clickedNum : String){
        if (status!="equals") {
            history = mainBinding.textViewHistory.text.toString()
            currentResult = clickedNum
            if (number == null) {
                number = clickedNum
            } else number += clickedNum

            mainBinding.textViewResult.text = number
            mainBinding.textViewHistory.text = history.plus(clickedNum)

            operator = true
        }else
        {
            aC()
            onNumberClicked(clickedNum)
        }
    }


    @SuppressLint("NewApi")
    fun add(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()

        firstNumber+=lastNumber

        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }

    @SuppressLint("NewApi")
    fun Minus(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()

        firstNumber-=lastNumber

        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }

    @SuppressLint("NewApi")
    fun Mul(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()

        firstNumber*=lastNumber

        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }

    @SuppressLint("NewApi")
    fun Divide(){
        lastNumber = mainBinding.textViewResult.text.toString().toDouble()

        if (lastNumber==0.0) {
            Toast.makeText(applicationContext,"YOU are Gay",Toast.LENGTH_LONG).show()
            mainBinding.textViewResult.text = "can't divide by zero retard, also u gay"
            return
        }

        firstNumber/=lastNumber

        mainBinding.textViewResult.text = myFormatter.format(firstNumber)
    }


    fun aC(){
        number=null
        status=null
        mainBinding.textViewResult.text="0"
        mainBinding.textViewHistory.text = ""
        firstNumber=0.0
        lastNumber= 0.0
        dotControll=true

    }


}