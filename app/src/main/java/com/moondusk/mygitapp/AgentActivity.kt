package com.moondusk.mvvmkotlin.ui.agent

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moondusk.mvvmkotlin.adapters._base.RecyclerItemClickListener
import com.moondusk.mvvmkotlin.adapters.agent.AgentAdapter
import com.moondusk.mvvmkotlin.data.DataResource
import com.moondusk.mvvmkotlin.databinding.ActivityAgentBinding
import com.moondusk.mvvmkotlin.error.ErrorCode.Companion.NO_INTERNET_CONNECTION
import com.moondusk.mvvmkotlin.error.ErrorManager
import com.moondusk.mvvmkotlin.models.agent.Data
import com.moondusk.mvvmkotlin.ui._base.BaseActivity
import com.moondusk.mvvmkotlin.utilities.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class AgentActivity : BaseActivity<ActivityAgentBinding>() {

    private val _viewModel by viewModels<AgentViewModel>()
    private lateinit var _agentAdapter: AgentAdapter

    @Inject
    lateinit var errorManager: ErrorManager

    override fun initViewBinding(inflater: LayoutInflater) = ActivityAgentBinding.inflate(inflater)

    override fun initViews() {
        _binding.lifecycleOwner = this
        setSupportActionBar(_binding.tbAgent)
        _agentAdapter = AgentAdapter()
        _binding.rvAgent.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@AgentActivity)
            adapter = _agentAdapter
        }
    }

    override fun observersAndEvents() {
        _binding.tbAgent.setNavigationOnClickListener {
            onBackPressed()
        }
        lifecycleScope.launchWhenStarted {
            _viewModel.agentData.collectLatest {
                when (it) {
                    is DataResource.Success -> {
                        _binding.setLoading = false
                        _binding.setNoData = false
                        _agentAdapter.updateDataList(it.data.data)
                    }
                    is DataResource.Failure -> {
                        _binding.setLoading = false
                        _binding.setNoData = true
                    }
                    is DataResource.Loading -> {
                        _binding.setLoading = true
                        _binding.setNoData = false
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            _viewModel.error.collectLatest {
                errorManager.handleError(_binding.root, it.errorCode, it.errorMessage) {
                    if (it.errorCode == NO_INTERNET_CONNECTION) {
                        _viewModel.getAgents()
                    }
                }
            }
        }
        _agentAdapter.itemClickListener = object : RecyclerItemClickListener<Data> {
            override fun onItemClicked(item: Data, pos: Int) {
                _binding.root.snackbar("ItemClicked " + item.name)
            }
        }
        _binding.rvAgent.onScrollFabShowHideTop(_binding.fabScrollHide)
        _binding.rvAgent.onScrollFabShowHide(_binding.fabScrollHide2)
        _binding.rvAgent.onScrollFabExtendShrinkTop(_binding.efabScroll)
        _binding.rvAgent.onScrollFabExtendShrink(_binding.efabScroll2)
    }
}