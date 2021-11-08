package com.example.codingchallenge.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.databinding.ItemsDetailsViewBinding
import com.example.codingchallenge.model.responses.ItunesDataItem
import com.example.codingchallenge.utils.ImageDisplayer
import javax.inject.Inject

class ItemsDetailsAdapter @Inject constructor(
    private val imageDisplayer: ImageDisplayer,
) :
    RecyclerView.Adapter<ItemsDetailsAdapter.ViewHolder>() {
    var onItemClick: ((ItunesDataItem) -> Unit)? = null
    private var itemDetailsList: List<ItunesDataItem> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding =
            ItemsDetailsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Monitoring users scroll to be diplay in the recycle view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemDetailsList[position])
    }

    override fun getItemCount(): Int {
        return itemDetailsList.size
    }

    /**
     * This method is use to notify any new value that was added
     * in room persistence since
     */

    fun updateItunesData(iTunesItemData: List<ItunesDataItem>) {
        this.itemDetailsList = iTunesItemData
        notifyDataSetChanged()


    }


    inner class ViewHolder(
        private val itemsDetailsBinding: ItemsDetailsViewBinding,
    ) :
        RecyclerView.ViewHolder(itemsDetailsBinding.root) {

        fun bind(iTunesItemData: ItunesDataItem) {
            /**
             * Attaching the data coming from remote and saving it via room persistence
             * to the views
             */
            itemsDetailsBinding.apply {

                trackNameText.text = iTunesItemData.trackName
                trackGenreText.text = iTunesItemData.primaryGenreName
                trackPriceText.text = iTunesItemData.trackPrice
                iTunesItemData.artworkUrl100?.let { imageUrl ->
                    imageDisplayer.displayImage(albumImage = albumCover,
                        imageUrl)
                }
                // handling click events of the views
                itemsDetailsBinding.root.setOnClickListener {
                    onItemClick?.invoke(iTunesItemData)
                }
            }


        }

    }
}