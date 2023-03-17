package com.example.movieapps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var backdrop: String? = null,
    var poster: String? = null,
    var title: String? = null,
    var release: String? = null,
    var rating: Double? = null,
    var overview: String? = null,
) : Parcelable
