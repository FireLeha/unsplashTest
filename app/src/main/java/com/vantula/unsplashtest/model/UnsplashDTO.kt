package com.vantula.unsplashtest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashDTO (
    val id: String,
    val title: String,
    val description: String,

    @SerializedName("total_photos")
    val totalPhotos: Long,

    val links: TopicLinks,

    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,

    @SerializedName("preview_photos")
    val previewPhotos: List<PreviewPhoto>
): Parcelable

@Parcelize
data class CoverPhoto (
    val id: String,

    val width: Long,
    val height: Long,
    val color: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val description: String,

    @SerializedName("alt_description")
    val altDescription: String,

    val urls: Urls,
): Parcelable

@Parcelize
data class Urls (
    val regular: String,
): Parcelable

@Parcelize
data class TopicLinks (
    val self: String,
    val html: String,
    val photos: String
): Parcelable

@Parcelize
data class PreviewPhoto (
    val id: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val urls: Urls
): Parcelable
