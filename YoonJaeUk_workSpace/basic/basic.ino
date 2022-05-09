/*
2022.04.28
블루투스 테스트 - Livor 1.8v : line ending없음 으로동작
특이사항 : 보드레이트 9600에서 동작 / 115200에서는 동작하지 않음

*/
#include <SoftwareSerial.h> //시리얼 통신 라이브러리 호출
//블루투스 연결하기 (기본)
//#define blueTx 12   //Tx(보내는 핀 설정)
//#define blueRx 13  //Rx (받는 핀 설정)
int blueTx=12;
int blueRx=13;
 
SoftwareSerial btSerial(blueTx, blueRx);  //시리얼 통신을 위한 객체 선언
/* 기차 정보 선언 begin */
typedef struct {
  int unique_number;
  int type_id;
  int carriage_count;
  char* cur_station;
  char* next_tation;
  int carriag_max_num;
} train;
train myTrain;
/* 기차 정보 선언 end */

void setup() {
  Serial.begin(9600);    // 시리얼 모니터
  btSerial.begin(9600); // 블루투스 시리얼
  myTrain.unique_number = 1880;
  myTrain.type_id = 3;
  myTrain.carriage_count = 10;
  myTrain.cur_station = "대전역";
  myTrain.next_station = "천안역";
}

void loop() {
 if (btSerial.available())
 {
  Serial.write(btSerial.read()); //블루투스측 내용을 시리얼 모니터에 출력
 }
 if (Serial.available())
 {
  btSerial.write(Serial.read());  //시리얼 모니터 내용을 블루투스 측에 써라.
  if(!Serial.available())
    btSerial.println("");
 }
}
