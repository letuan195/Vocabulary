package com.tuan.vocabulary.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tuan.vocabulary.R;
import com.tuan.vocabulary.database.DatabaseHandler;
import com.tuan.vocabulary.review.Word;
import com.tuan.vocabulary.review.WordAdapter;
import com.tuan.vocabulary.ultis.Ultis;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tuanl on 20-Feb-17.
 */

public class FragmentReview extends Fragment {
    public static final int THE_NUMBER_OF_WORDS = 5;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private ArrayList<Word> mListWord;

    private EditText mEditTextValue;
    private TextView mTextViewRandomWord;
    private Button mButton;

    private DatabaseHandler mDatabase;
    private ArrayList<Word> mWords;

    private int mIndex = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mDatabase = new DatabaseHandler(mContext);
        mWords = mDatabase.getAllWords();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_checked);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mListWord = new ArrayList<>();
        mAdapter = new WordAdapter(mListWord, mContext);
        mRecyclerView.setAdapter(mAdapter);

        mTextViewRandomWord = (TextView) view.findViewById(R.id.tvRandomWord);
        mButton = (Button) view.findViewById(R.id.btOK);
        mEditTextValue = (EditText) view.findViewById(R.id.etWord);

        onClick();

        setWord();
    }

    public void onClick(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mTextViewRandomWord.getText().toString();
                String value = mEditTextValue.getText().toString();
                boolean isCorrect = mDatabase.findWord(key, value);
                Word word = new Word();
                word.setKey(key);
                word.setValue(value);
                word.setCorrect(isCorrect);

                updateList(word);
                setWord();

                String result = "";
                if (isCorrect){
                    result = "Correct";
                } else {
                    result = "Incorrect";
                }
                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                Ultis.hideSoftKeyboard(getActivity());
            }
        });
    }

    public void setWord(){
        if (mIndex == THE_NUMBER_OF_WORDS){
            Toast.makeText(mContext, "Học ít thôi!", Toast.LENGTH_SHORT).show();
            return;
        }
        //co the trung, danh thoi gian suy nghi lai
        Random random = new Random();
        if (mWords.size() == 0){
            mTextViewRandomWord.setText("Welcome!");
            return;
        }
        int position = random.nextInt(mWords.size());
        Word word = mWords.get(position);
        mTextViewRandomWord.setText(word.getKey());
        mIndex++;
    }

    @Override
    public void onResume() {
        super.onResume();
        mIndex = 0;
    }

    public void updateList(Word word){
        mListWord.add(word);
        mAdapter.notifyDataSetChanged();
    }
}
