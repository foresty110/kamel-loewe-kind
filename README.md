# kamel-loewe-kind

철학을 좋아하는 사람들이 모여 자유롭게 생각을 나누는 게시판, **철학 자유게시판**입니다.

화면 기획 → DB 설계 → 백엔드/프론트 구현 순서로 진행 중인 개인 학습 프로젝트입니다.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-3.x-red)

## 목차

- [ERD](#erd)
- [설계 의도](#설계-의도)

## ERD

추후 추가 예정입니다.

## 설계 의도

`schema.sql`을 작성하면서 내린 판단 중, 코드만 봐서는 이유가 드러나지 않는 것들을 정리했습니다.

| 결정 | 한 줄 요약 |
|---|---|
| 외래키에 이름을 직접 지정 | 에러 메시지·수정 시점에 어떤 제약인지 바로 알아보기 위해 |
| `updated_at`을 자동 갱신하지 않음 | 조회수 증가까지 "수정"으로 오인되는 걸 막기 위해 |
| `board → category`는 RESTRICT | 카테고리는 게시글과 독립적으로 의미 있는 데이터라서 |
| `comment/attachment → board`는 CASCADE | 게시글에 완전히 종속된 데이터라서 |

<details>
<summary><b>외래키 제약에 이름을 직접 붙인 이유</b></summary>

`fk_board_category`, `fk_comment_board`, `fk_attachment_board`처럼 모든 외래키 제약에 `fk_테이블_참조테이블` 패턴으로 이름을 직접 붙였습니다. 이름을 안 붙여도 MySQL이 알아서 이름을 지어주지만, 알아보기 어려운 자동 생성 이름 대신 의미가 드러나는 이름을 쓰면 두 가지가 편해집니다.

- 제약 위반으로 에러가 났을 때, 에러 메시지에 이 이름이 그대로 나와서 어떤 관계 때문에 실패했는지 바로 알 수 있습니다.
- 나중에 `ALTER TABLE ... DROP FOREIGN KEY fk_board_category`처럼 특정 제약만 콕 집어 수정하거나 삭제할 때, 예측 가능한 이름으로 바로 참조할 수 있습니다.

</details>

<details>
<summary><b><code>updated_at</code>을 자동으로 채우지 않은 이유</b></summary>

`updated_at`은 `ON UPDATE CURRENT_TIMESTAMP` 같은 자동 갱신을 쓰지 않고, `NULL`을 허용하는 컬럼으로만 두었습니다. 대신 게시글을 실제로 수정하는 쿼리에서만 애플리케이션이 직접 값을 채웁니다.

이유는 게시글 보기 화면에서 조회수를 올리는 동작도 `board` 테이블에 대한 UPDATE이기 때문입니다. 자동 갱신을 걸어두면 글을 조회만 해도 `updated_at`이 채워져서, "수정한 적 없는 글은 '-'로 표기한다"는 화면 요구사항과 어긋나게 됩니다. 조회수 갱신과 실제 수정을 DB가 구분하지 못하는 상황을 피하기 위해, 수정일시는 의도적으로 수동 관리합니다.

</details>

<details>
<summary><b><code>board → category</code>를 RESTRICT로 둔 이유</b></summary>

카테고리는 게시글이 없어도 그 자체로 의미가 있는 데이터입니다(분류 체계). 그래서 게시글이 참조하고 있는 카테고리를 실수로 지우는 상황을 막기 위해, 기본 동작(RESTRICT)을 그대로 두었습니다. 참조 중인 카테고리를 삭제하려 하면 DB가 거부합니다.

</details>

<details>
<summary><b><code>comment → board</code>, <code>attachment → board</code>를 CASCADE로 둔 이유</b></summary>

댓글과 첨부파일은 게시글 없이는 존재할 이유가 없는, 게시글에 완전히 종속된 데이터입니다. 게시글이 삭제되면 이 데이터들도 함께 정리되는 게 자연스러워서 `ON DELETE CASCADE`를 걸었습니다. 이렇게 하면 게시글만 지워도 댓글·첨부파일 행이 자동으로 같이 삭제되어, "게시글은 없는데 댓글만 남아있는" 고아 데이터가 생기지 않습니다.

단, CASCADE는 DB 안의 행만 정리해줄 뿐 서버에 실제로 업로드된 파일까지 지워주지는 않습니다. 첨부파일의 실제 삭제는 애플리케이션 코드에서 별도로 처리해야 합니다.

</details>
