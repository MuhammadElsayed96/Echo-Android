package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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

  public NewsAdapter(Context context, List<Article> newsList) {
    mContext = context;
    mNewsList = newsList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.wtf(TAG,"onCreateViewHolder(): has been instantiated");
    switch (viewType) {
      case TYPE_FIRST_ITEM:
        final View itemViewLarge =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.large_news_item, parent, false);
        return new NewsAdapterViewHolder(itemViewLarge);
      case TYPE_ITEM:
        final View itemViewNormal =
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.normal_news_item, parent, false);
        return new NewsAdapterViewHolder(itemViewNormal);
      default:
        return null;
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Log.wtf(TAG,"onBindViewHolder(): has been instantiated");

    Article article = mNewsList.get(position);

    switch (holder.getItemViewType()) {

      case TYPE_FIRST_ITEM:
        LargeNewsAdapterViewHolder largeViewHolder = (LargeNewsAdapterViewHolder) holder;

        largeViewHolder.mCaptionTextL.setText(article.getDescription());
        largeViewHolder.mTitleTextL.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage().toString()).into(largeViewHolder.mNewsImageL);
        scaleImage(largeViewHolder.mNewsImageL);
        largeViewHolder.mSourceNameL.setText(article.getSource().getName());
        Picasso.get().load(R.drawable.abc_news).into(largeViewHolder.mSourceImageL);
        scaleImage(largeViewHolder.mSourceImageL);

        break;

      case TYPE_ITEM:
        NewsAdapterViewHolder normalViewHolder = (NewsAdapterViewHolder) holder;

        normalViewHolder.mCaptionText.setText(article.getDescription());
        normalViewHolder.mTitleText.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage().toString()).into(normalViewHolder.mNewsImage);
        scaleImage(normalViewHolder.mNewsImage);
        normalViewHolder.mSourceName.setText(article.getSource().getName());
        Picasso.get().load(R.drawable.abc_news).into(normalViewHolder.mSourceImage);
        scaleImage(normalViewHolder.mSourceImage);

        break;
    }
  }

  @Override
  public int getItemViewType(int position) {
    Log.wtf(TAG,"getItemViewType(): has been instantiated");
    if (position == 0) return TYPE_FIRST_ITEM;
    return TYPE_ITEM;
  }

  @Override
  public int getItemCount() {
    return mNewsList.size();
  }

  class NewsAdapterViewHolder extends RecyclerView.ViewHolder {

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

  class LargeNewsAdapterViewHolder extends RecyclerView.ViewHolder {

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

  private void scaleImage(ImageView view) throws NoSuchElementException {
    Log.wtf(TAG,"scaleImage(): has been instantiated");

    // Get bitmap from the the ImageView.
    Bitmap bitmap = null;

    try {
      Drawable drawing = view.getDrawable();
      bitmap = ((BitmapDrawable) drawing).getBitmap();
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No drawable on given view");
    } catch (ClassCastException e) {
      // Check bitmap is Ion drawable
      //      bitmap = Ion.with(view).getBitmap();
    }

    // Get current dimensions AND the desired bounding box
    int width = 0;

    try {
      width = bitmap.getWidth();
    } catch (NullPointerException e) {
      throw new NoSuchElementException("Can't find bitmap on given view/drawable");
    }

    int height = bitmap.getHeight();
    int bounding = dpToPx(250);
    Log.i("Test", "original width = " + Integer.toString(width));
    Log.i("Test", "original height = " + Integer.toString(height));
    Log.i("Test", "bounding = " + Integer.toString(bounding));

    // Determine how much to scale: the dimension requiring less scaling is
    // closer to the its side. This way the image always stays inside your
    // bounding box AND either x/y axis touches it.
    float xScale = ((float) bounding) / width;
    float yScale = ((float) bounding) / height;
    float scale = (xScale <= yScale) ? xScale : yScale;
    Log.i("Test", "xScale = " + Float.toString(xScale));
    Log.i("Test", "yScale = " + Float.toString(yScale));
    Log.i("Test", "scale = " + Float.toString(scale));

    // Create a matrix for the scaling and add the scaling data
    Matrix matrix = new Matrix();
    matrix.postScale(scale, scale);

    // Create a new bitmap and convert it to a format understood by the ImageView
    Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    width = scaledBitmap.getWidth(); // re-use
    height = scaledBitmap.getHeight(); // re-use
    BitmapDrawable result = new BitmapDrawable(scaledBitmap);
    Log.i("Test", "scaled width = " + Integer.toString(width));
    Log.i("Test", "scaled height = " + Integer.toString(height));

    // Apply the scaled bitmap
    view.setImageDrawable(result);

    // Now change ImageView's dimensions to match the scaled image
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
    params.width = width;
    params.height = height;
    view.setLayoutParams(params);

    Log.i("Test", "done");
  }

  private int dpToPx(int dp) {
    float density = mContext.getResources().getDisplayMetrics().density;
    return Math.round((float) dp * density);
  }
}
