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
