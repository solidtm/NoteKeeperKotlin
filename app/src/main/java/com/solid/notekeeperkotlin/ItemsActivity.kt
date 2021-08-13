package com.solid.notekeeperkotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.solid.notekeeperkotlin.adapter.CourseRecyclerAdapter
import com.solid.notekeeperkotlin.adapter.NoteRecyclerAdapter
import com.solid.notekeeperkotlin.data.DataManager
import com.solid.notekeeperkotlin.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityItemsBinding

    private val noteLayoutManager by lazy{
        LinearLayoutManager(this)
    }

    private val noteRecyclerAdapter by lazy {
        NoteRecyclerAdapter(this, DataManager.notes)
    }

    private val courseLayoutManager by lazy{
        GridLayoutManager(this, 2)
    }

    private val courseRecyclerAdapter by lazy {
        CourseRecyclerAdapter(this, DataManager.courses.values.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsBinding.inflate(layoutInflater)
        val listItems = binding.appBarContainer.appBarItemsContainer.listItems
        val toolbar = binding.appBarContainer.toolbar

        setContentView(R.layout.activity_items)
        setSupportActionBar(toolbar)

        binding.appBarContainer.fab.setOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
        }

        displayNotes(listItems)

        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun displayNotes(listItems: RecyclerView) {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter

        binding.navView.menu.findItem(R.id.nav_notes).isChecked = true
    }

    private fun displayCourses(listItems: RecyclerView) {
        listItems.layoutManager = courseLayoutManager
        listItems.adapter = courseRecyclerAdapter

        binding.navView.menu.findItem(R.id.nav_notes).isChecked = true
    }

    override fun onResume() {
        super.onResume()
        binding.appBarContainer.appBarItemsContainer.listItems.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_notes -> {
                // Handle the notes selection action
                displayNotes(binding.appBarContainer.appBarItemsContainer.listItems)
            }
            R.id.nav_courses -> {
                displayCourses(binding.appBarContainer.appBarItemsContainer.listItems)
            }

            R.id.nav_share -> {
                handleSelection("Share")
            }
            R.id.nav_send -> {
                handleSelection("Send")
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(binding.appBarContainer.appBarItemsContainer.listItems, message,
                        Snackbar.LENGTH_LONG).show()
    }
}
