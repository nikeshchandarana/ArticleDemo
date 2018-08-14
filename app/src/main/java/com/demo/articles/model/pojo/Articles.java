package com.demo.articles.model.pojo;

public class Articles {

    public String mArticleBy, mArticleDate, mArticlePhoto, mArticleName;
    public String mArticleSource, mArticleMainLink;
    public String mProductId;

    private Articles(Builder builder) {
        mArticleBy = builder.mArticleBy;
        mArticleDate = builder.mArticleDate;
        mArticlePhoto = builder.mArticlePhoto;
        mArticleName = builder.mArticleName;
        mArticleSource = builder.mArticleSource;
        mProductId = builder.mProductId;
        mArticleMainLink = builder.mArticleMainLink;
    }

    public static class Builder {

        private String mArticleBy, mArticleDate, mArticlePhoto, mArticleName;
        private String mArticleSource, mArticleMainLink;
        private String mProductId;

        public Builder setArticleMainLink(String link){
            mArticleMainLink = link;
            return  Builder.this;
        }

        public Builder setArticleBy(String category) {
            mArticleBy = category;
            return Builder.this;
        }

        public Builder setArticleDate(String instructions) {
            mArticleDate = instructions;
            return Builder.this;
        }

        public Builder setPhoto(String photo) {
            mArticlePhoto = photo;
            return Builder.this;
        }

        public Builder setName(String name) {
            mArticleName = name;
            return Builder.this;
        }

        public Builder setArticleSource(String price) {
            mArticleSource = price;
            return Builder.this;
        }

        public Builder setProductId(String productId) {
            mProductId = productId;
            return Builder.this;
        }

        public Articles build() {
            return new Articles(Builder.this);
        }
    }
}
