package com.example.gsport24.root.mapperBase

interface Mapper<T, R> {

    operator fun invoke(input: T): R

}