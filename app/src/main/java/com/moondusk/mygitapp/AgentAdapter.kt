package com.moondusk.mvvmkotlin.adapters.agent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moondusk.mvvmkotlin.adapters._base.RecyclerItemClickListener
import com.moondusk.mvvmkotlin.databinding.ItemAgentBinding
import com.moondusk.mvvmkotlin.models.agent.Data

class AgentAdapter : RecyclerView.Adapter<AgentViewHolder>() {

    var itemClickListener: RecyclerItemClickListener<Data>? = null

    private var _items = listOf<Data>()

    fun updateDataList(newList: List<Data>) {
        _items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val itemBinding =
            ItemAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        holder.bind(_items[position], position)
    }

    override fun getItemCount(): Int {
        return _items.size
    }
}