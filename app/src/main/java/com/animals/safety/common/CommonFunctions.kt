package com.animals.safety.common

/** Capitalize the first char of a string. */
fun capitalizeFirstChar(input: String): String {
    return input.replaceFirstChar { it.uppercaseChar() }
}