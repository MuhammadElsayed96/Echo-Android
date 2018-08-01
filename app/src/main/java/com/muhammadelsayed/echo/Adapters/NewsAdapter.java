package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.NoSuchElementException;

import me.grantland.widget.AutofitTextView;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private static final String TAG = NewsAdapter.class.getSimpleName();
  private static final int TYPE_FIRST_ITEM = 0;
  private static final int TYPE_ITEM = 1;
  private Context mContext;
  private List<Article> mNewsList;
  private ItemClickListener mListener;

  public NewsAdapter(Context context, List<Article> newsList, ItemClickListener listener) {
    mContext = context;
    mNewsList = newsList;
    mListener = listener;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.wtf(TAG, "onCreateViewHolder(): has been instantiated");
    switch (viewType) {
      case TYPE_FIRST_ITEM:
        final View itemViewLarge =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.large_news_item, parent, false);
        final LargeNewsAdapterViewHolder mLargeViewHolder =
            new LargeNewsAdapterViewHolder(itemViewLarge);

        itemViewLarge.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                mListener.onItemClick(view, mLargeViewHolder.getPosition());
              }
            });
        return mLargeViewHolder;

      case TYPE_ITEM:
        final View itemViewNormal =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.normal_news_item, parent, false);

        final NewsAdapterViewHolder mNormalViewHolder = new NewsAdapterViewHolder(itemViewNormal);

        itemViewNormal.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                mListener.onItemClick(itemViewNormal, mNormalViewHolder.getPosition());
              }
            });
        return mNormalViewHolder;

      default:
        return null;
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Log.wtf(TAG, "onBindViewHolder(): has been instantiated");

    Article article = mNewsList.get(position);

    switch (holder.getItemViewType()) {
      case TYPE_FIRST_ITEM:
//        LargeNewsAdapterViewHolder largeViewHolder = (LargeNewsAdapterViewHolder) holder;
//        largeViewHolder.mCaptionTextL.setText(article.getDescription());
//        Typeface custom_font = ResourcesCompat.getFont(mContext, R.font.belgrano);
//        largeViewHolder.mTitleTextL.setTypeface(custom_font);
//        largeViewHolder.mTitleTextL.setText(article.getTitle());
//        if (article.getUrlToImage() != null)
//          Picasso.get()
//              .load(article.getUrlToImage().toString())
//              .fit()
//              .into(largeViewHolder.mNewsImageL);
//        else largeViewHolder.mNewsImageL.setVisibility(View.GONE);
        break;

      case TYPE_ITEM:
//        NewsAdapterViewHolder normalViewHolder = (NewsAdapterViewHolder) holder;
//        normalViewHolder.mCaptionText.setText(article.getDescription());
//        Typeface custom_font1 = ResourcesCompat.getFont(mContext, R.font.belgrano);
//        normalViewHolder.mTitleText.setTypeface(custom_font1);
//        normalViewHolder.mTitleText.setText(article.getTitle());
//        if (article.getUrlToImage() != null)
//          Picasso.get()
//              .load(article.getUrlToImage().toString())
//              .fit()
//              .into(normalViewHolder.mNewsImage);
//        else normalViewHolder.mNewsImage.setVisibility(View.GONE);
        break;
    }
  }

  @Override
  public int getItemViewType(int position) {
    Log.wtf(TAG, "getItemViewType(): has been instantiated");
    if (position == 0) return TYPE_FIRST_ITEM;
    return TYPE_ITEM;
  }

  @Override
  public int getItemCount() {
    return mNewsList.size();
  }

  private class NewsAdapterViewHolder extends RecyclerView.ViewHolder {

    private ImageView mNewsImage, mSourceImage, mBookmarkImage, mShareImage;
    private AutofitTextView mTitleText;
    private TextView mCaptionText, mSourceName;

    private NewsAdapterViewHolder(View view) {
      super(view);
      mNewsImage = view.findViewById(R.id.news_image);
      mSourceImage = view.findViewById(R.id.news_source_image);
      mBookmarkImage = view.findViewById(R.id.bookmark_news);
      mShareImage = view.findViewById(R.id.share_news);
      mTitleText = view.findViewById(R.id.title_text);
      mCaptionText = view.findViewById(R.id.caption_text);
      mSourceName = view.findViewById(R.id.news_source_name);
    }
  }

  private class LargeNewsAdapterViewHolder extends RecyclerView.ViewHolder {

    private ImageView mNewsImageL, mSourceImageL, mBookmarkImageL, mShareImageL;
    private AutofitTextView mTitleTextL;
    private TextView mCaptionTextL, mSourceNameL;

    private LargeNewsAdapterViewHolder(View view) {
      super(view);
      mNewsImageL = view.findViewById(R.id.large_news_image);
      mSourceImageL = view.findViewById(R.id.large_news_source_image);
      mBookmarkImageL = view.findViewById(R.id.large_bookmark_news);
      mShareImageL = view.findViewById(R.id.large_share_news);
      mTitleTextL = view.findViewById(R.id.large_title_text);
      mCaptionTextL = view.findViewById(R.id.large_caption_text);
      mSourceNameL = view.findViewById(R.id.large_news_source_name);
    }
  }

  public interface ItemClickListener {
    void onItemClick(View v, int position);
  }
}
