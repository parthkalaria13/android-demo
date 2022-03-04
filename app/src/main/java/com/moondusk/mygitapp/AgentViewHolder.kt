package com.moondusk.mvvmkotlin.adapters.agent

import androidx.recyclerview.widget.RecyclerView
import com.moondusk.mvvmkotlin.adapters._base.RecyclerItemClickListener
import com.moondusk.mvvmkotlin.databinding.ItemAgentBinding
import com.moondusk.mvvmkotlin.models.agent.Data

class AgentViewHolder(private val binding: ItemAgentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: RecyclerItemClickListener<Data>? = null

    fun bind(data: Data, pos: Int) {
        binding.agentData = data
        binding.executePendingBindings()
        binding.crdMain.setOnClickListener {
            itemClickListener?.onItemClicked(data, pos)
        }
    }
}