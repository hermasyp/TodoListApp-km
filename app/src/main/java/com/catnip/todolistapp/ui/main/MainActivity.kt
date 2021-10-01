package com.catnip.todolistapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.catnip.todolistapp.R
import com.catnip.todolistapp.databinding.ActivityMainBinding
import com.catnip.todolistapp.ui.taskform.TaskFormActivity
import com.catnip.todolistapp.ui.tasklist.TaskListFragment
import com.catnip.todolistapp.utils.views.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        //adding fragment to adapter
        viewPagerAdapter.addFragment(TaskListFragment.newInstance(false), "Undone Task")
        viewPagerAdapter.addFragment(TaskListFragment.newInstance(true), "Done Task")
        binding.vpTask.adapter = viewPagerAdapter
        //setting title for tab layout
        TabLayoutMediator(binding.tlTask, binding.vpTask, true) { tab, pos ->
            tab.text = viewPagerAdapter.getPageTitle(pos)
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.menu_add_task -> {
                startActivity(Intent(this,TaskFormActivity::class.java))
                true
            }
            R.id.menu_about -> {
                // TODO: 01/10/2021 open dialog about
                true
            }
            else -> {
                true
            }
        }
    }


}