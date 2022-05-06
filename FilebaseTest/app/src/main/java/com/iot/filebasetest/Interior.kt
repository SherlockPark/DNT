package com.iot.filebasetest.Interior

open class Interior {
    var id:String = ""
    var name : String = ""
    var design : String = ""
    var price : Long = 0;
    var isBuy:Boolean = false
    var isSet:Boolean = false

    constructor(_id:String, _name:String, _design: String, _price: Long, _isBuy:Boolean, _isSet: Boolean){
        id = _id
        name = _name
        design = _design
        price = _price
        isBuy = _isBuy
        isSet = _isSet
    }
}