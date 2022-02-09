package com.powerteamdeveloper.multiviewrececlerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.powerteamdeveloper.multiviewrececlerview.R
import com.powerteamdeveloper.multiviewrececlerview.model.NewsFeedsModel
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerSimple
import kotlinx.android.synthetic.main.item_news_feed.view.*

class NewsFeedAdapter (val context: Context,val list: ArrayList<NewsFeedsModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView:View? =null
        itemView = LayoutInflater.from(context).inflate(R.layout.item_news_feed,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ViewHolder){
            var model = list[position]

            holder.bindTo(model,model.viewType)


            Glide.with(context).load(model.profileImage).into(holder.imageProfile)
            holder.txtName.text = model.name
            holder.txtTime.text = model.time

            if (model.viewType ==1) {
                holder.textPost.text = model.postText
            }
            if (model.viewType ==2) {
                holder.txtImagePost.text = model.postText
                Glide.with(context).load(model.postImageUrl).into(holder.imageProfile)

            }
            if (model.viewType ==3) {
                holder.txtVideoPost.text = model.postText
                videoInit(model.postVideoUrl,holder)
            }
        }

    }

    private fun videoInit(path:String,holder: ViewHolder){

        holder.postVideo.setUp(path,JCVideoPlayerSimple.SCREEN_LAYOUT_NORMAL,"")
        val mediaController = MediaController(context)
        mediaController.setAnchorView(holder.postVideo)
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val txtName =view.txtName
        val txtTime = view.txtTime

        val imageProfile = view.imgProfile
        val postImage =view.imgPost
        val postVideo =view.videoView

        val textPost = view.txtPostText
        val txtImagePost = view.txtPostImage
        val txtVideoPost = view.txtPostVideo


        val txtLayout = view.layoutText
        val imageLayout = view.layoutImage
        val videoLayout = view.layoutVideo

        var model:NewsFeedsModel? = null

            fun bindTo(model: NewsFeedsModel,viewType: Int){
                this.model = model
                this.model?.let {

                    when(viewType){
                        1 -> {
                            txtLayout.visibility = View.VISIBLE
                            imageLayout.visibility = View.GONE
                            videoLayout.visibility = View.GONE

                        }
                        2 -> {
                            txtLayout.visibility = View.GONE
                            imageLayout.visibility = View.VISIBLE
                            videoLayout.visibility = View.GONE

                        }
                        3 -> {
                            txtLayout.visibility = View.GONE
                            imageLayout.visibility = View.GONE
                            videoLayout.visibility = View.VISIBLE

                        }
                        else ->{
                            txtLayout.visibility = View.GONE
                            imageLayout.visibility = View.GONE
                            videoLayout.visibility = View.GONE

                        }
                    }


                }

            }

    }

}