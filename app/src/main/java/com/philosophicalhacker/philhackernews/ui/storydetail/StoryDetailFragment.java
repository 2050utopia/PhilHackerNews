package com.philosophicalhacker.philhackernews.ui.storydetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.philosophicalhacker.philhackernews.R;
import com.philosophicalhacker.philhackernews.model.Item;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Shows a webview that points to the link within the HackerNews story.
 */
public class StoryDetailFragment extends Fragment {

    private static final String ARGS_STORY = "ARGS_STORY";

    public static StoryDetailFragment newInstance(Item item) {
        StoryDetailFragment storyDetailFragment = new StoryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_STORY, item);
        storyDetailFragment.setArguments(args);
        return storyDetailFragment;
    }


    @Bind(R.id.webView)
    WebView mWebView;

    @Bind(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);
        ButterKnife.bind(this, view);
        Item item = getArguments().getParcelable(ARGS_STORY);
        if (savedInstanceState == null) {
            getActivity().setTitle(item.getTitle());
            mWebView.setWebViewClient(new SwipeToRefreshUpdatingWebViewClient(mSwipeRefreshLayout));
            mSwipeRefreshLayout.setOnRefreshListener(new WebViewReloadingOnRefreshListener(mWebView));
            String url = item.getUrl();
            if (url != null) {
                mWebView.loadUrl(url);
            } else {
                mWebView.loadData(item.getText(), "text/html", "UTF-8");
            }
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_story_detail, menu);
    }

    //----------------------------------------------------------------------------------
    // Nested Inner Class
    //----------------------------------------------------------------------------------
    private static class SwipeToRefreshUpdatingWebViewClient extends WebViewClient {
        private SwipeRefreshLayout mSwipeRefreshLayout;

        public SwipeToRefreshUpdatingWebViewClient(SwipeRefreshLayout swipeRefreshLayout) {
            mSwipeRefreshLayout = swipeRefreshLayout;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mSwipeRefreshLayout.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    private static class WebViewReloadingOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        private WebView mWebView;

        public WebViewReloadingOnRefreshListener(WebView webView) {
            mWebView = webView;
        }

        @Override
        public void onRefresh() {
            mWebView.reload();
        }
    }
}