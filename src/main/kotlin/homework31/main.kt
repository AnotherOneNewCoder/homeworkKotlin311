import homework31.attach.*
import homework31.files.*

fun main() {
    val com = Comment()
    val arrayComment = arrayOf(com)
    val like = Likes()
    val newCom = Comment(6, "hi")
    val newCom2 = Comment(6, "hello")
    like.counts = -4
    com.counts = - 10

    val audioAttach = AudioAttachment(Audio("Nirvana", "www.nirvana.org", "Smells like teen spirit"))
    val videoAttach = VideoAttachment(Video("Simpsons", "www.ourweekend.org", "Family in the forest"))
    val photoAttach = PhotoAttachment(Photo("Simpsons", "www.ourweekend.org", "We met bear"))
    val fileAttach = FileAttachment(File("Simpsons", "www.ourweekend.org", "Our family notes"))
    val presentAttach = PresentAttachment(Present("Simpsons", "www.ourweekend.org", "Present from forest"))
    val arrayAttach = arrayOf(audioAttach,videoAttach,photoAttach,fileAttach,presentAttach)

    val post1 = Post(1, 123,321, 211221, null, "Nature","Advanture in the forest",true,
        arrayComment, like,arrayAttach)
    val post2 = Post(1, 123,321, 211221, 111, "Since","News from space",true,
        arrayComment, like, arrayAttach)
    val post3 = Post(1, 123,321, 211221, 111, "Medicine","Health to everyone",true,
        arrayComment, like, arrayAttach)
    val post4 = Post(3, 321,123, 217221, null, "Update","ggg",true,
        arrayComment, like,arrayAttach)
    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
//    WallService.print()
    WallService.update(post4)
//    WallService.print()
    WallService.createComment(3, newCom)
    WallService.print()
    WallService.createComment(3, newCom2)
    WallService.print()
    WallService.createComment(15, newCom)
}