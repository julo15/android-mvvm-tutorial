package io.github.julo15.mvvmtutorial.imageupload

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.julo15.mvvmtutorial.databinding.ItemImageBinding

class ImageAdapter(private val listener: Listener) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    interface Listener {
        fun onDelete(uri: Uri)
    }

    private var uris: List<Uri>? = null

    fun updateUris(updatedUris: List<Uri>) {
        uris = updatedUris
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(parent: ViewGroup, private val binding: ItemImageBinding =
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(uri: Uri) {
            binding.uriTextView.text = uri.toString()
            binding.deleteButton.setOnClickListener {
                listener.onDelete(uri)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(parent)

    override fun getItemCount() = uris?.size ?: 0

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(uris?.get(position) ?: throw IndexOutOfBoundsException())
    }
}