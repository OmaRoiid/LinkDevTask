package com.example.linkdevtask.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.linkdevtask.R
import com.example.linkdevtask.util.DateFormatter
import kotlinx.android.synthetic.main.article_details.*
import kotlinx.android.synthetic.main.details_screen.*


class DetailScreenFragment : Fragment() {
    private val mDetailArticleArgs: DetailScreenFragmentArgs by navArgs()
    private var mDetailScreenView: View?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDetailScreenView = inflater.inflate(R.layout.fragment_details_screen, container, false)
        return mDetailScreenView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArticleDetailView()
    }

    private fun setupArticleDetailView() {
        val ownerTxt = "By ${mDetailArticleArgs.article.author}"
        val formattedDate=mDetailArticleArgs.article.publishedAt?.substring(0, 10)
        article_title_ds.text = mDetailArticleArgs.article.title
        article_owner_ds.text = ownerTxt
        article_desc_ds.text = mDetailArticleArgs.article.description
        article_date_ds.text = formattedDate?.let { DateFormatter.format(it) }
        open_btn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mDetailArticleArgs.article.url))
            startActivity(browserIntent)
        }
        Glide.with(requireContext())
            .load(mDetailArticleArgs.article.urlToImage)
            .placeholder(R.drawable.holder)
            .into(article_iv_ds)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mDetailScreenView.let {
            mDetailScreenView=null
        }
    }
}