package homework31.files

open class File(
    val executor: String,
    val url: String,
    val name: String
) {
    override fun toString(): String {
        return "Executor: $executor, Name: $name "
    }
}

class Audio(executor: String, url: String, name: String) : File(
    executor, url, name) {
    override fun toString(): String {
        return "Artist $executor, Song: $name "
    }
}

class Video(executor: String, url: String, name: String) : File(
    executor, url, name) {
    override fun toString(): String {
        return "Producer $executor, Video: $name "
    }
}

class Photo (executor: String, url: String, name: String) : File(
    executor, url, name) {
    override fun toString(): String {
        return "Photograph $executor, Photo: $name "
    }
}
class Present (executor: String, url: String, name: String) : File(
    executor, url, name) {
    override fun toString(): String {
        return "Presenter $executor, Present: $name "
    }
}