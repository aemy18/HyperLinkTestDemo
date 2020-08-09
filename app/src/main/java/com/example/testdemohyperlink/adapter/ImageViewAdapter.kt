package com.example.testdemohyperlink.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testdemohyperlink.R
import com.example.testdemohyperlink.models.Image
import kotlinx.android.synthetic.main.adapter_image_list.view.*

class ImageViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfImages = listOf<Image>()

    fun setImageList(listOfMovies: List<Image>) {
        this.listOfImages = listOfMovies
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageListStaggeredViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_image_list, parent, false)
        )
    }

    override fun getItemCount(): Int = listOfImages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = holder as ImageListStaggeredViewHolder
        movieViewHolder.bindView(listOfImages[position])
    }
}

class ImageListStaggeredViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(movieModel: Image) {
        Glide.with(itemView.context).load(movieModel.url).into(itemView.imgView)
    }
}