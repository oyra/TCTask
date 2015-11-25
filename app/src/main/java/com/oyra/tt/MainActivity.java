package com.oyra.tt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oyra.tt.oper.Processor;

import java.util.HashMap;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements IResultsReceiver {

    private static final String WORD_TO_LOOK_FOR = "google";

    private Button mStartBtn;
    private TextView mText0;
    private TextView mText1;
    private TextView mText2;
    private TextView mTitle0;
    private TextView mTitle1;
    private TextView mTitle2;
    private ProgressBar mProgress0;
    private ProgressBar mProgress1;
    private ProgressBar mProgress2;
    private Processor mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mStartBtn = (Button) findViewById(R.id.start_btn);
        mText0 = (TextView) findViewById(R.id.text_0);
        mText1 = (TextView) findViewById(R.id.text_1);
        mText2 = (TextView) findViewById(R.id.text_2);
        mTitle0 = (TextView) findViewById(R.id.title_0);
        mTitle1 = (TextView) findViewById(R.id.title_1);
        mTitle2 = (TextView) findViewById(R.id.title_2);
        mProgress0 = (ProgressBar) findViewById(R.id.progress_0);
        mProgress1 = (ProgressBar) findViewById(R.id.progress_1);
        mProgress2 = (ProgressBar) findViewById(R.id.progress_2);
        String n = getResources().getString(R.string.n);
        mTitle0.setText(getResources().getString(R.string.tt_nth_character_request_result, n));
        mTitle1.setText(getResources().getString(R.string.tt_every_nth_character_request_result, n));
        mHelper = new Processor();

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataViews(mText0, mTitle0, mProgress0);
                showDataViews(mText1, mTitle1, mProgress1);
                showDataViews(mText2, mTitle2, mProgress2);

                mHelper.getData(Processor.TT_10TH_CHAR_REQUEST, MainActivity.this);
                mHelper.getData(Processor.TT_EVERY_10TH_CHAR_REQUEST, MainActivity.this);
                mHelper.getData(Processor.TT_WORD_COUNTER_REQUEST, MainActivity.this);
            }
        });
    }

    private void showDataViews(TextView tv, TextView title, ProgressBar progress) {
        mStartBtn.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProcessingFinished(final char c, final int numOfChar) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgress0.setVisibility(View.GONE);
                mTitle0.setText(getResources().getString(R.string.tt_nth_character_request_result, numOfChar));
                if ('\0' != c) {
                    mText0.setText(String.valueOf(c));
                } else {
                    mText0.setText(getResources().getString(R.string.error));
                }

            }
        });
    }

    @Override
    public void onProcessingFinished(final char[] chars, final int posPeriodOfChars) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgress1.setVisibility(View.GONE);
                mTitle1.setText(getResources().getString(R.string.tt_every_nth_character_request_result, posPeriodOfChars));
                if (chars.length > 0) {
                    mText1.setText(chars, 0, chars.length);
                } else {
                    mText1.setText(getResources().getString(R.string.error));
                }

            }
        });

    }

    @Override
    public void onProcessingFinished(final HashMap<String, Integer> words) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgress2.setVisibility(View.GONE);

                if (words != null && words.containsKey(WORD_TO_LOOK_FOR)) {
                    mText2.setText(getResources().getString(R.string.number_of_words, WORD_TO_LOOK_FOR, words.get(WORD_TO_LOOK_FOR)));

                } else {
                    mText2.setText(getResources().getString(R.string.error));
                }
            }
        });
    }


}
