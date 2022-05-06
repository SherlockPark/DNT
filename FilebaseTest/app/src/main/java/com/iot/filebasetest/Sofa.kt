package com.iot.filebasetest.Sofa

import com.iot.filebasetest.Interior.Interior


open class Sofa(id:String, name : String,design: String,price : Long,isBuy:Boolean, isSet:Boolean):
    Interior(id, name, design, price, isBuy, isSet) {
}