package homework31.attach

import homework31.files.*

sealed class Attachment(val type: String)

data class VideoAttachment(val video: Video) : Attachment("video")
data class AudioAttachment(val audio: Audio) : Attachment("audio")
data class PhotoAttachment(val photo: Photo) : Attachment("photo")
data class FileAttachment(val file: File) : Attachment("file")
data class PresentAttachment(val present: Present) : Attachment("present")