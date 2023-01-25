import homework31.attach.Attachment
import java.lang.RuntimeException
import java.util.*

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
                    val postUpdate = post.copy(ownerId = post2.ownerId, date = post2.date)
                    posts.set(index, postUpdate)
                    return true
                }

            }
        }
        return false
    }

    //    fun createComment(postId: Int, comment: Comment): Comment {
//        try {
//            for ((index, post) in posts.withIndex()){
//                if (postId == post.id){
//                    comments += comment
//                    val updateComment = post.copy(comment = comments)
//                    posts.set(index, updateComment)
//                    return comments.last()
//                }
//            }
//        } catch (e:PostNotFoundException) {
//            println("no post with id: $postId")
//        }
//        return comment
//    }
    fun createComment(postId: Int, comment: Comment): Comment {
        var check: Boolean = true
        for ((index, post) in posts.withIndex()){
            if (postId == post.id){
                comments += comment
                val updateComment = post.copy(comment = comments)
                posts.set(index, updateComment)
                check = false
                return comments.last()
            }

        }
        if (check)
            throw PostNotFoundException("Id $postId not found")
        return comment
    }
}
class PostNotFoundException(message: String): RuntimeException(message)
class Comment(
    counts: Int = 0,
    var commet: String = "",
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
        return " counts = $counts, comment: $commet ,canPost = $canPost, groupsCanPost = $groupsCanPost, canClose = $canClose, canOpen = $canOpen"
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

