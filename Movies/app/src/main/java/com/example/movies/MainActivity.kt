package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movies.databinding.ActivityMainBinding
import com.example.stories.bookmarks.BookmarkFragment
import com.example.stories.homemovies.HomeMoviesFragment
import com.example.stories.searchmovies.SearchMoviesFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //viewbindind
    private lateinit var binding: ActivityMainBinding

    //declare the different fragments
    private lateinit var homeMoviesFragment: HomeMoviesFragment
    private lateinit var searchMoviesFragment: SearchMoviesFragment
    private lateinit var bookmarkFragment: BookmarkFragment


    //create a array that will hold the fragments
    private val fragment: Array<Fragment>
        get() =
            arrayOf(
                homeMoviesFragment,
                searchMoviesFragment,
                bookmarkFragment
            )

    private var indexSelected = 0

    private val selectedFragment get() = fragment[indexSelected]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //assign binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

//        check if there has been a configuration change.
//        if it is null, there has been no config change

        if (savedInstanceState == null) {

//        initialize the lateinit fragments
            homeMoviesFragment = HomeMoviesFragment()
            searchMoviesFragment = SearchMoviesFragment()
            bookmarkFragment = BookmarkFragment()


            //before we can attach and detach, we have to add the fragments to container.
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, homeMoviesFragment, TAG_HOME_MOVIES_FRAGMENT)
                .add(R.id.fragment_container, searchMoviesFragment, TAG_SEARCH_MOVIES_FRAGMENT)
                .add(R.id.fragment_container, bookmarkFragment, TAG_BOOKMARK_FRAGMENT)
                .commit()
        }
        else{
//            in case there was a configuration change...
//            fragments are still in memory but the fragment manager is responsible for finding them. restores them
            homeMoviesFragment =
                supportFragmentManager.findFragmentByTag(TAG_HOME_MOVIES_FRAGMENT) as HomeMoviesFragment
            searchMoviesFragment =
                supportFragmentManager.findFragmentByTag(TAG_SEARCH_MOVIES_FRAGMENT) as SearchMoviesFragment
            bookmarkFragment =
                supportFragmentManager.findFragmentByTag(TAG_BOOKMARK_FRAGMENT) as BookmarkFragment

            //retrieve the selected index which was saved when there is a config change. set default to first fragment,0
            indexSelected = savedInstanceState.getInt(SELECTED_INDEX_VALUE,0)
        }

        //this will select first view because the index is set to 0
        selectedFragment(selectedFragment)





        //NAVIGATION ITEM VIEW
        binding.buttonNav.setOnNavigationItemSelectedListener {itemSelected ->
            var fragment = when (itemSelected.itemId){
                R.id.nav_movies -> homeMoviesFragment
                R.id.nav_search -> searchMoviesFragment
                R.id.nav_bookmarks -> bookmarkFragment
                else -> throw IllegalArgumentException("error")
            }
            selectedFragment(fragment)
            true
        }




    }


    //function responsbile for inserting selected fragment on screen
    private fun selectedFragment(selectedFragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()

        //go through the array and see if they match with selectedFragment.
//    if they match, put it on screen. if not, detach fragment from screen
        fragment.forEachIndexed { index, fragment ->
            if (selectedFragment == fragment) {
                transaction = transaction.attach(fragment)
                indexSelected = index
            } else {
                transaction = transaction.detach(fragment)
            }
        }
        transaction.commit()

        //title of the page change...
        title = when(selectedFragment){

            is HomeMoviesFragment -> getString(R.string.home_screen_title)
            is SearchMoviesFragment -> getString(R.string.search_title)
            is BookmarkFragment -> getString(R.string.bookmake_title)
            else -> ""

        }

    }

    override fun onBackPressed() {
//        if you are not on the home page, get you back on home
        if(indexSelected!=0){
            binding.buttonNav.selectedItemId = R.id.nav_movies
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        save index when there is a configuration change
        outState.putInt(SELECTED_INDEX_VALUE,indexSelected)
    }
}


private const val TAG_HOME_MOVIES_FRAGMENT = "HOME_MOVIES_FRAGMENT"
private const val TAG_SEARCH_MOVIES_FRAGMENT = "TAG_SEARCH_MOVIES_FRAGMENT"
private const val TAG_BOOKMARK_FRAGMENT = "TAG_SEARCH_MOVIES_FRAGMENT"
private const val SELECTED_INDEX_VALUE = "SELECTED_INDEX_VALUE"

