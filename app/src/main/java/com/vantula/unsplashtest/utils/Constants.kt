package com.vantula.unsplashtest.utils

object Constants {

        const val UNSPLASH_API_URL = "https://api.unsplash.com/"
        const val UNSPLASH_TOPICS_ENDPOINT = "topics"

        const val TOPIC_ID_KEY = "id"
        const val SHARED_PREFERENCES_KEY = "sp_key"
        const val IMAGE_URL_KEY = "image_key"
        const val IMAGE_ID_KEY = "image_id"


        //Page number to retrieve. Value type = Int
        const val PAGE = 1
        //Number of items per page. Value type= Int
        const val ITEMS_PER_PAGE = 23
        //How to sort the topics. Possible values: featured, latest, oldest, position; default: position
        const val ORDER_TOPICS_BY = "latest"
        //How to sort the photos. Possible values: latest, oldest, popular; default: latest)
        const val ORDER_PHOTOS_BY = "latest"
        //Filter by photo orientation. Possible values: landscape, portrait, squarish
        const val PHOTO_ORIENTATION = "squarish"


}