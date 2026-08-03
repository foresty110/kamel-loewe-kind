INSERT INTO category (name) VALUES
  ('윤리학'), ('철학사'), ('형이상학'), ('인식론'), ('논리학'), ('정치철학')
ON CONFLICT (name) DO NOTHING;

-- 비밀번호는 모두 'Test1234!'를 BCrypt로 암호화한 값
INSERT INTO board (category_id, author, password, title, content)
SELECT (SELECT id FROM category WHERE name = '윤리학'),
       '김철수',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '칸트의 정언명령에 대하여',
       '정언명령은 결과와 무관하게 그 자체로 타당한 도덕법칙입니다. 여러분은 어떻게 생각하시나요?'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '칸트의 정언명령에 대하여');

INSERT INTO board (category_id, author, password, title, content)
SELECT (SELECT id FROM category WHERE name = '철학사'),
       '이영희',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '소크라테스의 산파술이란?',
       '소크라테스는 질문을 거듭해 상대방 스스로 자신의 무지를 깨닫게 만들었습니다. 이런 대화법이 오늘날에도 유효할까요?'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '소크라테스의 산파술이란?');

INSERT INTO board (category_id, author, password, title, content)
SELECT (SELECT id FROM category WHERE name = '형이상학'),
       '박민수',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '존재란 무엇인가',
       '하이데거는 존재와 존재자를 구분해야 한다고 말했습니다. 이 구분이 왜 중요한지 함께 이야기해봐요.'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '존재란 무엇인가');

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '칸트의 정언명령에 대하여'),
       '정다운',
       '결과를 전혀 고려하지 않는 게 오히려 비현실적이지 않을까요?'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '칸트의 정언명령에 대하여')
      AND author = '정다운'
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '칸트의 정언명령에 대하여'),
       '한지민',
       '동기의 순수성을 중시한다는 점에서 결과주의와는 확실히 다른 매력이 있네요.'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '칸트의 정언명령에 대하여')
      AND author = '한지민'
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '소크라테스의 산파술이란?'),
       '최우진',
       '스스로 깨닫게 하는 방식이라 지금 교육에도 그대로 적용될 수 있을 것 같아요.'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '소크라테스의 산파술이란?')
      AND author = '최우진'
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '소크라테스의 산파술이란?'),
       '오세영',
       '질문만으로 무지를 깨닫게 하는 게 말처럼 쉽지는 않을 것 같은데, 실제로 효과가 있었을까요?'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '소크라테스의 산파술이란?')
      AND author = '오세영'
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '존재란 무엇인가'),
       '윤서연',
       '존재와 존재자의 구분이 처음엔 어려웠는데, 읽다 보니 조금씩 이해가 되네요.'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '존재란 무엇인가')
      AND author = '윤서연'
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '존재란 무엇인가'),
       '강태민',
       '하이데거는 확실히 어렵네요. 관련해서 쉽게 설명된 책이 있으면 추천 부탁드려요.'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '존재란 무엇인가')
      AND author = '강태민'
);
