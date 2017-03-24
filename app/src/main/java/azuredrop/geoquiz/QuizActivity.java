package azuredrop.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";

    private Button mTrueBtn;
    private Button mFalseBtn;
    private Button mNextBtn;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_america, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // TextView
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        // TrueBtn
        mTrueBtn = (Button) findViewById(R.id.true_btn);
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // FalseBtn
        mFalseBtn = (Button) findViewById(R.id.false_btn);
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // NextBtn
        mNextBtn = (Button) findViewById(R.id.next_btn);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuestion(){
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
    }

    private void checkAnswer(Boolean userPressedTrue) {
        int messageResId = 0;
        if (userPressedTrue == mQuestionBank[mCurrentIndex].isTrueQuestion()) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
