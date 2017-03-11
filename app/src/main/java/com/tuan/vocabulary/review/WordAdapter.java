package com.tuan.vocabulary.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tuan.vocabulary.R;

import java.util.ArrayList;

/**
 * Created by tuanl on 20-Feb-17.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private ArrayList<Word> mList;
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_word, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Word data = mList.get(position);
        holder.mTextViewPosition.setText(""+(position + 1));
        holder.mTextViewKey.setText(data.getKey());
        holder.mTextViewValue.setText(data.getValue());
        if (data.isCorrect()){
            holder.mTextViewKey.setTextColor(mContext.getResources().getColor(R.color.correct));
            holder.mTextViewValue.setTextColor(mContext.getResources().getColor(R.color.correct));
        } else {
            holder.mTextViewKey.setTextColor(mContext.getResources().getColor(R.color.incorrect));
            holder.mTextViewValue.setTextColor(mContext.getResources().getColor(R.color.incorrect));
        }
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextViewKey;
        public TextView mTextViewValue;
        public TextView mTextViewPosition;
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mTextViewKey = (TextView) v.findViewById(R.id.tvKey);
            mTextViewValue = (TextView) v.findViewById(R.id.tvValue);
            mTextViewPosition = (TextView) v.findViewById(R.id.tvPosition);
            mView = v;
        }
    }

    public WordAdapter(ArrayList<Word> data, Context context){
        this.mList = data;
        this.mContext = context;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
