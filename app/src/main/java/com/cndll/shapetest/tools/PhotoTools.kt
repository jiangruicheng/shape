package com.cndll.shapetest.tools

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File

/**
 * Created by Administrator on 2017/7/17 0017.
 */
class PhotoTools{
    //拍照
    val PHOTO=101
    //本地选择
    val ALBUM=102
    /**
     * 拍照
     */
     fun takePhoto(activity: Activity):Uri {
        val file = File(Environment.getExternalStorageDirectory(), "shape/拍照")
        if (!file.exists()) {
            file.mkdir()
        }
        val output = File(file, System.currentTimeMillis().toString() + ".jpg")
        try {
            if (output.exists()) {
                output.delete()
            }
            output.createNewFile()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        var imageUri = Uri.fromFile(output)
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        activity.startActivityForResult(intent, PHOTO)
        return imageUri
    }


    /**
     * 本地选择一张图片
     * */
      fun getImageFromAlbum(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"//相片类型
        activity.startActivityForResult(intent, ALBUM)
    }
}