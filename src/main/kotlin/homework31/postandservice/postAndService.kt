import homework31.attach.Attachment

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val replyPostId : Int?,
    val postType : String,
    val text: String,
    val friendsOnly: Boolean,
    val comment: Array<Comment>,
    val likes: Likes,
    val attach: Array<Attachment>
)
object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var id = 0


    fun add(post: Post): Post {
        //id++
        val postId = post.copy(id = ++id)
        posts += postId
        return posts.last()
    }

    fun print() {
        for (post in posts)
            println(post)
    }

    fun update(post: Post): Boolean {
        for ((index, post2) in posts.withIndex()) {
            when {
                post2.id.equals(post.id) -> {
                    println("found id in array with index: $index")
                    val postUpdate = post.copy(ownerId = post2.ownerId, date = post2.date)
                    posts.set(index, postUpdate)
                    return true
                }

            }
        }
        return false
    }
    fun createComment(postId: Int, comment: Comment): Comment {
        TODO()
    }
}

class Comment(
    counts: Int = 0,
    var canPost: Boolean = true,
    var groupsCanPost: Boolean = true,
    var canClose: Boolean = true,
    var canOpen: Boolean = true
){
    var counts = counts
        set(value){
            if (value < 0)
                return
            field = value
        }

    override fun toString(): String {
        return " counts = $counts, canPost = $canPost, groupsCanPost = $groupsCanPost, canClose = $canClose, canOpen = $canOpen"
    }
}
//count (integer) — число пользователей, которым понравилась запись;
//user_likes* (boolean) — наличие отметки «Мне нравится» от текущего пользователя;
//can_like* (boolean) — информация о том, может ли текущий пользователь поставить отметку «Мне нравится»;
//can_publish* (boolean) — информация о том, может ли текущий пользователь сделать репост записи.
class Likes(
    counts: Int = 0,
    var userLikes: Boolean = true,
    var canLike: Boolean = true,
    var canPublish: Boolean = true
){
    var counts = counts
        set(value){
            field = if (value >= 0)
                value
            else
                0
        }

    override fun toString(): String {
        return " counts = $counts,userLikes = $userLikes, canLike = $canLike, canPublish = $canPublish"
    }
}

