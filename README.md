# 아키텍처

![](./docs/architecture.png)

# 사용 기술
- Java17, Spring Boot 3.2
- Mongo DB
- WebSocket
- Kafka
    - kstream

# 고민한 사항
- 채팅방 WebSocket Session을 서버에서 어떻게 관리할까
    - WebSocketSession을 In-Memory에 Map 구조로 관리
    - 같은 채팅방 Session을 같은 Server에 저장
        - 장점
            - 같은 채팅방에 메세지 전송시 별도의 처리 불 필요
        - 단점
            - 서버별 트래픽 편차 발생
    - 같은 채팅방 Session을 서버들에 분산 시켜 저장
        - 장점
            - 서버 자원 고르게 사용
        - 단점
            - 같은 채팅방에 메세지 전송을 위해 서버간 이벤트 전달 필요
    - 채택
        - 같은 채팅방 Session을 서버들에 분산 시켜 저장
    - 이유
        - 메세지 송수신시 CPU를 많이 사용하기에 scale out 필요
        - 다양한 후처리는 처리량을 높이기 위해 비동기로 처리할 예정이기에 이벤트 큐를 사용하는걸 단점으로 생각하지 않음

</br></br>

- 서버의 CPU 사용을 다른 서버와 분담 하자
  - 다수의 Websocket을 이용해 메세지를 보내는 처리는 CPU 사용량이 많다
  - 메세지 큐를 이용해 금칙어 처리나, 메세지 영구 저장 같은 부수적인 비즈니스 처리를 다른 서버에서 처리한다
  - 메세지 큐 서비스로 kafka 를 활용
    - 사용 이유
      - 사용하기 익숙함
  - 카프카 컨슈머 그룹을 이용해 하나의 이벤트를 여러 서버에서 consume 하기
  - exact only 처리를 어떻게하면 구현할 수 있을 까
      - KStream

- 금칙어를 어떻게 빨리 판단 할 수 있을까
  - 문자열 길이 N
  - 사전에 있는 단어 수 M
  - vanilla
    - O(NM)
  - aho corasick algorithm
    - O(N+M)
  - message processor에서 금칙어 Trie 자료구조를 보관하고 있고, 데이터 베이스에 수정한 금칙어를 반영하고 싶다면
  - 이벤트를 전송해 데이터베이스에 금칙어를 읽어 Trie를 update한다

- tomcat은 어떻게 생겼을까
    - webflux 가 적은 리소스로 처리할 수 있다는 이야기를 보고 webflux에서 사용하는 was인 netty와 tomcat의 차이가 궁금해져서 tomcat의 소스코드를 보고 분석했다

# reference
- 카카오 엔터테인먼트, 라이브 채팅 플랫폼 구현기
  - https://kakaoentertainment-tech.tistory.com/109
