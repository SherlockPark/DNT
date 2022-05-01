package DNTClass

// writer : Yoon Jae Uk
// date : 2022.04.29 ~ ?
// content : 객차 정보를 담고있는 클래스이다.

class CarriageInfo
    /* class, member, basic constructor(init) declaration begin */
    (val INDEX_NUM: Int, var curNum: Int, var resNum: Int, val MAX_NUM:Int) {
    val CARR_NAME: String = String.format("%d호차",INDEX_NUM+1)

    init{
        println("$CARR_NAME 생성")
    }
    /* class, member, basic constructor(init) declaration end */

    /* class method declaration begin */
    /* class method declaration end */
}