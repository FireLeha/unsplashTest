package com.vantula.unsplashtest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashImagesDTO(
    val id: String,

    val width: Long,
    val height: Long,
    val color: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val urls: ImagesUrls,
): Parcelable

@Parcelize
data class ImagesUrls (
    val regular: String
): Parcelable




