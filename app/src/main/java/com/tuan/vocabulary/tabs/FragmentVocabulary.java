package com.tuan.vocabulary.tabs;

import android.app.Activity;
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
import android.widget.Toast;

import com.tuan.vocabulary.R;
import com.tuan.vocabulary.database.DatabaseHandler;
import com.tuan.vocabulary.review.Word;
import com.tuan.vocabulary.review.WordAdapter;
import com.tuan.vocabulary.ultis.Ultis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanl on 20-Feb-17.
 */

public class FragmentVocabulary extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private ArrayList<Word> mListWord;

    private EditText mEditTextKey;
    private EditText mEditTextValue;
    private Button mButtonOK;

    private DatabaseHandler mDatabase;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mDatabase = new DatabaseHandler(mContext);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_all);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mListWord = mDatabase.getAllWords();
        mAdapter = new WordAdapter(mListWord, mContext);
        mRecyclerView.setAdapter(mAdapter);


        mEditTextKey = (EditText) view.findViewById(R.id.etKey);
        mEditTextValue = (EditText) view.findViewById(R.id.etValue);
        mButtonOK = (Button) view.findViewById(R.id.btOK);

        onClick();
    }

    public void onClick(){
        mButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mEditTextKey.getText().toString();
                String value = mEditTextValue.getText().toString();
                if (key.equals("") && value.equals("")){
                    Toast.makeText(mContext, "Please input value", Toast.LENGTH_SHORT).show();
                    return;
                }
                Word word = new Word();
                word.setKey(key);
                word.setValue(value);
                mDatabase.addWord(word);
                mListWord.add(word);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(mContext, "Done!", Toast.LENGTH_SHORT).show();
                Ultis.hideSoftKeyboard(getActivity());
            }
        });
    }


}
