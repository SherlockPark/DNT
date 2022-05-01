package DNTClass

// writer : Yoon Jae Uk
// date : 2022.04.29 ~ ?
// content : 기차 정보를 담고있는 클래스이다.


class TrainInfo
    /* class, member, basic constructor(init) declaration begin */
    (val UNIQUE_NUMBER:Int, val TYPE_ID:Int, val CARRIAGE_COUNT:Int, var currentStation:String) {
        var carriage: Array<CarriageInfo?> = arrayOfNulls(CARRIAGE_COUNT)
        val TOTAL_MAX_NUM:Int

        init{
            var totalNum: Int = 0
            for(item in 0 until CARRIAGE_COUNT){
                carriage[item] = CarriageInfo(item,0,0,10)
                totalNum += carriage[item]?.MAX_NUM!!
            }
            TOTAL_MAX_NUM = totalNum
            println("${UNIQUE_NUMBER}호 생성")
        }
    /* class, member, basic constructor(init) declaration end */

    /* class method declaration begin */
    fun getCurNumTotal(): Int {
        var sum: Int = 0
        for(item in 0 until CARRIAGE_COUNT)
            sum += carriage[item]?.curNum!!

        return sum
    } // 현 기차의 현재 인원수 총 합계 getter function
    fun getResNumTotal(): Int {
        var sum: Int = 0
        for(item in 0 until CARRIAGE_COUNT)
            sum += carriage[item]?.resNum!!

        return sum
    } // 현 기차의 예약된 인원수 총 합계 getter function
    fun getNumMessage(): String {
        val numMessage = "${this.getCurNumTotal()}/${this.getResNumTotal()}/${this.TOTAL_MAX_NUM}"
        return numMessage
    } // 기차의 현 인원수 메세지 getter function
    /* class method declaration end */
}