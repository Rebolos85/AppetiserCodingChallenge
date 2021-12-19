package com.example.codingchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.codingchallenge.databinding.ItemsDetailsViewBinding
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.utils.ImageDisplayer
import javax.inject.Inject

class ItemsDetailsAdapter @Inject constructor(
    private val imageDisplayer: ImageDisplayer,
    private val swipeContainer: SwipeRefreshLayout,
) :
    RecyclerView.Adapter<ItemsDetailsAdapter.ViewHolder>() {
    var onItemClick: ((Music) -> Unit)? = null
    private var itemDetailsList: List<Music> = listOf()

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
        swipeContainer.apply {
            when (position) {
                0 -> {
                    isRefreshing = false
                    isEnabled = false
                }
                in 1..48 -> {
                    isRefreshing = true
                    isEnabled = true
                }
                in 49..51 -> {
                    isRefreshing = false
                    isEnabled = false
                }
            }

        }
        holder.bind(itemDetailsList[position])
    }

    // geting the size of the list
    override fun getItemCount(): Int {
        return itemDetailsList.size
    }


    /**
     * responsible for updating the array adapter
     * @param listOfMusic - list of domain model
     */
    fun updateItunesData(listOfMusic: List<Music>) {
        this.itemDetailsList = listOfMusic
        notifyDataSetChanged()


    }


    inner class ViewHolder(
        private val itemsDetailsBinding: ItemsDetailsViewBinding,
    ) :
        RecyclerView.ViewHolder(itemsDetailsBinding.root) {
        /**
         * binding the view to the data coming domain model
         * @param musicDomain - getting the data coming from
         * our domain model
         */
        fun bind(musicDomain: Music) {
            /**
             * Attaching the data coming from remote and saving it via room persistence
             * to the views
             */
            itemsDetailsBinding.apply {
                trackNameText.text = musicDomain.name
                trackGenreText.text = musicDomain.genre

                trackPriceText.text = musicDomain.price.toString()
                musicDomain.imageURL.let { imageUrl ->
                    imageDisplayer.displayImage(
                        albumImage = itemsDetailsBinding.artwork,
                        imageUrl
                    )
                }
                // handling click events of the views
                itemsDetailsBinding.root.setOnClickListener {
                    onItemClick?.invoke(musicDomain)
                }
            }


        }

    }
}