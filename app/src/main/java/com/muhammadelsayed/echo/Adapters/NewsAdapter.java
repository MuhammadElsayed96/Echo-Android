package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.database.DatabaseHelper;
import com.muhammadelsayed.echo.model.Article;
import com.muhammadelsayed.echo.model.Tag;
import com.squareup.picasso.Picasso;
import com.thefinestartist.finestwebview.FinestWebView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.grantland.widget.AutofitTextView;

import static android.content.Intent.createChooser;
import static com.thefinestartist.utils.content.ContextUtil.getString;
import static com.thefinestartist.utils.content.ContextUtil.startActivity;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();
    private static final int TYPE_FIRST_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private static final boolean UN_BOOKMARED = false;
    private static final boolean BOOKMARKED = true;
    private boolean mBookmark = UN_BOOKMARED;
    private Context mContext;
    private List<Article> mNewsList;

    public NewsAdapter(Context context, List<Article> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.wtf(TAG, "onCreateViewHolder(): has been instantiated");
//        switch (viewType) {
//            case TYPE_FIRST_ITEM:
//                final View itemViewLarge =
//                        LayoutInflater.from(parent.getContext())
//                                .inflate(R.layout.large_news_item, parent, false);
//                final LargeNewsAdapterViewHolder mLargeViewHolder =
//                        new LargeNewsAdapterViewHolder(itemViewLarge);
//
//                itemViewLarge.setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                mListener.onItemClick(view, mLargeViewHolder.getPosition());
//                            }
//                        });
//                return mLargeViewHolder;

//            case TYPE_ITEM:
        final View itemViewNormal =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.normal_news_item, parent, false);
        final NewsAdapterViewHolder mNormalViewHolder = new NewsAdapterViewHolder(itemViewNormal);
        return mNormalViewHolder;
//
//            default:
//                return null;
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.wtf(TAG, "onBindViewHolder(): has been instantiated");

        final Article article = mNewsList.get(position);

//        switch (holder.getItemViewType()) {
//            case TYPE_FIRST_ITEM:
//                LargeNewsAdapterViewHolder largeViewHolder = (LargeNewsAdapterViewHolder) holder;
//                Typeface custom_font = ResourcesCompat.getFont(mContext, R.font.belgrano);
//                largeViewHolder.mTitleTextL.setTypeface(custom_font);
//                largeViewHolder.mTitleTextL.setText(article.getWebTitle());
//                Picasso.get()
//                        .load(article.getFields().getThumbnail())
//                        .fit()
//                        .into(largeViewHolder.mNewsImageL);
//                break;
//
//            case TYPE_ITEM:
        final NewsAdapterViewHolder normalViewHolder = (NewsAdapterViewHolder) holder;
        Typeface custom_font1 = ResourcesCompat.getFont(mContext, R.font.belgrano);
        normalViewHolder.mTitleText.setTypeface(custom_font1);

        final DatabaseHelper db = new DatabaseHelper(mContext);

        if (db.isArticleAlreadySaved(article.getId())) {
            article.setBookmarked(BOOKMARKED);
            normalViewHolder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_black_24dp);
        } else {
            article.setBookmarked(UN_BOOKMARED);
            normalViewHolder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }

        normalViewHolder.mBookmarkImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Change Image Resource
            if (article.isBookmarked()) {
                article.setBookmarked(UN_BOOKMARED);

                db.deleteSavedArticle(article);

                normalViewHolder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();

            } else {
                long id = db.saveArticle(article);

                Log.wtf(TAG, "onClick: SAVED = " + id);

                normalViewHolder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_black_24dp);
                Toast.makeText(mContext, "Bookmarked", Toast.LENGTH_SHORT).show();
            }

            }
        });
        normalViewHolder.mTitleText.setText(article.getWebTitle());
        normalViewHolder.mShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Share URL to...";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, article.getWebUrl());
                startActivity(createChooser(sharingIntent, shareBody));
            }
        });
        if (article.getFields().getThumbnail() != null)
            Picasso.get()
                    .load(article.getFields().getThumbnail())
                    .fit()
                    .into(normalViewHolder.mNewsImage);
        else
            normalViewHolder.mNewsImage.setVisibility(View.GONE);
        if (article.getTags().length <= 0) {
            Tag tag = new Tag();
            tag.setWebTitle("Anonymous");
            article.setTags(new Tag[]{tag});
        }
        normalViewHolder.mAuthorName.setText(article.getTags()[0].getWebTitle());
        try {
            normalViewHolder.mTime.setText(formatTime(article.getWebPublicationDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        normalViewHolder.mHeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String articleUrl = article.getWebUrl();
                new FinestWebView.Builder(mContext)
                        .theme(R.style.FinestWebViewTheme)
                        .titleDefault(getString(R.string.the_guardian))
                        .showUrl(false)
                        .statusBarColorRes(R.color.bluePrimaryDark)
                        .toolbarColorRes(R.color.bluePrimary)
                        .titleColorRes(R.color.finestWhite)
                        .urlColorRes(R.color.bluePrimaryLight)
                        .iconDefaultColorRes(R.color.finestWhite)
                        .progressBarColorRes(R.color.finestWhite)
                        .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                        .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                        .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                        .showSwipeRefreshLayout(true)
                        .swipeRefreshColorRes(R.color.bluePrimaryDark)
                        .menuSelector(R.drawable.selector_light_theme)
                        .menuTextGravity(Gravity.CENTER)
                        .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                        .dividerHeight(0)
                        .gradientDivider(false)
                        .setCustomAnimations(
                                R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                        .show(articleUrl);

                long id = db.addArticleToHistory(article);
                Log.d(TAG, "onClick: HISTORY = " + id);
            }
        });
//                break;
//        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        Log.wtf(TAG, "getItemViewType(): has been instantiated");
//        if (position == 0) return TYPE_FIRST_ITEM;
//        return TYPE_ITEM;
//    }


    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }

    private class NewsAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView mNewsImage, mBookmarkImage, mShareImage;
        private AutofitTextView mTitleText;
        private TextView mAuthorName, mTime;
        private ConstraintLayout mHeadline;

        private NewsAdapterViewHolder(View view) {
            super(view);
            mNewsImage = view.findViewById(R.id.news_image);
            mTime = view.findViewById(R.id.time);
            mBookmarkImage = view.findViewById(R.id.bookmark_news);
            mShareImage = view.findViewById(R.id.share_news);
            mTitleText = view.findViewById(R.id.title_text);
            mAuthorName = view.findViewById(R.id.author_name);
            mHeadline = view.findViewById(R.id.headline_view);
        }
    }

    private class LargeNewsAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView mNewsImageL, mBookmarkImageL, mShareImageL;
        private AutofitTextView mTitleTextL;
        private TextView mSourceNameL, mTimeL;

        private LargeNewsAdapterViewHolder(View view) {
            super(view);
            mNewsImageL = view.findViewById(R.id.large_news_image);
            mTimeL = view.findViewById(R.id.large_time);
            mBookmarkImageL = view.findViewById(R.id.large_bookmark_news);
            mShareImageL = view.findViewById(R.id.large_share_news);
            mTitleTextL = view.findViewById(R.id.large_title_text);
            mSourceNameL = view.findViewById(R.id.large_news_source_name);
        }
    }

    private static String formatTime(String tripTime) throws ParseException {
        DateFormat formatter
                = new SimpleDateFormat("MM/dd/yy 'at' h:mm a");
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date date = originalFormat.parse(tripTime);
        return formatter.format(date);
    }
}
