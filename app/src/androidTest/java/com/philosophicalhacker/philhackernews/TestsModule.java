package com.philosophicalhacker.philhackernews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;

import com.google.gson.Gson;
import com.philosophicalhacker.philhackernews.data.cache.HackerNewsDatabaseOpenHelper;
import com.philosophicalhacker.philhackernews.data.remote.HackerNewsRestAdapter;
import com.philosophicalhacker.philhackernews.model.Story;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import dagger.Module;
import dagger.Provides;
import retrofit.http.Path;

/**
 * Overrides the HackerNewsRestAdapter with an adapter that loads a JSON string from memory.
 *
 * Created by MattDupree on 7/16/15.
 */
@Module(overrides = true,
        library = true,
        complete = false,
        injects = {MainActivityTests.class,
                CachingTests.class}
        )
public class TestsModule {
    @Provides
    HackerNewsRestAdapter provideHackerNewsRestAdapter() {
        final Gson gson = new Gson();
        return new HackerNewsRestAdapter() {
            @Override
            public List<Integer> getTopStories() {
                Type listType = new TypeToken<List<Integer>>() {
                }.getType();
                return gson.fromJson("[9897329,9899313,9899549,9897306,9899369,9899348,9898075,9897513,9898504,9897751,9897159,9896815,9896760,9896402,9898796,9898310,9897860,9896590,9896369,9896853,9897838,9897658,9898745,9896436,9898201,9896689,9898981,9898275,9895694,9896558,9896938,9897054,9895609,9898249,9895108,9898839,9895094,9899726,9899636,9899237,9896910,9899317,9895713,9895790,9897220,9895767,9894237,9899386,9897929,9897585,9895834,9899548,9895931,9899815,9898924,9893412,9895681,9897796,9895888,9894570,9895558,9898430,9899626,9894508,9894226,9896538,9898560,9899707,9894336,9899766,9896956,9899503,9899676,9898363,9896141,9896758,9893561,9895438,9899603,9895903,9891705,9897681,9893730,9897565,9899141,9899366,9899359,9898935,9898926,9899309,9898954,9894993,9899203,9895887,9894841,9898575,9898999,9899016,9897849,9896873,9892887,9892810,9890824,9896903,9898064,9897635,9897937,9896434,9897732,9897930,9898548,9896139,9891998,9897156,9883246,9896709,9898502,9889057,9898426,9898412,9894152,9882587,9895917,9896943,9897024,9897966,9898186,9891366,9894014,9898176,9897555,9892325,9894588,9897044,9896491,9895278,9896326,9897891,9893989,9894342,9897875,9896748,9896936,9892049,9896138,9894748,9896972,9879632,9889777,9897817,9893867,9893359,9896440,9894198,9893972,9897062,9892200,9895181,9895698,9896808,9892970,9895513,9892013,9897030,9892633,9891311,9894213,9896050,9892515,9896822,9889598,9892333,9880694,9884074,9884616,9896796,9890980,9893903,9895759,9895861,9890850,9890188,9896514,9896762,9896921,9895507,9892340,9878160,9895311,9896495,9896839,9891695,9891927,9890476,9894647,9896858,9896854,9895590,9895116,9895730,9891509,9896119,9895778,9894457,9895602,9891856,9887548,9894301,9891115,9891133,9896131,9895894,9895053,9893385,9892157,9889152,9892449,9891531,9894051,9896393,9894177,9891709,9891537,9895591,9889019,9892463,9894994,9895578,9895046,9889365,9895854,9892159,9889979,9892099,9891068,9895204,9896067,9889609,9895922,9891487,9890952,9870408,9890574,9891921,9894255,9889039,9891492,9894320,9892251,9892552,9892724,9889548,9895071,9891220,9884915,9891874,9887802,9887040,9895791,9895075,9888442,9891868,9892438,9895473,9889213,9886555,9894991,9886817,9891837,9890180,9891431,9894288,9893499,9890974,9893131,9889588,9893098,9893401,9894513,9886103,9890364,9895298,9895623,9891759,9888012,9885353,9894423,9892488,9893349,9888231,9865835,9885503,9895253,9870349,9895838,9895572,9891901,9890566,9891406,9891681,9894171,9894430,9891624,9885896,9886101,9879153,9883882,9892221,9888743,9879336,9881453,9892574,9893490,9895119,9891015,9891352,9895319,9883313,9895420,9894787,9895376,9888387,9895374,9893432,9891989,9895235,9894242,9879685,9869871,9893437,9893884,9894532,9889399,9888528,9894922,9881213,9893741,9892989,9878061,9879580,9887358,9893602,9893778,9891373,9886640,9891560,9892135,9894942,9893389,9893773,9893640,9878302,9875549,9894948,9884005,9894235,9892436,9874521,9894524,9894341,9892701,9891258,9884417,9894552,9888035,9890808,9876561,9889352,9891064,9891262,9840647,9892891,9889582,9893965,9893203,9893732,9890673,9893894,9888153,9879715,9869755,9888945,9893354,9894111,9893927,9889328,9894024,9894015,9893507,9889909,9892079,9891992,9893722,9891280,9892491,9885625,9883747,9891175,9889149,9887664,9893206,9886067,9848124,9840682,9876940,9830773,9881929,9894287,9889964,9891625,9892051,9885413,9884057,9891088,9884244,9891600,9892288,9891217,9891680,9885992,9884715,9883893,9882277,9888553,9880585,9886682,9876554,9891670,9887469,9887285,9876016,9890147,9888162,9881696,9887164,9890603,9889370,9812245,9890176,9872504,9857662,9854076,9890123,9888899,9851685,9848645,9882617,9794548,9720813,9803790,9797918,9826386,9835195,9851757,9805220,9854123,9889854,9876999,9808480,9770362,9856637,9826131,9835375,9836942,9843500,9893496,9791272,9857332,9800809,9884165,9894153,9893498,9831680,9422033,9851953,9817160,9810641,9481211,9805150,9420135,7539390,9895010,9840468,9456094,5624727,1627246,9892364,9891323,9891869,9891558,9889647,9893525,9889785,9892652,9891495,9892629,9890953,9882135,9884970,9891832]", listType);
            }

            @Override
            public Story getStory(@Path("id") int id) {
                Story story;
                if (id == 9897329) {
                    //Guarantee that this story will be at the top of the list.
                    story = new Story(id, 10000, MainActivityTests.DUMMY_STORY_TITLE, MainActivityTests.DUMMY_STORY_AUTHOR, MainActivityTests.DUMMY_URL);
                } else {
                        Random random = new Random();
                        int score = random.nextInt(999);
                        story = new Story(id, score, "Dummy Story", "Who Cares?", "http://www.google.com");
                }
                return story;
            }
        };
    }
    @Provides
    HackerNewsDatabaseOpenHelper provideHackerNewsDatabaseOpenHelper(Context context) {
        return new HackerNewsDatabaseOpenHelper(context, "test_hackernewsdata.db", null, 1);
    }

    @Provides
    File provideHackerNewsDatabaseFile(SQLiteDatabase sqLiteDatabase) {
        return new File(sqLiteDatabase.getPath());
    }
}
