package com.philosophicalhacker.philhackernews;

import android.support.annotation.NonNull;

import com.philosophicalhacker.philhackernews.data.remote.HackerNewsRestAdapter;
import com.philosophicalhacker.philhackernews.data.remote.RemoteDataFetcher;
import com.philosophicalhacker.philhackernews.model.Item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 *
 * Created by MattDupree on 7/18/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class RemoteDataFetcherTests {

    @Mock
    HackerNewsRestAdapter mHackerNewsRestAdapter;

    @Test
    public void limitsNumberOfStoriesReturnedToTwenty() {
        when(mHackerNewsRestAdapter.getTopStories()).thenReturn(makeRandomList());
        RemoteDataFetcher remoteDataFetcher = new RemoteDataFetcher(mHackerNewsRestAdapter);
        List<Item> topStories = remoteDataFetcher.getTopStories(20);
        assertEquals(20, topStories.size());
    }

    @NonNull
    private List<Integer> makeRandomList() {
        ArrayList<Integer> integers = new ArrayList<>(200);
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            integers.add(random.nextInt());
        }
        return integers;
    }

}
