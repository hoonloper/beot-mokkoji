INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('b0f8df7b-837c-4b69-bd25-99e60ad60c26', '짱구', '개구쟁이', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('1852b156-c35e-43fb-8800-9c8cb37b5ed7', '맹구', '코흘리개', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('e6e2a54f-c90d-4ad1-a747-3e538074fa66', '철수', '똑똑이', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('3d2dccae-b59d-4c11-920b-4c354b77ce4a', '훈이', '순딩이', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('7e0b66b2-82aa-49d9-8d36-b3b67637daa5', '유리', '조폭마누라', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('0ca75e74-c967-45e9-afd3-dd820ed30b02', '수지', '부잣집딸', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('874d9086-c91d-404f-be74-3108e157ad7e', '나미리', '폭군', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('78344b72-20f6-48a4-923c-3e0eda961067', '봉미선', '삼겹살', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('8ee64970-36e3-4e18-a6fc-262e8956bfcf', '신형만', '발냄새', null);
INSERT INTO MEMBERS (id, name, nickname, birthday) VALUES ('97f1ac41-626d-408c-a5a7-bcb4b9ceae33', '오수', '오수생', null);

INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (1, 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '1852b156-c35e-43fb-8800-9c8cb37b5ed7', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (2, 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', 'e6e2a54f-c90d-4ad1-a747-3e538074fa66', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (3, 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '3d2dccae-b59d-4c11-920b-4c354b77ce4a', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (4, 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '0ca75e74-c967-45e9-afd3-dd820ed30b02', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (5, '0ca75e74-c967-45e9-afd3-dd820ed30b02', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (6, '78344b72-20f6-48a4-923c-3e0eda961067', '8ee64970-36e3-4e18-a6fc-262e8956bfcf', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (7, '8ee64970-36e3-4e18-a6fc-262e8956bfcf', '78344b72-20f6-48a4-923c-3e0eda961067', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (8, '97f1ac41-626d-408c-a5a7-bcb4b9ceae33', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (9, '78344b72-20f6-48a4-923c-3e0eda961067', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (10, '8ee64970-36e3-4e18-a6fc-262e8956bfcf', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '2022-01-01');
INSERT INTO BEOTS (id, from_member_id, to_member_id, created_at) VALUES (11, '874d9086-c91d-404f-be74-3108e157ad7e', '78344b72-20f6-48a4-923c-3e0eda961067', '2022-01-01');

INSERT INTO ROOMS (member_id, room_id, name) VALUES ('b0f8df7b-837c-4b69-bd25-99e60ad60c26', '09f10a65-b2ff-4390-b491-06c0436b0918', '모자');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('78344b72-20f6-48a4-923c-3e0eda961067', '09f10a65-b2ff-4390-b491-06c0436b0918', '모자');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('b0f8df7b-837c-4b69-bd25-99e60ad60c26', 'd2d9d376-a2cc-4805-9956-ad0b64f89c10', '부자');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('8ee64970-36e3-4e18-a6fc-262e8956bfcf', 'd2d9d376-a2cc-4805-9956-ad0b64f89c10', '부자');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('b0f8df7b-837c-4b69-bd25-99e60ad60c26', '0e353a9b-0e1b-4857-99f2-2939c5d9d55d', '연인');
INSERT INTO ROOMS (member_id, room_id, name) VALUES ('0ca75e74-c967-45e9-afd3-dd820ed30b02', '0e353a9b-0e1b-4857-99f2-2939c5d9d55d', '연인');

INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '개구쟁이님이 채팅방에 접속했습니다.', '2023-07-07 20:54:00.883', 0);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', '0ca75e74-c967-45e9-afd3-dd820ed30b02', '부잣집딸님이 채팅방에 접속했습니다.', '2023-07-07 20:55:00.883', 0);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '호이호이', '2023-07-07 20:55:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', '0ca75e74-c967-45e9-afd3-dd820ed30b02', '짱구 뭐해', '2023-07-07 20:56:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '나 부리부리 댄스~', '2023-07-07 20:58:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '춤추고 있어!', '2023-07-07 20:59:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', '0ca75e74-c967-45e9-afd3-dd820ed30b02', '재미있겠다! 같이 추자', '2023-07-07 21:00:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', '0ca75e74-c967-45e9-afd3-dd820ed30b02', '부리부리~~', '2023-07-07 21:01:00.883', 1);
INSERT INTO CHATS (room_id, sender_id, message, send_at, type) VALUES ('0e353a9b-0e1b-4857-99f2-2939c5d9d55d', 'b0f8df7b-837c-4b69-bd25-99e60ad60c26', '그건좀..', '2023-07-07 22:36:00.883', 1);
