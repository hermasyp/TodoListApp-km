package com.catnip.todolistapp.ui.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.todolistapp.App
import com.catnip.todolistapp.data.model.Task
import com.catnip.todolistapp.databinding.FragmentTaskListBinding
import com.catnip.todolistapp.ui.taskdetail.TaskDetailActivity
import com.catnip.todolistapp.ui.tasklist.adapter.TaskAdapter
import com.google.android.material.snackbar.Snackbar

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
    private var isTaskCompleted: Boolean = false
    private lateinit var adapter: TaskAdapter

    private fun setupList() {
        adapter = TaskAdapter(
            { item, pos ->
                //onClickListener
                TaskDetailActivity.startActivity(context, item)
            },
            { item, pos ->
                //onLongClickListener
                showDialogDeleteTask(item)
            }
        )
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TaskListFragment.adapter
        }
        getListData()
    }

    private fun showDialogDeleteTask(item: Task) {
        context?.let {
            AlertDialog.Builder(it)
                .apply {
                    setTitle("Delete Task ?")
                    setMessage("Do you want to delete task \"${item.title}\"")
                    setPositiveButton("Yes") { dialog, id ->
                        deleteTask(item)
                    }
                    setNegativeButton("No") { dialog, id ->

                    }
                    create()
                    show()
                }
        }
    }

    private fun deleteTask(item: Task) {
        (activity?.application as App).getDataSource()?.delete(item.id)
        Toast.makeText(context, "Task Successfully Deleted", Toast.LENGTH_SHORT).show()
        getListData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isTaskCompleted = it.getBoolean(ARG_IS_TASK_COMPLETED)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeRefresh()
        setupList()

    }

    private fun setupSwipeRefresh() {
        binding.srlTask.setOnRefreshListener {
            binding.srlTask.isRefreshing = false
            getListData()
        }
    }


    private fun getListData() {
        (activity?.application as App).getDataSource()?.getData(isTaskCompleted)?.let {
            adapter.items = it
        }
    }


    companion object {
        private const val ARG_IS_TASK_COMPLETED = "ARG_IS_TASK_COMPLETED"

        @JvmStatic
        fun newInstance(isTaskCompleted: Boolean) =
            TaskListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_TASK_COMPLETED, isTaskCompleted)
                }
            }
    }
}