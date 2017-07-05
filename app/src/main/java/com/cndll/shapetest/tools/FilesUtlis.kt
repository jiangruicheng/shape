package com.cndll.shapetest.tools

import android.content.Context
import android.os.Environment
import java.io.File

/**
 * Created by Administrator on 2017/7/5 0005.
 * 清楚缓存
 */
 class FilesUtlis {
    /**
     * 清除缓存

     * @param context
     */
    fun clearAllCache(context: Context) {
        deleteDir(context.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            deleteDir(context.externalCacheDir)
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children!!.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir!!.delete()
    }

    /**
     * 获取缓存大小
     * @param context
     * *
     * @return
     * *
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getTotalCacheSize(context: Context): String {
        var cacheSize =getDirSize(context.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            cacheSize +=getDirSize(context.externalCacheDir)
        }
        return formatFileSize(cacheSize)
    }

    /**
     * 获取目录文件大小

     * @param dir
     * *
     * @return
     */
    fun getDirSize(dir: File?): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files!!) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    /**
     * 转换文件大小

     * @param fileS
     * *
     * @return B/KB/MB/GB
     */
    fun formatFileSize(fileS: Long): String {
        val df = java.text.DecimalFormat("#.00")
        var fileSizeString:String?
        if (fileS < 1024) {
            fileSizeString = df.format(fileS.toDouble()) + "B"
        } else if (fileS < 1048576) {
            fileSizeString = df.format(fileS.toDouble() / 1024) + "KB"
        } else if (fileS < 1073741824) {
            fileSizeString = df.format(fileS.toDouble() / 1048576) + "MB"
        } else {
            fileSizeString = df.format(fileS.toDouble() / 1073741824) + "G"
        }
        return fileSizeString
    }

    /**
     * 版本号
     * */
    fun getVersion(context: Context): String {
        try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            val version = info.versionName
            return "V" + version
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

}