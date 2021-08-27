package com.kirillov.brainbooster;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView_question;
    String question;
    int rightAnswerButton;
    Button rightButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMenu(View view) {
        setContentView(R.layout.game_layout);
        startGame();


    }

    public void startGame() {
        ArrayList<Button> buttons = new ArrayList<>();
        identifyButtonsToArrayList(buttons);
        question = getQuestion();
        setQuestion(question);
        setRightAnswerButton();
        rightButton = buttons.get(rightAnswerButton);
        setRightAnswer(buttons);
        setWrongAnswers(buttons);
        rightButton.setOnClickListener(view -> {
            if (rightButton.isPressed()) {
                startGame();
            }
        });


    }


    @SuppressLint("ResourceAsColor")
    public void identifyButtonsToArrayList(ArrayList<Button> buttons) {
        Button button1 = findViewById(R.id.button_answer_1);
        Button button2 = findViewById(R.id.button_answer_2);
        Button button3 = findViewById(R.id.button_answer_3);
        Button button4 = findViewById(R.id.button_answer_4);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);


    }

    public void setWrongAnswers(ArrayList<Button> buttons) { //3
        for (int i = 0; i < buttons.size(); i++) {
            if (rightAnswerButton != i) {
                buttons.get(i).setText(String.valueOf(getWrongAnswersAsList()));
            }
        }


    }

    public String getWrongAnswers() { //1
        StringBuilder stringBuilder = new StringBuilder();
        int a = (int) (Math.random() * 100);
        int b = (int) (Math.random() * 100);
        if ((Math.random()) > 0) {
            stringBuilder.append(a).append(" + ").append(b);
        } else {
            stringBuilder.append(a).append(" - ").append(b);
        }
        return stringBuilder.toString();

    }

    public int getWrongAnswersAsList() { //2
        String[] numbers = getWrongAnswers().split(" ");
        if (numbers[1].equals("+")) {
            return Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[2]);
        } else {
            return Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[2]);
        }

    }


    public void setRightAnswerButton() {
        rightAnswerButton = (int) (Math.random() * 4);
    }

    public void setRightAnswer(ArrayList<Button> buttons) {
        buttons.get(rightAnswerButton).setText(String.valueOf(getRightAnswer()));
    }

    @SuppressLint("SetTextI18n")
    public void setQuestion(String question) {
        textView_question = findViewById(R.id.textView_question);
        textView_question.setText(question + "= ?");
    }

    public String getQuestion() {
        int a = (int) (Math.random() * 100);
        int b = (int) (Math.random() * 100);

        StringBuilder stringBuilder = new StringBuilder();
        while (a == b) {
            b = (int) (Math.random() * 100);
        }

        if ((Math.random()) > 0) {
            stringBuilder.append(a).append(" + ").append(b);
            return stringBuilder.toString();
        } else {
            stringBuilder.append(a).append(" - ").append(b);
            return stringBuilder.toString();
        }

    }


    public int getRightAnswer() {
        String[] numbers = question.split(" ");
        if (numbers[1].equals("+")) {
            return Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[2]);
        } else {
            return Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[2]);
        }


    }


}