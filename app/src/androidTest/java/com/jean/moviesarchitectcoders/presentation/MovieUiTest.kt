package com.jean.moviesarchitectcoders.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.jean.moviesarchitectcoders.R
import com.jean.moviesarchitectcoders.utils.MockWebServerRule
import com.jean.moviesarchitectcoders.utils.OkHttp3IdlingResource
import com.jean.moviesarchitectcoders.utils.PermissionHandler
import com.jean.moviesarchitectcoders.utils.fromJson
import com.jean.moviesarchitectcoders.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MovieUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var permissionHandler: PermissionHandler

    @Before
    fun setUp() {
        mockWebServerRule.mockWebServer.enqueue(
            MockResponse().fromJson("now_playing_movies.json")
        )

        hiltRule.inject()

        val resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun verify_if_movie_list_is_visible() {
        launchFragmentInHiltContainer<MovieFragment>()

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verify_when_user_clicked_in_a_movie_then_show_movie_detail() {
        launchFragmentInHiltContainer<MovieFragment>()

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
            )

        onView(withId(R.id.tv_title_detail))
            .check(matches(hasDescendant(withText("Madame Web"))))
    }

}