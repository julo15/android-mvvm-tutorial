package io.github.julo15.mvvmtutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.julo15.mvvmtutorial.imageupload.ImageUploadActivity
import io.github.julo15.mvvmtutorial.imageupload.ImageUploadMVVMActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_upload_button.setOnClickListener {
            startActivity(Intent(this, ImageUploadActivity::class.java))
        }

        image_upload_mvvm_button.setOnClickListener {
            startActivity(Intent(this, ImageUploadMVVMActivity::class.java))
        }
    }
}