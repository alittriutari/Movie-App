package com.example.ramapradana.mou.Reviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramapradana.mou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rama Pradana on 2/13/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    List<ResultsItem> dataset = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        char fname;
        ResultsItem resultsItem = dataset.get(position);
        holder.mFullName.setText(resultsItem.getAuthor());
        holder.mReview.setText(resultsItem.getContent());

        fname = resultsItem.getAuthor().charAt(0);
        holder.mFname.setText(fname+"");
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setData(List<ResultsItem> resultsItem){
        this.dataset = resultsItem;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mFullName, mReview, mFname;

        public ViewHolder(View itemView) {
            super(itemView);

            mFullName = itemView.findViewById(R.id.tv_full_name);
            mReview = itemView.findViewById(R.id.tv_review);
            mFname = itemView.findViewById(R.id.tv_first_name);
        }
    }
}
