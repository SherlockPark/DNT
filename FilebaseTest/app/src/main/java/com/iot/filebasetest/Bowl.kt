package com.iot.filebasetest.Bowl

import com.iot.filebasetest.Interior.Interior


class Bowl(id:String, name : String,design: String,price : Long,isBuy:Boolean, isSet:Boolean):
    Interior(id, name, design, price, isBuy, isSet) {
}