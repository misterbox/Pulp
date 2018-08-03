package com.theskyegriffin.pulp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private String[] categories;

    public CategoryListAdapter(String[] categories) {
        this.categories = categories;
    }

    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String category = categories[position];
        viewHolder.categoryTextView.setText(category);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View categoryView;
        public TextView categoryTextView;

        public ViewHolder(View view) {
            super(view);
            categoryTextView = (TextView) view.findViewById(R.id.tv_category);

            categoryView = view;
        }
    }
}
