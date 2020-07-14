package com.example.linkdevtask.ui.home
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkdevtask.R
import com.example.linkdevtask.adapter.HomeScreenAdapter
import com.example.linkdevtask.listeners.OnClickedArticleListener
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.roomDB.ArticleDao
import com.example.linkdevtask.roomDB.ArticleDataBase
import com.example.linkdevtask.store.localstorage.LocalStore
import com.example.linkdevtask.store.repository.HomeScreenRepository
import com.example.linkdevtask.store.webservice.WebServiceStore
import kotlinx.android.synthetic.main.fragment_home.*

class HomeScreenFragment : Fragment(), OnClickedArticleListener {
    private lateinit var mHomeScreenViewModel: HomeScreenViewModel
    private lateinit var mHomeScreenViewModelFactory : HomeScreenViewModelFactory
    private lateinit var mHomeScreenAdapter: HomeScreenAdapter
    private var mHomeScreenView: View?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mHomeScreenView= inflater.inflate(R.layout.fragment_home, container, false)
        return mHomeScreenView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArticlesList()
    }
    private fun setupArticlesList(){
        mHomeScreenViewModelFactory = HomeScreenViewModelFactory(requireActivity().application, isNetworkAvailable())
        mHomeScreenViewModel = ViewModelProvider(this, mHomeScreenViewModelFactory).get(HomeScreenViewModel::class.java)
        mHomeScreenViewModel.getArticlesFromServer()
        mHomeScreenViewModel.articlesLiveData.observe(viewLifecycleOwner, Observer { observedList ->
            observedList.let {
                articles_list.layoutManager = LinearLayoutManager(activity)
                mHomeScreenAdapter = HomeScreenAdapter(requireContext(), this, observedList)
                articles_list.adapter = mHomeScreenAdapter
            }
        })
    }

    override fun onArticleClicked(mArticle: Articles) {
        //using safeargs of navigation lib
        val navigateAction = HomeScreenFragmentDirections.actionHomeScreenFragmentToDetailScreenFragment(mArticle)
        findNavController().navigate(navigateAction)
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    /*
    * for avoiding Library leaks
    * you must to destroy the view to make sure that there is any leaks that is the
    * check this link for more ->https://github.com/square/leakcanary/pull/1785
    * */
    override fun onDestroyView() {
        super.onDestroyView()
        mHomeScreenView.let {
            mHomeScreenView=null
        }
    }
}