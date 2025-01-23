package ru.netology.nmedianew.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedianew.dto.Post

class PostRepositoryInMemory : PostRepository {
    private val data = MutableLiveData(
        Post(
            id = 1,
            likes = 115,
            shares = 5098,
            views = 1299998,
            author = "Нетология. Университет интернет-профессий будущего",
            date_and_time_of_publication = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likeByMe = false,
        )
    )

    override fun getPost(): LiveData<Post> = data

    override fun like() {
        val currentPost = data.value ?: return
        val updatedPost = currentPost.copy(
            likeByMe = !currentPost.likeByMe,
            likes = if (currentPost.likeByMe) {115} else {116}
        )
        data.value = updatedPost

    }
}