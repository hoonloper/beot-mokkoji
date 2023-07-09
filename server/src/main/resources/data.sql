INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID1', 'test1', 'test1', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID2', 'test2', 'test2', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID3', 'test3', 'test3', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID4', 'test4', 'test4', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID5', 'test5', 'test5', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID6', 'test6', 'test6', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID7', 'test7', 'test7', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID8', 'test8', 'test8', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID9', 'test9', 'test9', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('UUID10', 'test10', 'test10', null);

INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (1, 'UUID1', 'UUID2', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (2, 'UUID1', 'UUID3', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (3, 'UUID1', 'UUID4', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (4, 'UUID1', 'UUID5', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (5, 'UUID1', 'UUID6', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (6, 'UUID1', 'UUID7', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (7, 'UUID1', 'UUID8', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (8, 'UUID1', 'UUID9', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (9, 'UUID2', 'UUID1', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (10, 'UUID2', 'UUID3', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (11, 'UUID2', 'UUID4', '2022-01-01');

INSERT INTO ROOMS (member_id, room_id, name) VALUES ('UUID1', 'ROOM1', '채팅방1');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('UUID2', 'ROOM1', '채팅방1');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('UUID1', 'ROOM2', '채팅방2');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('UUID3', 'ROOM2', '채팅방2');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('UUID1', 'ROOM3', '채팅방3');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('UUID5', 'ROOM3', '채팅방3');

INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID1', 'UUID1님이 채팅방에 접속했습니다.', '2023-07-07 20:54:00.883', 0);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID2', 'UUID2님이 채팅방에 접속했습니다.', '2023-07-07 20:55:00.883', 0);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID1', '안녕하세요.', '2023-07-07 20:55:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID2', '저도 안녕하세요.', '2023-07-07 20:56:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID1', '오늘 날씨 좋죠?', '2023-07-07 20:58:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID1', '정말 푸른 거 같아요.', '2023-07-07 20:59:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID2', '아녀? 흐린데요.', '2023-07-07 21:00:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID2', '비올 거 같은데 좋은 날씨로 보여요?', '2023-07-07 21:01:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('ROOM1', 'UUID1', '착가했네요..', '2023-07-07 22:36:00.883', 1);
