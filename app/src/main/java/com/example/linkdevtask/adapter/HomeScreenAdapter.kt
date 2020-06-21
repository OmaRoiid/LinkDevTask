package com.example.linkdevtask.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.linkdevtask.R
import com.example.linkdevtask.listeners.OnClickedArticleListener
import com.example.linkdevtask.model.Articles
import com.example.linkdevtask.util.DateFormatter


class HomeScreenAdapter(private val mContext : Context, var mOnClickedArticleListener: OnClickedArticleListener, private val articlesList: List<Articles>  ) : RecyclerView.Adapter<HomeScreenAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mArticlesCardView: View = LayoutInflater.from(mContext).inflate(R.layout.articles_card,parent,false)
        return MyViewHolder(mArticlesCardView)
    }
    override fun getItemCount(): Int=articlesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(position)

   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
       private val mArticleImage=itemView.findViewById<ImageView>(R.id.article_iv)
       private val mArticleTitle=itemView.findViewById<TextView>(R.id.article_title_tv)
       private val mArticleOwner=itemView.findViewById<TextView>(R.id.article_owner_tv)
       private val mArticleRelease=itemView.findViewById<TextView>(R.id.article_release_tv)
       init {
           itemView.setOnClickListener(this)
       }
            fun bind(position: Int){
                val releaseDate = articlesList[position].publishedAt.substring(0,10)
                mArticleTitle.text=articlesList[position].title
                mArticleOwner.text="By ${articlesList[position].author}"
                mArticleRelease.text=DateFormatter.format(releaseDate)
                Glide.with(mContext)
                    .load(articlesList[position].urlToImage )
                    .placeholder(R.drawable.holder)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(mArticleImage)
            }
        override fun onClick(v: View?) {
            mOnClickedArticleListener.onArticleClicked(articlesList[adapterPosition])
        }

    }

}