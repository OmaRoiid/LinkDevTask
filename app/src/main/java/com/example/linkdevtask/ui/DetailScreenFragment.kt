package com.example.linkdevtask.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.linkdevtask.R
import com.example.linkdevtask.listeners.DrawerBehaviour
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.util.DateFormatting


class DetailScreenFragment : Fragment() {
    lateinit var mClickedArticle: Articles
    private var mDetailScreenView: View?=null
    lateinit var mDrawerBehaviour: DrawerBehaviour
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mDrawerBehaviour = context as DrawerBehaviour
        } catch (e: Exception) {
            throw ClassCastException("$context must implement DrawerBehaviour")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDetailScreenView = inflater.inflate(R.layout.fragment_details_screen, container, false)
        //passed Article
        val bundle = arguments
        mClickedArticle = bundle!!.getSerializable("clickedArticle") as Articles
        initDetailView()
        mDrawerBehaviour.lockDrawer()
        return mDetailScreenView
    }

    private fun initDetailView() {
        val mArticleTitle = mDetailScreenView?.findViewById<TextView>(R.id.article_title_ds)
        val mArticleRelease= mDetailScreenView?.findViewById<TextView>(R.id.article_date_ds)
        val mArticleOwner = mDetailScreenView?.findViewById<TextView>(R.id.article_owner_ds)
        val mArticleDesc = mDetailScreenView?.findViewById<TextView>(R.id.article_desc_ds)
        val mArticleImage = mDetailScreenView?.findViewById<ImageView>(R.id.article_iv_ds)
        val mArticlePage = mDetailScreenView?.findViewById<Button>(R.id.open_btn)
        val ownerTxt = "By " + mClickedArticle.author
        val formattedDate=mClickedArticle.publishedAt.substring(0, 10)
        mArticleTitle?.text = mClickedArticle.title
        mArticleOwner?.text = ownerTxt
        mArticleDesc?.text = mClickedArticle.description
        mArticleRelease?.text = DateFormatting.reFormat(formattedDate)
        mArticlePage?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${mClickedArticle.url}"))
            startActivity(browserIntent)
        }
        Glide.with(requireContext())
            .load(mClickedArticle.urlToImage)
            .placeholder(R.drawable.holder)
            .into(mArticleImage!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mDrawerBehaviour.unlockDrawer()
        mDetailScreenView.let {
            mDetailScreenView=null
        }
    }

}