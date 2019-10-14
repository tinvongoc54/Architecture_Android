package com.example.myapplication.features.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.EndlessRecyclerViewScrollListener
import com.example.myapplication.features.userDetail.UserDetailFragment
import com.example.myapplication.utils.SafeObserver
import com.example.myapplication.utils.goTo
import com.example.myapplication.utils.gone
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModel()
    private val homeAdapter by lazy {
        HomeAdapter {
            activity?.goTo(UserDetailFragment.newInstance(), R.id.container)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        edtSearch.setText("ThuanPx")

        val layoutManager = LinearLayoutManager(context)
        rvResult.layoutManager = layoutManager
        rvResult.adapter = homeAdapter

        rvResult.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.searchUser(edtSearch.text.toString(), page)
            }
        })

        tvSearch.setOnClickListener {
            viewModel.searchUser(edtSearch.text.toString())
        }
        tvClear.setOnClickListener {
            homeAdapter.setData(viewModel.users.apply {
                removeAt(0)
            })
        }

        viewModel.usersLiveData.observe(viewLifecycleOwner, SafeObserver {
            homeAdapter.setData(it)
        })
        viewModel.onError.observe(viewLifecycleOwner, SafeObserver {
            Log.e("Error", it.localizedMessage)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, SafeObserver {
            loading.gone(!it)
        })
    }
}