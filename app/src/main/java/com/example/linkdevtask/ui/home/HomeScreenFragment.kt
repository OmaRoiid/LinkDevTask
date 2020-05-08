package com.example.linkdevtask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linkdevtask.R
import com.example.linkdevtask.adapter.HomeScreenAdapter
import com.example.linkdevtask.listeners.OnClickedArticleListener
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.repository.HomeScreenRepository
import com.example.linkdevtask.ui.DetailScreenFragment
import androidx.lifecycle.ViewModelProvider


class HomeScreenFragment : Fragment(), OnClickedArticleListener {
    lateinit var mHomeScreenViewModel: HomeScreenViewModel
    lateinit var mHomeScreenViewModelFactory: HomeScreenViewModelFactory
    lateinit var mHomeScreenAdapter: HomeScreenAdapter
    private var mHomeScreenView: View?=null
    private val mHomeScreenRepository = HomeScreenRepository()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         mHomeScreenView= inflater.inflate(R.layout.fragment_home, container, false)
        val articlesList = mHomeScreenView?.findViewById<RecyclerView>(R.id.articles_list)
        articlesList?.layoutManager = LinearLayoutManager(activity)
        articlesList?.setHasFixedSize(true)
        mHomeScreenViewModelFactory = HomeScreenViewModelFactory(activity!!.application, mHomeScreenRepository)
        mHomeScreenViewModel = ViewModelProvider(this, mHomeScreenViewModelFactory).get(HomeScreenViewModel::class.java)
        mHomeScreenViewModel.getArticlesFromServer()
        mHomeScreenViewModel.articlesLiveData.observe(viewLifecycleOwner, Observer { observedList ->
            observedList.let {
                       mHomeScreenAdapter = HomeScreenAdapter(requireContext(), this, observedList)
                       mHomeScreenAdapter.notifyDataSetChanged()
                       articlesList?.adapter = mHomeScreenAdapter
            }
        })
        return mHomeScreenView
    }

    override fun onArticleClicked(position: Int) {
        val onArticlePositionClicked: Articles = mHomeScreenAdapter.articlesList[position]
        val bundle = Bundle()
        bundle.putSerializable("clickedArticle", onArticlePositionClicked)
        val mDetailScreenFragment = DetailScreenFragment()
        mDetailScreenFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, mDetailScreenFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mHomeScreenView.let {
            mHomeScreenView=null
        }

    }

}