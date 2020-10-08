package io.github.julo15.mvvmtutorial.imageupload

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageUploadViewModel : ViewModel() {
    private val _uris = MutableLiveData<List<Uri>>(emptyList())
    val uris: LiveData<List<Uri>>
        get() = _uris

    fun onUriAdded(uri: Uri) {
        _uris.value?.toMutableList()?.let { newUriList ->
            // Append the uri to a copy of the list of uris
            newUriList.add(uri)

            // Push the new uri list into the LiveData
            _uris.value = newUriList
        }
    }

    fun onUriRemoved(uri: Uri) {
        _uris.value?.filter { it != uri }?.let { newUriList ->
            // Push the new uri list into the LiveData
            _uris.value = newUriList
        }
    }
}