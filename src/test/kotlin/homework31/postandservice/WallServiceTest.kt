package homework31.postandservice


import Comment
import CommetNotFoundException
import Likes
import Post
import PostNotFoundException
import ReasonNotFoundException
import WallService
import homework31.attach.*
import homework31.files.*
import org.junit.Assert.*
import org.junit.Test


class WallServiceTest {
    val com = Comment(1, 1,"com")
    val arrayComment = arrayOf(com)
    private var posts = emptyArray<Post>()
    private var id = 0
    val audioAttach = AudioAttachment(Audio("Nirvana", "www.nirvana.org", "Smells like teen spirit"))
    val videoAttach = VideoAttachment(Video("Simpsons", "www.ourweekend.org", "Family in the forest"))
    val photoAttach = PhotoAttachment(Photo("Simpsons", "www.ourweekend.org", "We met bear"))
    val fileAttach = FileAttachment(File("Simpsons", "www.ourweekend.org", "Our family notes"))
    val presentAttach = PresentAttachment(Present("Simpsons", "www.ourweekend.org", "Present from forest"))
    val arrayAttach = arrayOf(audioAttach,videoAttach,photoAttach,fileAttach,presentAttach)

    @Test
    fun createComment() {
        val service = WallService
        val necom = Comment(3, 1, "newcom")
        service.add(Post(0,1,1,10123,0,"test1", "test1",true,arrayComment,Likes(-3),arrayAttach))
        service.add(Post(0,1,1,10123,0,"test2", "test2",true,arrayComment,Likes(-3),arrayAttach))
        service.add(Post(0,1,1,10123,0,"test31", "test3",true,arrayComment,Likes(-3),arrayAttach))
        service.createComment(2, necom)
        val result = necom
        assertEquals(necom, result)
    }
    @Test(expected = PostNotFoundException::class)
    fun createCommentException() {
        val service = WallService
        val necom = Comment(3, 1,"newcom")
        service.add(Post(0, 1, 1, 10123, 0, "test1", "test1", true, arrayComment, Likes(-3), arrayAttach))
        service.add(Post(0, 1, 1, 10123, 0, "test2", "test2", true, arrayComment, Likes(-3), arrayAttach))
        service.add(Post(0, 1, 1, 10123, 0, "test31", "test3", true, arrayComment, Likes(-3), arrayAttach))
        service.createComment(15, necom)

    }

    @Test
    fun add() {
        id++
        val post = Post(0, 1,1,170123,1,"test", "testing", false, arrayComment, Likes(3), arrayAttach)
        val postId = post.copy(id = id)
        posts += postId
        val result = postId.id
        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(0,1,1,10123,0,"test1", "test1",true,arrayComment,Likes(-3),arrayAttach))
        service.add(Post(0,1,1,10123,0,"test2", "test2",true,arrayComment,Likes(-3),arrayAttach))
        service.add(Post(0,1,1,10123,0,"test31", "test3",true,arrayComment,Likes(-3),arrayAttach))
        val update = Post(2,1,1,170123,null,"test4", "test4",true,arrayComment,Likes(-3),arrayAttach)
        val result = service.update(update)
        assertTrue(result)
    }
    @Test
    fun updateNotExisting() {
        val service = WallService
        service.add(Post(0,1,1,10123,0,"test1", "test1",true,arrayComment,Likes(-3),arrayAttach))
        service.add(Post(0,1,1,10123,0,"test2", "test2",true,arrayComment,Likes(-3),arrayAttach))
        service.add(Post(0,1,1,10123,0,"test3", "test3",true,arrayComment,Likes(-3),arrayAttach))
        val update = Post(57,1,1,170123,null,"test4", "test4",true,arrayComment,Likes(-3),arrayAttach)
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun toComplainDone() {
        val service = WallService
        val complain = Comment(13, 13, "fourth")
        val result = println(service.toComplain(complain, 6))
        assertEquals(kotlin.Unit, result)
    }
    @Test(expected = CommetNotFoundException::class)
    fun toComplainFailsNoComment() {
        val service = WallService
        val complain = Comment(13, 17, "fourth")
        service.toComplain(complain,1)

    }
    @Test(expected = ReasonNotFoundException::class)
    fun toComplainFailsNoReason() {
        val service = WallService
        val complain = Comment(11, 11, "second")
        service.toComplain(complain,19)

    }



}

