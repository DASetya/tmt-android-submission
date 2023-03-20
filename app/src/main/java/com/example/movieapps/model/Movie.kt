package com.example.movieapps.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var backdrop: Int? = null,
    var poster: Int? = null,
    var title: String? = null,
    var release: String? = null,
    var rating: Double? = null,
    var overview: String? = null,
) : Parcelable
