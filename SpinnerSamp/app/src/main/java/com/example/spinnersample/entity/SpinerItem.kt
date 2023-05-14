package com.example.spinnersample.entity

data class SpinerItem(
    var uid: Int?,
    var Name: String
){
    override fun toString(): String {
        //Здесь настраивается визуальное представление
        //каждого элемента данных в Spinner. В данном случае -
        //просто вывод значения поля Name, но можно задать и
        //более сложный формат, и даже алгоритм
        return Name
    }
}
