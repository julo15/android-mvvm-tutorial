package io.github.julo15.mvvmtutorial.imageupload

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.julo15.mvvmtutorial.databinding.ActivityImageUploadBinding

class ImageUploadMVVMActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageUploadBinding

    private val imageAdapterListener = object : ImageAdapter.Listener {
        override fun onDelete(uri: Uri) {
            imageUploadViewModel.onUriRemoved(uri)
        }
    }

    private val imageAdapter = ImageAdapter(imageAdapterListener)

    private val imageUploadViewModel: ImageUploadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageUploadBinding.inflate(layoutInflater)
        initializeAddImageButton()
        initializeImageRecyclerView()

        setContentView(binding.root)

        observeUris()
    }

    private fun initializeAddImageButton() {
        binding.addImageButton.setOnClickListener {
            showPickerDialog(this) { uri ->
                uri?.let { uriToAdd ->
                    imageUploadViewModel.onUriAdded(uriToAdd)
                }
            }
        }
    }

    private fun initializeImageRecyclerView() {
        binding.imageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ImageUploadMVVMActivity, LinearLayoutManager.VERTICAL, false)
            adapter = imageAdapter
        }
    }

    private fun observeUris() {
        imageUploadViewModel.uris.observe(this, Observer { uris ->
            imageAdapter.updateUris(uris)
        })
    }
}