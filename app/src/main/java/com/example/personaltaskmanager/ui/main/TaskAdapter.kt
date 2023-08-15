package com.example.personaltaskmanager.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.personaltaskmanager.data.model.Task
import com.example.personaltaskmanager.databinding.TaskItemBinding

class TaskAdapter(var onItemClicked: (Int) -> Unit) :
    PagingDataAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallBack()) {




    inner class ViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task?) {
            binding.tvTitle.text=item?.title
        }

        init {
            binding.root.setOnClickListener {
                onItemClicked.invoke(getItem(absoluteAdapterPosition)?.id?:-1)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class TaskDiffCallBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}

