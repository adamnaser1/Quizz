package com.example.quizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button startQuizButton;
    private TextView questionText;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private TextView feedbackText;
    private TextView finalScoreText;

    private String[][] questions = {
            {"Quelle est l'équipe nationale qui gagne la coupe du monde 2022 ?", "Argentine", "France", "Brasil", "Argentine"},
            {"Qui est le joueur qui gagne le ballon d'or 2023  ?", "Lionel Messi", "Karim Benzema", "Killian Mbappe ", "Lionel Messi"},
            {"Qui est l'entraineur actuel de l'équipe nationale de Tunisie  ?", "Jalel Kadri", "Fawzi Benzarti", "Maher Kanzari", "Jalel Kadri"},
            {"A quelle année l'équipe nationale de Tunisie gagne la coupe d'afrique ?", "2004", "2002", "1998", "2004"},
            {"Qui est le butteur historique de Champions League European ?", "Lionel Messi", "Maradona", "Cristiano Ronaldo", "Cristiano Ronaldo"}
    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcomeText);
        startQuizButton = findViewById(R.id.startQuizButton);
        questionText = findViewById(R.id.questionText);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        feedbackText = findViewById(R.id.feedbackText);
        finalScoreText = findViewById(R.id.finalScoreText);

        welcomeText.setVisibility(View.VISIBLE);
        startQuizButton.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.GONE);
        option1Button.setVisibility(View.GONE);
        option2Button.setVisibility(View.GONE);
        option3Button.setVisibility(View.GONE);
        feedbackText.setVisibility(View.GONE);
        finalScoreText.setVisibility(View.GONE);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option1Button.getText().toString());
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option2Button.getText().toString());
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(option3Button.getText().toString());
            }
        });
    }

    private void startQuiz() {
        welcomeText.setVisibility(View.GONE);
        startQuizButton.setVisibility(View.GONE);
        questionText.setVisibility(View.VISIBLE);
        option1Button.setVisibility(View.VISIBLE);
        option2Button.setVisibility(View.VISIBLE);
        option3Button.setVisibility(View.VISIBLE);
        feedbackText.setVisibility(View.GONE);
        finalScoreText.setVisibility(View.GONE);

        currentQuestionIndex = 0;
        score = 0;
        updateQuestionAndOptions();
    }

    private void updateQuestionAndOptions() {
        if (currentQuestionIndex < questions.length) {
            questionText.setText(questions[currentQuestionIndex][0]);
            option1Button.setText(questions[currentQuestionIndex][1]);
            option2Button.setText(questions[currentQuestionIndex][2]);
            option3Button.setText(questions[currentQuestionIndex][3]);
        } else {
            endQuiz();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(questions[currentQuestionIndex][4])) {
            score++;
            feedbackText.setText("Correct!");
        } else {
            feedbackText.setText("Incorrect. La réponse correcte est : " + questions[currentQuestionIndex][4]);
        }
        feedbackText.setVisibility(View.VISIBLE);

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            updateQuestionAndOptions();
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        questionText.setVisibility(View.GONE);
        option1Button.setVisibility(View.GONE);
        option2Button.setVisibility(View.GONE);
        option3Button.setVisibility(View.GONE);
        feedbackText.setVisibility(View.GONE);

        finalScoreText.setText("Quiz terminé. Votre score est de " + score + " sur " + questions.length);
        finalScoreText.setVisibility(View.VISIBLE);

        Toast.makeText(this, "Quiz terminé. Votre score est de " + score + " sur " + questions.length, Toast.LENGTH_SHORT).show();
    }
}
