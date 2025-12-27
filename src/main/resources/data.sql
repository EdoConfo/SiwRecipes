/* Dati iniziali salvati automaticamente il Sab 27 Dic 2025 17:57:26 CET */

-- Dump dati per la tabella: users
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 1, 'edo.conforti@stud.uniroma3.it', 'Edoardo', 'Conforti');
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 2, 'tom.tabacchini@stud.uniroma3.it', 'Tommaso', 'Tabacchini');
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 3, 'ema.orlandi@stud.uniroma3.it', 'Emanuele', 'Orlandi');
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 4, 'edo.conforti@stud.uniroma3.it', 'Edoardo', 'Conforti');
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 5, 'naz.sprynskyy@stud.uniroma3.it', 'Nazar', 'Sprynskyy');
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 6, 'dan.dilorenzo@stud.uniroma3.it', 'Daniele', 'Di Lorenzo');
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 7, 'mat.ambrosio@stud.uniroma3.it', 'Mattia', 'Ambrosio');

-- Dump dati per la tabella: credentials
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 3, 3, '$2a$10$reglyi7Y.Jz2JvEId4fTlOqlZVnPziMQBV7c9uyMVODeJ4awarJby', 'DEFAULT', 'ZiLele');
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 1, 1, '$2a$10$Afbg0slzv5j1inrULsS9oeOhds6wL057yjbE26w3wiHJQspZO8FZ.', 'DEFAULT', 'EdoConfo');
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 2, 2, '$2a$10$iqKCvVKARoOf1ooaz8y9CufRH3mM/VKsft0DovRNfl9ytukl10wa.', 'DEFAULT', 'TomTab');
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 4, 4, '$2a$10$/OISWXu9uoPdxMV/ErCnfONRM/iflrGWY4D1Psm1gXn7MGCp/o3Vm', 'ADMIN', 'admin');
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 5, 5, '$2a$10$yRn4x2QBLVYCJuczurTA8OAfVhrWP84h/khl.6iIq495QpXPyTgvu', 'DEFAULT', 'Naspry');
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 6, 6, '$2a$10$F5CK35.BHRwoBpwbY2OoMe.X57Z3tnVIW6mmnAh.RalIZk8lGscB.', 'DEFAULT', 'LeleDilo');
INSERT INTO public.credentials (enabled, id, user_id, password, role, username) VALUES (true, 7, 7, '$2a$10$fu.tfs0N2p2PYsC8L6GGKu3DI/iDrGFveDCsYsZH2dlkg/0xyKpyK', 'DEFAULT', 'Matty');

-- Dump dati per la tabella: recipe
INSERT INTO public.recipe (difficulty, prep_time, author_id, creation_date, id, description, procedure, category, image, title) VALUES (2, 1, 1, '2025-12-27 17:52:46.900578', 1, 'brreve descrizione
se vad a capo qui non vado a capo nel data.sql', '1. procedimento a passaggi
2. se vado a capo, si vede nel data.sql
posso scrivere senza numero
3. e poi ritorna alla riga successiva', 'Antipasti', 'Conforti-Edoardo-titolo-1766854366928.jpg', 'Titolo');

-- Dump dati per la tabella: ingredient
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (1, 1, 'Nome ingrediente', '200', 'g');

-- Dump dati per la tabella: review
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (3, 4, '2025-12-27 17:57:02.635844', 1, 1, 'Descrizione della recensione.
Se vado a capo dovrei andare a capo anche nel data.sql', 'Titolo della recensione');


-- Reset delle sequenze per la generazione degli ID
SELECT setval('credentials_seq', (SELECT MAX(id) FROM public.credentials));
SELECT setval('users_seq', (SELECT MAX(id) FROM public.users));
SELECT setval('recipe_seq', (SELECT MAX(id) FROM public.recipe));
SELECT setval('ingredient_seq', (SELECT MAX(id) FROM public.ingredient));
SELECT setval('review_seq', (SELECT MAX(id) FROM public.review));
