# 과제2. 설계

## 온라인 쇼핑몰의 주문 시스템
### 내용

- 고객은 여러 개의 주문을 할 수 있습니다.
- 주문은 하나 이상의 제품을 포함할 수 있습니다.
- 주문은 상태(예: 주문 완료, 배송 중, 취소)를 가질 수 있습니다.
- 각 주문의 제품은 주문 아이템(OrderItem)으로 관리합니다.
- 주문 완료 상태일 경우에만 주문을 취소할 수 있습니다.

### 테스트 방법 
- initDb로 데이터 미리 넣어둠 
- test.http를 사용하여 테스트 가능

### 구조
- controller
- service
- repository
- domain
- dto
- exception

### api 

- 주문 API
  - 경로 : /order
  - POST 방식
  - 파라미터 : 
    - userName(사용자 이름)
    - List<OrderItemRequest>(주문 상품)
      - itemId(상품번호)
      - stock(수량)


- 주문 조회 API 
  - 경로 : /orders
  - GET 방식
  - 파라미터 :
    - userName(사용자 이름)


- 주문상태 배송중 변경 API
  - 경로 : /order/ship
  - PUT 방식
  - 파라미터 :
    - id(주문 아이디)


- 주문상태 취소 변경 API
  - 경로 : /order/cancel
  - POST 방식
  - 파라미터 :
    - id(주문 아이디)

