package ru.netology.nmedianew

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import ru.netology.nmedianew.databinding.ActivityMainBinding
import ru.netology.nmedianew.dto.Post
import ru.netology.nmedianew.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel by viewModels<PostViewModel> ()

        viewModel.post.observe(this){ post ->
            binding.author.text = post.author
            binding.textOfThePublication.text = post.content
            binding.dateAndTimeOfPublication.text = post.date_and_time_of_publication
            binding.volumeOfLikes.text = formatCount(post.likes)
            binding.volumeOfShare.text = formatCount(post.shares)
            binding.volumeOfViews.text = formatCount(post.views)

            binding.root.setOnClickListener {
            }

            binding.likes.setOnClickListener {
                //post.likeByMe = !post.likeByMe
                updateLike(binding, post)
                viewModel.like()
            }

            binding.shares.setOnClickListener {
                updateShare(binding, post)
            }

            binding.views.setOnClickListener {
                updateViews(binding, post)
            }
        }

    }


    private fun updateLike(binding: ActivityMainBinding, post: Post) {
        binding.likes.setImageResource(
            if (post.likeByMe) {
                R.drawable.baseline_favorite_border_24
            } else {
                R.drawable.baseline_favorite_24
            }
        )
        if (post.likeByMe) post.likes-- else post.likes++
        binding.volumeOfLikes.text = formatCount(post.likes)
    }


    private fun updateShare(binding: ActivityMainBinding, post: Post) {
        post.shares++
        binding.volumeOfShare.text = formatCount(post.shares)
    }

    private fun updateViews(binding: ActivityMainBinding, post: Post) {
        post.views++
        binding.volumeOfViews.text = formatCount(post.views)
    }

    private fun formatCount(count: Long): String {
        var stringReturn: String = ""
        var prCount: Long = 0
        if (count < 1000) {
            stringReturn = count.toString()
            return stringReturn
        } else if (count >= 1000 && count < 10_000) {
            prCount = (count / 100)
            stringReturn = (prCount / 10.0).toString() + "K"
            return stringReturn
        } else if (count >= 10_000 && count < 1_000_000) {
            stringReturn = (count / 1000).toString() + "K"
            return stringReturn
        } else if (count >= 1_000_000) {
            prCount = (count / 100_000)
            stringReturn = (prCount / 10.0).toString() + "M"
            return stringReturn
        }
        return stringReturn
    }
}

