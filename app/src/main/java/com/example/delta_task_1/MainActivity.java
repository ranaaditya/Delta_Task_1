package com.example.delta_task_1;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public TextView result;
    public TextView loose;
    public  TextView text;
    public TextView number;
    public Button btn_start ;
    public  Button btn_guess;
    public EditText editText;
    public  EditText editText2;
    public Random rand;
    public  String str;
    public  String guess_str;
    public String rand_str;
    public ConstraintLayout constraintLayout;
    public int cal=0;
    public  int converter=0;
    public int  random_age=0;
    public int flag=0;
    public  int guess=0;
    public int correct=0;                                                                //for correct no. of guesses.
    public  int wrong=0;                                                                 //for wrong   no. of guesses.

    public String redcolortable[]={"#990000","#b30000","#cc0000","#e60000","#ff0000"};            // color array containing the codes for various gradients/tints of red color .
    public String greencolortable[]={"#008000","#009000","00b300","00cc00","00e600"};             // color array containing the codes for various gradients/tints of green color .

    // Strings for the results of the guesses made by the  death's daughter.
    public  String lower="lower guess";
    public  String higher="higher";
    public  String ok="exact";
    public  String maximum=" OOPS!!!  LIMIT EXCEED ,TRY AGAIN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
            init();
       btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random_age=rand.nextInt(100);                                                  // generating the random number between 1 to 1000.
                rand_str=new Integer(random_age).toString();
                str = editText.getText().toString();
                text.setText("");
                loose.setText("");
                result.setText("");

                try {
                    converter = Integer.parseInt(str);                                               //parsing the string into the integer.
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }

                start_game(converter);                                                               // starting the game after pressing of START button.
            }
        });
    }
    void init(){
        
        btn_start=findViewById(R.id.button);
        btn_guess=findViewById(R.id.button2);
        editText=findViewById(R.id.trial);
        editText2=findViewById(R.id.guess);
        loose=findViewById(R.id.lose);
        text=findViewById(R.id.textView2);
        result= findViewById(R.id.resulttext);
        number=findViewById(R.id.textView);
        constraintLayout=findViewById(R.id.backr);
        editText2.setEnabled(false);
        btn_guess.setEnabled(false);   
        
        
        
    }



            // for changing the app's background colour according to the  accuracy with which guess she  made.

    public  void change_background(ConstraintLayout constr,int i){
        if (i==0){
            constr.setBackgroundColor(Color.parseColor("#00900"));

        }else if (i==1 ||i==-1){

            constr.setBackgroundColor(Color.parseColor("#00b300"));
        }else if (i==2 || i==-2){

            constr.setBackgroundColor(Color.parseColor("#00CC00"));

        }else if (i==3 || i==-3){

            constr.setBackgroundColor(Color.parseColor("#00e600"));

        }else if (i==4||i==-4){

            constr.setBackgroundColor(Color.parseColor("#00ff00"));

        }else if (i==5||i==-5){

            constr.setBackgroundColor(Color.parseColor("#ff4d4d"));

        }else if (i==6||i==-6){

            constr.setBackgroundColor(Color.parseColor("#ff3333"));

        }else if (i==7||i==-7){
            constr.setBackgroundColor(Color.parseColor("#ff1a1a"));

        }else if (i==8||i==-8){
            constr.setBackgroundColor(Color.parseColor("#ff0000"));

        }else if (i==9||i==-9){
            constr.setBackgroundColor(Color.parseColor("#cc0000"));

        }else{
            constr.setBackgroundColor(Color.parseColor("#cc0000"));

        }

    }



    public void start_game(final int trials){
        

        correct=0;
        wrong=0;
        flag=trials;


        btn_start.setEnabled(false);
        editText.setEnabled(false);

        btn_guess.setEnabled(true);
        editText2.setEnabled(true);

        loose.setText("");


        btn_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (editText2.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"enter a valid age",Toast.LENGTH_SHORT).show();
                }
                else

                if (flag > 1) {
                    guess_str = editText2.getText().toString();
                    try {
                        guess = Integer.parseInt(guess_str);                        //converting the string into integer .
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    cal = Math.abs(guess - random_age);                            //for converting the difference into positive integer.


                    if (guess == random_age) {

                        correct = correct + 1;
                        result.setText(ok);
                        flag = flag - 1;
                        editText2.setText("");
                        constraintLayout.setBackgroundColor(Color.parseColor(greencolortable[0]));


                    } else if (guess < random_age) {
                        change_background(constraintLayout, cal);

                        wrong = wrong + 1;
                        result.setText(lower);
                        flag = flag - 1;
                        editText2.setText("");


                    } else if (guess>random_age){


                        wrong = wrong + 1;
                        result.setText(higher);
                        flag = flag - 1;
                        editText2.setText("");
                        change_background(constraintLayout, cal);
                    }

                }

             else{
                    constraintLayout.setBackgroundColor(Color.parseColor("#4d4d4d"));
                    if (correct!=trials)
                    text.setText("win - " + correct + "  " + "loose - " + wrong);
                    if (correct==trials){
                        text.setEnabled(false);
                        loose.setText(" !!! oops Better luck next time  !!! ");
                    }else loose.setText(" LOST  ");
                    result.setTextSize(15);
                    number.setText("");
                    result.setText(maximum);
                    editText.setEnabled(true);
                    btn_start.setEnabled(true);
                    editText2.setEnabled(false);
                    btn_guess.setEnabled(false);
                    editText.setText("");
                    editText2.setText("");

                }
            }
        });



    }





}



