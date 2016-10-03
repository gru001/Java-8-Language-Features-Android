package com.example.android.java8feature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.java8feature.model.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 29/9/16
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<ToDo> mToDos;
    private ItemClickListener itemClickListener;

    public ToDoAdapter(ItemClickListener itemClickListener) {
        mToDos = new ArrayList<>();
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ToDoViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        final ToDo model = mToDos.get(position);
        holder.lblTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return mToDos.size();
    }

    public void add(int position, ToDo item) {
        mToDos.add(position, item);
        notifyItemInserted(position);
    }

    public void addItems(List<ToDo> item) {
        mToDos.clear();
        mToDos.addAll(item);
        notifyDataSetChanged();
    }

    public void remove(ToDo item) {
        int position = mToDos.indexOf(item);
        mToDos.remove(position);
        notifyItemRemoved(position);
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView lblTitle;

        ToDoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            lblTitle = (TextView) itemView.findViewById(android.R.id.text1);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(int pos);
    }
}
