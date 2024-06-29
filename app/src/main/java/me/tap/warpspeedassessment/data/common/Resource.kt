package me.tap.warpspeedassessment.data.common

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }

        fun <T> nothing(data: T? = null, message: String = ""): Resource<T> {
            return Resource(Status.NOTHING, data, message)
        }
    }

    fun isSuccess(): Boolean =
        status == Status.SUCCESS

    fun isLoading(): Boolean =
        status == Status.LOADING

    fun isError(): Boolean =
        status == Status.ERROR

    fun isNothing(): Boolean =
        status == Status.NOTHING

}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    NOTHING
}
