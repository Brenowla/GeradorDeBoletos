package com.example.geradorboletos.resource

abstract class Resource <T>(val data: T? = null, val error: String? = null)

class Sucess<T>(data: T? = null): Resource<T>(data = data)

class Failure<T>(error: String) : Resource<T>(error = error)