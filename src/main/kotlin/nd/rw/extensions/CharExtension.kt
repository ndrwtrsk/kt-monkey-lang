package nd.rw.extensions

fun Char.isLetterOrUnderScore(): Boolean {
    return this.isLetter() || this == '_'
}