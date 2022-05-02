package ObjectTest

import DNTClass.TrainInfo

fun main(){
    /*var train1 : TrainInfo = TrainInfo(1880,1,2,10,"천안역")
    for(item in train1.carriage){
        println(train1.carriage[0]?.carrName)
    }*/

   var intArray : Array<Int?> = arrayOfNulls<Int>(0)
    for(item in intArray.indices){
        println(intArray[item])
    }
}