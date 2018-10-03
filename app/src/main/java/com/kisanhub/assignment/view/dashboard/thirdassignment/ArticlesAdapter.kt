package com.kisanhub.assignment.view.dashboard.thirdassignment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.kisanhub.assignment.R
import com.kisanhub.assignment.model.ArticlesDetails
import com.kisanhub.assignment.model.SubscriptionPackageItem
import kotlinx.android.synthetic.main.row_item_articles_list.view.*
import java.util.*

class ArticlesAdapter(private val context: Activity, private val articlesList: List<ArticlesDetails>, private val mCallBack: OnShowArticlesDetails) : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_articles_list, parent, false)
        return ArticlesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val articles = articlesList.get(position)
        holder.title.setText(articles.title)
        holder.description.setText(articles.description)
        Log.i("Data:", articlesList.toString())
        holder.published.setText(articles.publish_date_readable)
        holder.created.setText(articles.created_readable)
        holder.type.setText(articles.article_type_fk)
        if (articles.publish_date.isNotEmpty()) {
            holder.date.setText(articles.publish_date.subSequence(0, 10))
        }

        holder.subscription.setOnClickListener({
            if (articles.subscription_package!!.isNotEmpty()) {
                openListPopUp(articles.subscription_package)
            } else {
                Toast.makeText(context, context.getString(R.string.subscription_package_not), Toast.LENGTH_LONG).show()
            }
        })

        if (articles.featured_image != null && articles.featured_image.size > 0) {
            setImages(holder.image, articles.featured_image.get(0).image_file)
        }

        if(articles.region_list!!.isNotEmpty()){
            holder.region.setText(articles.region_list.get(0).name)
        }

        if(articles.get_status_display=="Published"){
            holder.status.visibility = VISIBLE
        }else{
            holder.status.visibility = GONE
        }

        if (!articles.authors!!.isEmpty()) {
            holder.layout_profile.visibility = VISIBLE
            holder.author_name_1.setText(articles.authors[0].full_name)
            displayRoundImageforProfileUrl(holder.author_profile_1, articles.authors[0].picture)
            if (articles.authors.size > 1) {
                holder.author_name_2.setText(articles.authors[1].full_name)
                displayRoundImageforProfileUrl(holder.author_profile_2, articles.authors[1].picture)
                holder.layout_authors_profile_2.visibility = VISIBLE
            } else {
                holder.layout_authors_profile_2.visibility = GONE
            }
        } else {
            holder.layout_profile.visibility = GONE
        }

    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    inner class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.tv_title
        var image = itemView.iv_image
        var description = itemView.tv_description
        var author_profile_1 = itemView.iv_authors_profile_1
        var author_profile_2 = itemView.iv_authors_profile_2
        var author_name_1 = itemView.tv_authors_name_1
        var author_name_2 = itemView.tv_authors_name_2
        var layout_profile = itemView.ll_profile
        var layout_authors_profile_2 = itemView.rl_author_profile_2
        var published = itemView.tv_publish
        var created = itemView.tv_created
        var type = itemView.tv_type
        var date = itemView.tv_date
        var subscription = itemView.btn_subscription_package
        var region=itemView.tv_region
        var status=itemView.tv_status
    }

    interface OnShowArticlesDetails {
        fun onClickShowDetails(articlesDetails: ArticlesDetails)
    }


    fun setImages(imageView: ImageView?, url: String?) {

        val myOptions = RequestOptions()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.kisanhub_logo)
                .placeholder(R.drawable.kisanhub_logo)

        Glide.with(context)
                .load(url)
                .apply(myOptions)
                .into(imageView)
    }

    fun displayRoundImageforProfileUrl(imageView: ImageView?, url: String?) {
        val myOptions = RequestOptions()
                .centerCrop()
                .dontAnimate()
                .error(R.drawable.ic_profle)
                .placeholder(R.drawable.ic_profle)

        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load(url)

                .into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imageView?.setImageDrawable(circularBitmapDrawable)
                    }
                })
    }

    fun openListPopUp(oldList: List<SubscriptionPackageItem>?) {
        val newList = ArrayList<String>()
        for (i in 0 until oldList!!.size) {
            newList.add(oldList.get(i).name)
        }
        MaterialDialog.Builder(context)
                .title(context.getString(R.string.subscription_package))
                .items(newList)
                .show()
    }
}