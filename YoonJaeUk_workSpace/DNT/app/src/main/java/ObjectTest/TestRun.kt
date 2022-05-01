package ObjectTest

import DNTClass.TrainInfo

fun main(){
    val TRAIN1 : TrainInfo = TrainInfo(1880,1,2,"천안역")
    for(item in 0 until TRAIN1.CARRIAGE_COUNT){
        val STR:String = "${TRAIN1.UNIQUE_NUMBER}호 - ${TRAIN1.carriage.get(item)?.CARR_NAME!!}"
        println(STR)
    }
}