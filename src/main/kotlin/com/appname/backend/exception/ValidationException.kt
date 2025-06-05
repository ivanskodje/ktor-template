package com.appname.backend.exception

class ValidationException(val errors: List<FieldError>) : RuntimeException()

data class FieldError(val field: String, val message: String)
