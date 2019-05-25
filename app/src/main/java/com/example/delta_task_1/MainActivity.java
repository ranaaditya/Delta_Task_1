package com.example.delta_task_1;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public TextView result;
    public TextView loose;
    public TextView text;
    public TextView number;
    public Button btn_start;
    public Button btn_guess;
    public EditText editText;
    public EditText editText2;
    public int remwon;
    public int remlost;
    public String str;
    public String guess_str;
    public String rand_str;
    public ConstraintLayout constraintLayout;
    public int cal = 0;
    public int random_age = 50;
    public int flag = 5;                                                                //maximum permitted number of trials
    public int guess;
    public int correct;                                                                //for correct no. of guesses.
    public int wrong;                                                                 //for wrong   no. of guesses.
   public boolean state=true;
   public int mat;

    public SharedPreferences won;
    public SharedPreferences lost;

    public String redcolortable[] = {"#990000", "#b30000", "#cc0000", "#e60000", "#ff0000"};            // color array containing the codes for various gradients/tints of red color .
    public String greencolortable[] = {"#1B5E20", "#009000", "00b300", "00cc00", "00e600"};             // color array containing the codes for various gradients/tints of green color .

    // Strings for the results of the guesses made by the  death's daughter.
    public String lower = "lower guess";
    public String higher = "higher";
    public String maximum = " OOPS!!!  LIMIT EXCEED ,TRY AGAIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        if (won != null) {
            correct = won.getInt("com.example.delta_task_1.won", 0);

        }
        if (lost != null) {
            wrong = lost.getInt("com.example.delta_task_1.lost", 0);

        }
        if(savedInstanceState!=null){
            random_age=savedInstanceState.getInt("randomage");
            state=savedInstanceState.getBoolean("startstate");
            mat=savedInstanceState.getInt("trialsleft");
            start_game(mat,state);
        }




        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rand_str = editText.getText().toString();
                try {
                    random_age = Integer.parseInt(rand_str);
                }catch(NumberFormatException e) {
                    e.printStackTrace();
                }

                if (editText.getText().toString().equals("")||random_age > 100 || random_age < 1) {
                    Toast.makeText(MainActivity.this, "type a valid age", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    return;
                }
                else {


                    editText.setHint("enter the guess below ");

                    str = editText.getText().toString();
                    text.setText("");
                    loose.setText("");
                    result.setText("");


                         state=false;
                    start_game(flag,state);
                }                                                             // starting the game after pressing of START button.
            }
        });
    }

    void init() {
        btn_start = findViewById(R.id.button);
        btn_guess = findViewById(R.id.button2);
        editText = findViewById(R.id.trial);
        editText2 = findViewById(R.id.guess);
        loose = findViewById(R.id.lose);
        text = findViewById(R.id.textView2);
        result = findViewById(R.id.resulttext);
        number = findViewById(R.id.textView);
        constraintLayout = findViewById(R.id.backr);
        editText2.setEnabled(false);
        btn_guess.setEnabled(false);
        won = getSharedPreferences("com.example.delta_task_1.won", MODE_PRIVATE);
        lost = getSharedPreferences("com.example.delta_task_1.lost", MODE_PRIVATE);



    }


    // for changing the app's background colour according to the  accuracy with which guess she  made.

    public void change_background(ConstraintLayout constr, int i) {
        if (i == 0) {
            constr.setBackgroundColor(Color.parseColor("#1B5E20"));

        } else if (i == 1 || i == -1) {

            constr.setBackgroundColor(Color.parseColor("#2E7D32"));
        } else if (i == 2 || i == -2) {

            constr.setBackgroundColor(Color.parseColor("#388E3C"));

        } else if (i == 3 || i == -3) {

            constr.setBackgroundColor(Color.parseColor("#43A047"));

        } else if (i == 4 || i == -4) {

            constr.setBackgroundColor(Color.parseColor("#4CAF50"));

        } else if (i == 5 || i == -5) {

            constr.setBackgroundColor(Color.parseColor("#66BB6A"));

        } else if (i == 6 || i == -6) {

            constr.setBackgroundColor(Color.parseColor("#81C784"));

        } else if (i == 7 || i == -7) {
            constr.setBackgroundColor(Color.parseColor("#A5D6A7"));

        } else if (i == 8 || i == -8) {
            constr.setBackgroundColor(Color.parseColor("#C8E6C9"));

        } else if (i == 9 || i == -9) {
            constr.setBackgroundColor(Color.parseColor("#EF9A9A"));

        } else if (i == 10 || i == -10) {
            constr.setBackgroundColor(Color.parseColor("#E57373"));

        } else if (i == 11 || i == -11) {
            constr.setBackgroundColor(Color.parseColor("#EF5350"));

        } else if (i == 12 || i == -12) {
            constr.setBackgroundColor(Color.parseColor("#F44336"));

        } else if (i == 13 || i == -13) {
            constr.setBackgroundColor(Color.parseColor("#E53935"));

        } else if (i == 14 || i == -14) {
            constr.setBackgroundColor(Color.parseColor("#D32F2F"));

        } else if (i == 15 || i == -15) {
            constr.setBackgroundColor(Color.parseColor("#C62828"));

        } else if (i == 16 || i == -16) {
            constr.setBackgroundColor(Color.parseColor("#B71C1C"));

        } else {
            constr.setBackgroundColor(Color.parseColor("#B71C1C"));
        }
    }


    public void start_game( int trials,boolean state_btn) {
        editText.setText("");
        flag=trials;

        if (state_btn==false) {
            btn_start.setEnabled(false);
            editText.setEnabled(false);
            btn_guess.setEnabled(true);
            editText2.setEnabled(true);

            loose.setText("");
            editText.setHint("guess below");
        }


        btn_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess_str = editText2.getText().toString();
                try {
                    guess = Integer.parseInt(guess_str);                        //converting the string into integer .
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (guess > 100 || guess < 1) {
                    Toast.makeText(MainActivity.this, "type a valid age", Toast.LENGTH_SHORT).show();
                    editText2.setText("");
                    return;
                }

                if (editText2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "enter a valid age", Toast.LENGTH_SHORT).show();
                    return;
                } else



                    if (flag >= 1) {


                    cal = Math.abs(guess - random_age);                            //for converting the difference into positive integer.


                    if (guess == random_age) {
                        correct = correct + 1;
                        SharedPreferences.Editor editor2 = won.edit();
                        editor2.putInt("com.example.delta_task_1.won", correct);
                        editor2.commit();
                        remwon = won.getInt("com.example.delta_task_1.won", 0);
                        remlost = lost.getInt("com.example.delta_task_1.lost", 0);
                        Toast.makeText(MainActivity.this, "exact guess", Toast.LENGTH_SHORT).show();
                        loose.setText("!!!win!!!");
                        btn_guess.setEnabled(false);
                        btn_start.setEnabled(true);
                        editText.setEnabled(true);
                        editText2.setEnabled(false);
                        text.setText("TEST WON-" + remwon + "     " + "TEST LOST -" + remlost);
                        flag = 5;
                        mat=flag;
                        editText2.setText("");
                        state=true;
                        result.setText("");
                        editText.setHint("enter age to be guessed");
                        constraintLayout.setBackgroundColor(Color.parseColor(greencolortable[0]));





                    } else if (guess < random_age) {
                        change_background(constraintLayout, cal);

                        result.setText(lower);
                        flag = flag - 1;
                        mat=flag;
                        editText2.setText("");


                    } else if (guess > random_age) {

                        result.setText(higher);
                        flag = flag - 1;
                        mat=flag;
                        editText2.setText("");
                        change_background(constraintLayout, cal);
                    }

                } else {

                    wrong = wrong + 1;
                    SharedPreferences.Editor editor = lost.edit();
                    editor.putInt("com.example.delta_task_1.lost", wrong);
                    editor.commit();
                    loose.setText(" LOST  ");
                    constraintLayout.setBackgroundColor(Color.parseColor("#4d4d4d"));
                    remwon = won.getInt("com.example.delta_task_1.won", 0);
                    remlost = lost.getInt("com.example.delta_task_1.lost", 0);
                    text.setText("TEST WON-" + remwon + "     " + "TEST LOST -" + remlost);
                    wrong = remlost;
                    correct = remwon;
                    result.setTextSize(28);
                    result.setText(maximum);
                    flag = 5;
                    mat=flag;
                    editText.setEnabled(true);
                    btn_start.setEnabled(true);
                    editText2.setEnabled(false);
                    btn_guess.setEnabled(false);
                    editText.setHint("Age to be guessed");
                    editText.setHint("enter age to be guessed");
                    editText2.setText("");
                    state=true;


                }
            }
        });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("randomage",random_age);
        outState.putInt("trialsleft",mat);
        outState.putBoolean("startstate",state);
        super.onSaveInstanceState(outState);
    }
}



