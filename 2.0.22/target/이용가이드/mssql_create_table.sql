-- APP_SEND_DATA 테이블 생성
CREATE TABLE APP_SEND_DATA
(
    MSG_SEQ IDENTITY(1,1), -- 메시지 순번
    REQ_SEND_DATE CHAR(14) NOT NULL, -- 메시지 발송 일시
    PACK_UNIQUEKEY INT, -- APP_SEND_CONTENTS의 PK키 
    PHONE_NUM VARCHAR(12) NOT NULL, -- 수신자 전화번호
    NURI_MSG_SEQ INT, -- 문자로 전환될 시 누리 메시지 순번
    USER_SEQ INT, -- 유저가 임의로 사용 가능한 순번
    CUR_STATE INT DEFAULT 0, -- 메시지 상태
    APP_GUBUN VARCHAR(8) NOT NULL, -- 앱구분(카카오, 네이버)
    CALL_BACK VARCHAR(12) NOT NULL, -- 발신자 전화번호
    SUB_ID VARCHAR(30), -- 서브 아이디(협의된 기관만 사용)
    GAON_MSG_TYPE CHAR(1) NOT NULL, -- 메시지 타입(S: 단문 전용, L: 단문, 장문, 단문+버튼, 장문+버튼)
    SMS_MSG_DATA VARCHAR(135), -- 메시지 타입이 S인 경우 메시지 내용
    TEMPLATE_CODE VARCHAR(30), -- 템플릿 코드
    RSLT_CODE_APP CHAR(4), -- 메시지 전송 결과
    MODULE_SEND_TIME CHAR(14), -- 가온에서 서버로 발송한 시간
    SVR_RECV_TIME CHAR(14), -- 가온에서 발송한 데이터를 서버에서 수신한 시간
    APP_SEND_TIME CHAR(14), -- 서버에서 각 플랫폼 으로 발송한 시간 현재 갱신 안됨
    APP_RECV_TIME CHAR(14), -- 서버에서 각 플랫폼으로부터 결과 데이터를 받은 시간
    SERIAL_NUMBER VARCHAR(39), -- 가온에서 서버로 발송시 생성한 시리얼 넘버
    SEND_PACK_UKEY INT, -- 사용하지 않음
    PRIMARY KEY (MSG_SEQ)
)

-- APP_SEND_CONTENTS 테이블 생성
CREATE TABLE APP_SEND_CONTENTS
(
    REQ_SEND_DATE CHAR(14),  -- 메시지 발송 일시
    PACK_UNIQUEKEY IDENTITY(1,1) NOT NULL,  -- 순번
    MSG_SUBJECT VARCHAR(50) ,  -- LMS문자 전환시 문자 제목
    MSG_DATA VARCHAR(3000) NOT NULL,  -- 메시지 내용
    MSG_TYPE CHAR(2) NOT NULL, -- 메시지 타입 (AT로 고정)
    HEADER VARCHAR(32) , -- 이미지 알림톡에서 필요하나 현재 지원하지 않음
    BTN_CNT INT DEFAULT 0 , -- 버튼 개수 (현재 1개만 지원)
    ATTACHMENT VARCHAR(5000) , -- 버튼 정보 JSON 형식 
    LINK_CNT INT DEFAULT 0 , -- 바로연결 버튼 개수 (현재 지원하지 않음)
    SUPPLEMENT VARCHAR(5000) , -- 바로연결 버튼 정보 (현재 지원하지 않음)
    PHONE_CNT INT DEFAULT 0 NOT NULL, -- 전화번호 개수 (현재 사용 하지 않음)
    CUR_STATE INT DEFAULT 0, -- 메시지 상태 (현재 사용하지 않음)
    PRIMARY KEY CLUSTERED (PACK_UNIQUEKEY)
)

-- TEMPLATE_CODE 테이블 생성
CREATE TABLE TEMPLATE_CODE
(
    APP_GUBUN VARCHAR(8) NOT NULL, -- 앱구분(KAKAO, NAVER)
    TEMPLATE_CODE VARCHAR(30) NOT NULL, -- 템플릿 코드
    HEAD VARCHAR(500) NOT NULL, -- 머릿말
    FOOT VARCHAR(500), -- 꼬릿말 (사용하지 않음, 호환성을 위해 남겨둠)
    TITLE VARCHAR(100), -- 타이틀 (사용하지 않음, 호환성을 위해 남겨둠)
    USE_BUTTON    CHAR(1) DEFAULT 'N', -- 템플릿에 버튼 존재 여부
    PRIMARY KEY CLUSTERED (APP_GUBUN, TEMPLATE_CODE)
)