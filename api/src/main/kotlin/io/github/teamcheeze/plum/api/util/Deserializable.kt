package io.github.teamcheeze.plum.api.util

interface Deserializable<T> {
    class InvalidSerializedStringException(msg: String): Exception(msg)
    class SerializationTypeMismatchException(msg: String): Exception(msg)
    class InvalidSerializationParameterException(msg: String): Exception(msg)
    fun deserialize(str: String): T
}