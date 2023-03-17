package com.example.movieapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.movieapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.menu1.setOnClickListener{
            val thor = Movie("thor_bd", "thor_poster", "Thor: Ragnarok", "2017-10-24",7.6,"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela.")
            MovieDetailActivity.open(this@MainActivity, "${thor.title} Detail",thor)
        }
        binding.menu2.setOnClickListener {
            val spiderman = Movie("spiderman_bd", "spiderman_poster", "Spider-Man: No Way Home", "2021-12-15", 8.4, "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")
            MovieDetailActivity.open(this@MainActivity, "${spiderman.title} Detail", spiderman)
        }
        binding.menu3.setOnClickListener {
            val avengers = Movie("avengers_bd", "avengers_poster", "Avengers: Endgame", "2019-04-24", 8.3, "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.")
            MovieDetailActivity.open(this@MainActivity, "${avengers.title} Detail", avengers)
        }
        binding.menu4.setOnClickListener {
            val hobbsshaw = Movie("hobbsshaw_bd", "hobbsshaw_poster", "Fast & Furious Presents: Hobbs & Shaw", "2019-08-01", 6.9, "Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have traded smack talk and body blows. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, they join forces to defeat him.")
            MovieDetailActivity.open(this@MainActivity, "${hobbsshaw.title} Detail", hobbsshaw)
        }
        binding.menu5.setOnClickListener {
            val thor = Movie("thor_bd", "thor_poster", "Thor: Ragnarok", "2017-10-24",7.6,"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela.")
            MovieDetailActivity.open(this@MainActivity, "${thor.title} Detail",thor)
        }
        binding.menu6.setOnClickListener{
            val thor = Movie("thor_bd", "thor_poster", "Thor: Ragnarok", "2017-10-24",7.6,"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela.")
            MovieDetailActivity.open(this@MainActivity, "${thor.title} Detail",thor)
        }
        binding.menu7.setOnClickListener {
            val spiderman = Movie("spiderman_bd", "spiderman_poster", "Spider-Man: No Way Home", "2021-12-15", 8.4, "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.")
            MovieDetailActivity.open(this@MainActivity, "${spiderman.title} Detail", spiderman)
        }
        binding.menu8.setOnClickListener {
            val avengers = Movie("avengers_bd", "avengers_poster", "Avengers: Endgame", "2019-04-24", 8.3, "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.")
            MovieDetailActivity.open(this@MainActivity, "${avengers.title} Detail", avengers)
        }
        binding.menu9.setOnClickListener {
            val hobbsshaw = Movie("hobbsshaw_bd", "hobbsshaw_poster", "Fast & Furious Presents: Hobbs & Shaw", "2019-08-01", 6.9, "Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have traded smack talk and body blows. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, they join forces to defeat him.")
            MovieDetailActivity.open(this@MainActivity, "${hobbsshaw.title} Detail", hobbsshaw)
        }
    }
}