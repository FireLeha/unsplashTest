package com.vantula.unsplashtest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashDetailDTO (
    val id: String,

    val width: Long,
    val height: Long,
    val color: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val urls: DetailUrl,
): Parcelable

@Parcelize
data class DetailUrl (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
): Parcelable
