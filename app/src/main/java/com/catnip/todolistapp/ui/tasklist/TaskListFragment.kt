package com.catnip.todolistapp.ui.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.catnip.todolistapp.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
    private var isTaskCompleted: Boolean = false

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