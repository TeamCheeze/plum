package io.teamcheeze.plum.api.util.core

interface Deserializable<T> {
    class InvalidSerializedStringException(msg: String): Exception(msg)
    class SerializationTypeMismatchException(msg: String): Exception(msg)
    class InvalidSerializationParameterException(msg: String): Exception(msg)
    fun deserialize(str: String): T
}