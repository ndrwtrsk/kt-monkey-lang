package nd.rw.extensions

fun String.eatWhitespace() : String {
    return this.replace("\\s".toRegex(), "")
}