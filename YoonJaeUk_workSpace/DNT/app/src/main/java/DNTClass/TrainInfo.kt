package DNTClass

import java.io.Serializable

// writer : Yoon Jae Uk
// date : 2022.04.29 ~ ?
// content : 기차 정보를 담고있는 클래스이다.

class TrainInfo
    /* class, member, basic constructor(init) declaration begin */
    (val UNIQUE_NUMBER:Int, val TYPE_ID:Int, CARRIAGE_COUNT:Int, var currentStation:String,
    var nextStation: String, CARRIAGE_MAX_NUM: Int): Serializable {
    // constructor(member, parameter) : interface
        var carriage: Array<CarriageInfo?> = arrayOfNulls(CARRIAGE_COUNT)
        val TOTAL_MAX_NUM:Int

        init{
            var totalNum: Int = 0
            for(item in 0 until CARRIAGE_COUNT){
                carriage[item] = CarriageInfo(item,0,0,CARRIAGE_MAX_NUM)
                totalNum += carriage[item]?.MAX_NUM!!
            }
            TOTAL_MAX_NUM = totalNum
            println("${UNIQUE_NUMBER}호 생성")
        }
    /* class, member, basic constructor(init) declaration end */

    /* class method declaration begin */
    fun getCurNumTotal(): Int {
        var sum: Int = 0
        for(item in 0 until carriage.size)
            sum += carriage[item]?.curNum!!

        return sum
    } // 현 기차의 현재 인원수 총 합계 getter 메소드이다.

    fun getResNumTotal(): Int {
        var sum: Int = 0
        for(item in 0 until carriage.size)
            sum += carriage[item]?.resNum!!

        return sum
    } // 현 기차의 예약된 인원수 총 합계 getter 메소드이다.

    fun getNumMessage(): String {
        val numMessage = "${this.getCurNumTotal()}/${this.getResNumTotal()}/${this.TOTAL_MAX_NUM}"
        return numMessage
    } // 기차의 현 인원수 메세지 getter function

    // 파라미터는 객차의 인덱스 번호로, 0으로 시작하는 번호이다.
    // oldCarr : 기존에 있었던 객차 인덱스 번호
    // newCarr : 이동하려는 객차 인덱스 번호
    fun passengerMoveToCarriage(oldCarr: Int, newCarr: Int){
        var passenger: Int = this.carriage[oldCarr]?.curNum!!
        --passenger
        this.carriage[oldCarr]?.curNum = passenger
        // 기존에 있었던 객차

        passenger = this.carriage[newCarr]?.curNum!!
        ++passenger
        this.carriage[newCarr]?.curNum = passenger
        // 이동하는 객차
    } // 승객의 이동

    // 파라미터는 이동한 역의 이름으로 한다.
    fun trainMoveToStation(station: String){
        this.currentStation = station
    } // 기차의 이동

    // 파라미터는 객차의 인덱스 번호로 한다.
    fun makeReservation(indCarr: Int){
        val counting = this.carriage[indCarr]?.resNum!! + 1
        this.carriage[indCarr]?.resNum = counting
    } // 예매 - 예매 인원 증가

    // 파라미터는 객차의 인덱스 번호로 한다.
    fun getOnTrain(indCarr: Int){
        val counting = this.carriage[indCarr]?.curNum!! + 1
        this.carriage[indCarr]?.curNum = counting
    } // 승차 - 승차 인원 증가

    // 파라미터는 객차의 인덱스 번호로 한다.
    fun getOffTrain(indCarr: Int){
        val curCounting = this.carriage[indCarr]?.curNum!! - 1
        val resCounting = this.carriage[indCarr]?.resNum!! - 1
        this.carriage[indCarr]?.curNum = curCounting
        this.carriage[indCarr]?.resNum = resCounting
    } // 하차 - 예매와 승차 인원이 증가한다.

    // 파라미터는 업데이트 해야할 기차_객체(TrainInfo)이다.
    fun checkUpdate(thisTrain: TrainInfo): Boolean{
        if(!((this.UNIQUE_NUMBER == thisTrain.UNIQUE_NUMBER) &&
             (this.TYPE_ID == thisTrain.TYPE_ID))){
            return false
        } // 업데이트 해야할 기차 정보가 잘못되었을 때

        this.currentStation = thisTrain.currentStation
        for(item in 0 until thisTrain.carriage.size){
            this.carriage[item]?.curNum = thisTrain.carriage[item]?.curNum!!
            this.carriage[item]?.resNum = thisTrain.carriage[item]?.resNum!!
        }
        return true
    } // 기차 객체 업데이트
    /* class method declaration end */
}