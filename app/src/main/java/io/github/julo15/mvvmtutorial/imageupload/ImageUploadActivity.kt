package io.github.julo15.mvvmtutorial.imageupload

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.julo15.mvvmtutorial.databinding.ActivityImageUploadBinding

class ImageUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageUploadBinding

    private val imageAdapterListener = object : ImageAdapter.Listener {
        override fun onDelete(uri: Uri) {
            urisToUpload.remove(uri)
            imageAdapter.updateUris(urisToUpload)
        }
    }

    private val imageAdapter: ImageAdapter = ImageAdapter(imageAdapterListener)

    private val urisToUpload = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageUploadBinding.inflate(layoutInflater)
        initializeAddImageButton()
        initializeImageRecyclerView()

        setContentView(binding.root)
    }

    private fun initializeAddImageButton() {
        binding.addImageButton.setOnClickListener {
            showPickerDialog(this) { uri ->
                uri?.let { uriToAdd ->
                    addUri(uriToAdd)
                }
            }
        }
    }

    private fun initializeImageRecyclerView() {
        binding.imageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ImageUploadActivity, LinearLayoutManager.VERTICAL, false)
            adapter = imageAdapter
        }
    }

    private fun addUri(uri: Uri) {
        urisToUpload.add(uri)
        imageAdapter.updateUris(urisToUpload)
    }
}