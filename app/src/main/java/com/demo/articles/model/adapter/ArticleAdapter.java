package com.demo.articles.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.demo.articles.R;
import com.demo.articles.model.pojo.Articles;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.Holder> {

    public static String TAG = ArticleAdapter.class.getSimpleName();
    public OnItemClickListener mListener;
    private List<Articles> mArticles;

    public ArticleAdapter(List<Articles> articles,OnItemClickListener listener) {
        mArticles = articles;
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Articles item);
    }

    public void addArticles(Articles articles) {
        mArticles.add(articles);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
//        row.setOnClickListener(mOnClickListener);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Articles currentArticles = mArticles.get(position);
        holder.bind(currentArticles, mListener);

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView mArticleName, mArticleBy, mArticleSource, mArticleDate;
        public ImageView mArticleImage;

        public Holder(View itemView) {
            super(itemView);
            mArticleImage = (ImageView) itemView.findViewById(R.id.imgArticle);
            mArticleName = (TextView) itemView.findViewById(R.id.txtArticleName);
            mArticleBy = (TextView) itemView.findViewById(R.id.txtArticleBy);
            mArticleSource = (TextView) itemView.findViewById(R.id.txtArticleSource);
            mArticleDate = (TextView) itemView.findViewById(R.id.txtArticleDate);
        }

        public void bind(final Articles item, final OnItemClickListener listener) {
            mArticleName.setText(item.mArticleName);
            mArticleBy.setText(item.mArticleBy);
            mArticleSource.setText(item.mArticleSource);
            mArticleDate.setText(item.mArticleDate);

            Picasso.with(itemView.getContext()).load(item.mArticlePhoto).into(mArticleImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
