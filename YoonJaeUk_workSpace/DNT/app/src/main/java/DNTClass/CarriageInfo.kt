package DNTClass

import java.io.Serializable

// writer : Yoon Jae Uk
// date : 2022.04.29 ~ 2022.05.01
// content : 객차 정보를 담고있는 클래스이다.

class CarriageInfo
    /* class, member, basic constructor(init) declaration begin */

    (val INDEX_NUM: Int, var curNum: Int, var resNum: Int, val MAX_NUM:Int): Serializable {
    // constructor(member) : interface
    val CARR_NAME: String = String.format("%d호차",INDEX_NUM+1)

    init{
        // println("$CARR_NAME 생성") // for debug
    }
    /* class, member, basic constructor(init) declaration end */

    /* class method declaration begin */
    // NOT CODE!!
    /* class method declaration end */
}