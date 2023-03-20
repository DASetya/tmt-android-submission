package com.example.movieapps.dummy

import com.example.movieapps.R
import com.example.movieapps.model.Movie

object MovieData {
    fun getList(): List<Movie> {
        return listOf(
            Movie(
                R.drawable.avengers_bd,
                R.drawable.avengers_poster,
                "Avengers: Endgame",
                "2019-04-24",
                8.3,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store."
            ),
            Movie(
                R.drawable.spiderman_bd,
                R.drawable.spiderman_poster,
                "Spider-Man: No Way Home",
                "2021-12-15",
                8.4,
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
            ),
            Movie(
                R.drawable.thor_bd,
                R.drawable.thor_poster,
                "Thor: Ragnarok",
                "2017-10-24",
                7.6,
                "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela."
            ),
            Movie(
                R.drawable.hobbsshaw_bd,
                R.drawable.hobbsshaw_poster,
                "Fast & Furious Presents: Hobbs & Shaw",
                "2019-08-01",
                6.9,
                "Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have traded smack talk and body blows. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, they join forces to defeat him."
            ),
            Movie(
                R.drawable.conjuring_bd,
                R.drawable.conjuring_poster,
                "The Conjuring 2",
                "2016-06-08",
                7.3,
                "Lorraine and Ed Warren travel to north London to help a single mother raising four children alone in a house plagued by malicious spirits."
            )
        )
    }
}