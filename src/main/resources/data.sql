INSERT INTO category (board_type, name) VALUES
  ('GENERAL', '윤리학'), ('GENERAL', '철학사'), ('GENERAL', '형이상학'), ('GENERAL', '인식론'), ('GENERAL', '논리학'), ('GENERAL', '정치철학'),
  ('NOTICE', '정기모임'), ('NOTICE', '소모임'),
  ('FEED', '전체')
ON CONFLICT (board_type, name) DO NOTHING;

-- 비밀번호는 모두 'Test1234!'를 BCrypt로 암호화한 값
INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'GENERAL',
       (SELECT id FROM category WHERE board_type = 'GENERAL' AND name = '윤리학'),
       '김철수',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '칸트의 정언명령에 대하여',
       '정언명령은 결과와 무관하게 그 자체로 타당한 도덕법칙입니다. 여러분은 어떻게 생각하시나요?'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '칸트의 정언명령에 대하여');

INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'GENERAL',
       (SELECT id FROM category WHERE board_type = 'GENERAL' AND name = '철학사'),
       '이영희',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '소크라테스의 산파술이란?',
       '소크라테스는 질문을 거듭해 상대방 스스로 자신의 무지를 깨닫게 만들었습니다. 이런 대화법이 오늘날에도 유효할까요?'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '소크라테스의 산파술이란?');

INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'GENERAL',
       (SELECT id FROM category WHERE board_type = 'GENERAL' AND name = '형이상학'),
       '박민수',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '존재란 무엇인가',
       '하이데거는 존재와 존재자를 구분해야 한다고 말했습니다. 이 구분이 왜 중요한지 함께 이야기해봐요.'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '존재란 무엇인가');

INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'NOTICE',
       (SELECT id FROM category WHERE board_type = 'NOTICE' AND name = '정기모임'),
       '운영진',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '8월 정기모임 안내',
       '이번 달은 하이데거의 존재와 시간을 함께 읽습니다. 아래 모임 정보를 확인해주세요.'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '8월 정기모임 안내');

INSERT INTO board_notice (board_id, meeting_at, location, book, publisher, page_start, page_end, fee, fee_description)
SELECT (SELECT id FROM board WHERE title = '8월 정기모임 안내'),
       '2026-08-30 19:00:00',
       '강남 스터디카페',
       '존재와 시간',
       '까치글방',
       1,
       120,
       10000,
       '스터디룸 대관료'
WHERE NOT EXISTS (
    SELECT 1 FROM board_notice
    WHERE board_id = (SELECT id FROM board WHERE title = '8월 정기모임 안내')
);

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

-- FEED 게시판(전체) 카테고리는 화면에 표시하지 않고 DB 제약(board.category_id NOT NULL)을 만족시키기 위한 내부용

INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'FEED',
       (SELECT id FROM category WHERE board_type = 'FEED' AND name = '전체'),
       '소은',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '오늘 읽은 니체 구절 공유해요',
       '차라투스트라는 이렇게 말했다 읽다가 이 구절에서 한참 멈췄어요. 춤추는 별을 낳으려면 자기 안에 혼돈을 지니고 있어야 한다는 말, 다들 어떻게 읽으셨나요. 저는 이 문장을 읽고 나서 그동안 불안이라고만 여겼던 감정들을 조금 다르게 보게 됐어요.'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '오늘 읽은 니체 구절 공유해요');

INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'FEED',
       (SELECT id FROM category WHERE board_type = 'FEED' AND name = '전체'),
       '재현',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '이번 주 모임 다들 참석하시나요?',
       '장소 예약 때문에 인원 파악이 필요해서요. 참석 여부 댓글로 남겨주시면 감사하겠습니다!'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '이번 주 모임 다들 참석하시나요?');

INSERT INTO board (board_type, category_id, author, password, title, content)
SELECT 'FEED',
       (SELECT id FROM category WHERE board_type = 'FEED' AND name = '전체'),
       '도현',
       '$2a$10$mtr9RRnZpaEYLFOwBI1E1uL14thTvqA/FIZB19DjLvEZS7bE2qCQK',
       '스터디카페에서 찍은 책상 셋업',
       '다들 어떤 환경에서 책 읽으시나요? 저는 조용한 카페보다 백색소음 있는 곳이 더 집중이 잘 되더라고요. 괜찮은 스터디카페 있으면 추천 부탁드려요.'
WHERE NOT EXISTS (SELECT 1 FROM board WHERE title = '스터디카페에서 찍은 책상 셋업');

INSERT INTO board_feed (board_id, like_count)
SELECT (SELECT id FROM board WHERE title = '오늘 읽은 니체 구절 공유해요'), 24
WHERE NOT EXISTS (
    SELECT 1 FROM board_feed
    WHERE board_id = (SELECT id FROM board WHERE title = '오늘 읽은 니체 구절 공유해요')
);

INSERT INTO board_feed (board_id, like_count)
SELECT (SELECT id FROM board WHERE title = '이번 주 모임 다들 참석하시나요?'), 8
WHERE NOT EXISTS (
    SELECT 1 FROM board_feed
    WHERE board_id = (SELECT id FROM board WHERE title = '이번 주 모임 다들 참석하시나요?')
);

INSERT INTO board_feed (board_id, like_count)
SELECT (SELECT id FROM board WHERE title = '스터디카페에서 찍은 책상 셋업'), 41
WHERE NOT EXISTS (
    SELECT 1 FROM board_feed
    WHERE board_id = (SELECT id FROM board WHERE title = '스터디카페에서 찍은 책상 셋업')
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '이번 주 모임 다들 참석하시나요?'),
       '도현',
       '저 참석할게요!'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '이번 주 모임 다들 참석하시나요?')
      AND author = '도현'
);

INSERT INTO comment (board_id, author, content)
SELECT (SELECT id FROM board WHERE title = '이번 주 모임 다들 참석하시나요?'),
       '하은',
       '이번엔 못 갈 것 같아요'
WHERE NOT EXISTS (
    SELECT 1 FROM comment
    WHERE board_id = (SELECT id FROM board WHERE title = '이번 주 모임 다들 참석하시나요?')
      AND author = '하은'
);
