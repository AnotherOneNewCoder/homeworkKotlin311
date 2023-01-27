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
    private var comments2 = mutableListOf(
        Comment(10, 10, "first"),
        Comment(11, 11, "second"),
        Comment(12, 12, "third"),
        Comment(13, 13, "fourth")
    )
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

//    0 — спам;
//    1 — детская порнография;
//    2 — экстремизм;
//    3 — насилие;
//    4 — пропаганда наркотиков;
//    5 — материал для взрослых;
//    6 — оскорбление;
//    8 — призывы к суициду.


    fun reason(number:Int){
        when(number) {
            0 -> println("спам")
            1 -> println("детская порнография")
            2 -> println("экстремизм")
            3 -> println("насилие")
            4 -> println("пропаганда наркотиков")
            5 -> println("материал для взрослых")
            6 -> println("оскорбление")
            8 -> println("призывы к суициду")
            else -> throw ReasonNotFoundException("No reason")
        }
    }
//    fun toComplain(comment: Comment, reason: Int): String {
//        var check = true
//        for ((index, post) in posts.withIndex()) {
//            for ((index, comm) in comments.withIndex()) {
//                if (comment.id == comm.id){
//                    reason(reason)
//                    comments.drop(index)
//
//                    return "Successful removed"
//                }
//            }
//            val updateComments = post.copy(comment = comments)
//            posts.set(index, updateComments)
//            check = false
//        }
//        if (check)
//            throw CommetNotFoundException("Comment not found")
//        return "Nothing to remove"
//    }
    fun toComplain(comment: Comment, reason: Int): String {
        var check = true
        for ((index, comm) in comments2.withIndex()) {
            if (comment.id == comm.id){
                reason(reason)
                comments2.removeAt(index)
                check = false
                println(comments2)
                return "Successful removed"
                }
            }
        if (check)
            throw CommetNotFoundException("Comment not found")
        return "Nothing to remove"
    }
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
class CommetNotFoundException(message: String): RuntimeException(message)
class ReasonNotFoundException(message: String): RuntimeException(message)
class Comment(
    counts: Int = 0,
    var id: Int,
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

