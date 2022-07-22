package com.vantula.unsplashtest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashDTO(
    val id: String,
    val title: String,
    val description: String,

    val visibility: String,
    val featured: Boolean,

    @SerializedName("total_photos")
    val totalPhotos: Long,

    val links: TopicLinks,
    val status: String,
    val owners: List<User>,

    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,

    @SerializedName("preview_photos")
    val previewPhotos: List<PreviewPhoto>,
) : Parcelable

@Parcelize
data class PreviewPhoto (
    val id: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val urls: Urls
):Parcelable

@Parcelize
data class TopicLinks (
    val self: String,
    val html: String,
    val photos: String
):Parcelable

@Parcelize
data class CoverPhoto (
    val id: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    val width: Long,
    val height: Long,
    val color: String,

    @SerializedName("blur_hash")
    val blurHash: String,

    val description: String? = null,

    @SerializedName("alt_description")
    val altDescription: String? = null,

    val urls: Urls,
    val links: CoverPhotoLinks,
    val likes: Long,

    @SerializedName("liked_by_user")
    val likedByUser: Boolean,

    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissions,

    val user: User
):Parcelable

@Parcelize
data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,

    @SerializedName("small_s3")
    val smallS3: String
):Parcelable

@Parcelize
data class CoverPhotoLinks (
    val self: String,
    val html: String,
    val download: String,

    @SerializedName("download_location")
    val downloadLocation: String
):Parcelable

@Parcelize
data class TopicSubmissions (
    @SerializedName("digital-screens")
    val digitalScreens: DigitalScreens,

    @SerializedName("business-work")
    val businessWork: BusinessWork
):Parcelable

@Parcelize
data class BusinessWork (
    val status: String
):Parcelable

@Parcelize
data class DigitalScreens (
    val status: String,

    @SerializedName("approved_on")
    val approvedOn: String
):Parcelable



@Parcelize
data class User (
    val id: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    val username: String,
    val name: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @SerializedName("portfolio_url")
    val portfolioURL: String,

    val bio: String? = null,
    val location: String,
    val links: UserLinks,

    @SerializedName("profile_image")
    val profileImage: ProfileImage,

    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @SerializedName("total_collections")
    val totalCollections: Long,

    @SerializedName("total_likes")
    val totalLikes: Long,

    @SerializedName("total_photos")
    val totalPhotos: Long,

    @SerializedName("accepted_tos")
    val acceptedTos: Boolean,

    @SerializedName("for_hire")
    val forHire: Boolean,

    val social: Social
):Parcelable

@Parcelize
data class UserLinks (
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
):Parcelable

@Parcelize
data class ProfileImage (
    val small: String,
    val medium: String,
    val large: String
):Parcelable

@Parcelize
data class Social (
    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @SerializedName("portfolio_url")
    val portfolioURL: String,

    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @SerializedName("paypal_email")
    val paypalEmail: String? = null
):Parcelable
