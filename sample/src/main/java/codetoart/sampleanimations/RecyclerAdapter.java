package codetoart.sampleanimations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mobisys on 1/31/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] mTitleArray;
    private OnRecyclerItemClick mOnRecyclerItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;

        public ViewHolder(TextView v) {
            super(v);
            mTitle = v;
            mTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnRecyclerItemClickListener != null) {
                int itemPosition = getAdapterPosition();
                mOnRecyclerItemClickListener.onItemClick(view, itemPosition);
            }
        }
    }

    public RecyclerAdapter(String[] apiArray, OnRecyclerItemClick onRecyclerItemClick) {
        mTitleArray = apiArray;
        mOnRecyclerItemClickListener = onRecyclerItemClick;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_text, parent, false);
        return new ViewHolder((TextView) v.findViewById(R.id.text));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mTitleArray[position]);
    }

    @Override
    public int getItemCount() {
        return mTitleArray.length;
    }

    public interface OnRecyclerItemClick {
        void onItemClick(View view, int position);
    }
}