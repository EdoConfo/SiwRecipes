/* Dati iniziali salvati automaticamente il Gio 25 Dic 2025 11:53:33 CET */

-- Dump dati per la tabella: users
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 1, 'edo.conforti@stud.uniroma3.it', 'Edoardo', 'Conforti');

-- Dump dati per la tabella: credentials
INSERT INTO public.credentials (id, user_id, password, role, username) VALUES (1, 1, '$2a$10$Afbg0slzv5j1inrULsS9oeOhds6wL057yjbE26w3wiHJQspZO8FZ.', 'DEFAULT', 'EdoConfo');

-- Dump dati per la tabella: recipe
INSERT INTO public.recipe (difficulty, prep_time, author_id, creation_date, id, description, procedure, image, title) VALUES (2, 2, 1, '2025-12-25 01:22:14.539085', 5, 'ssss', '', 'Conforti-Edoardo-pizza-margherita-1766656335088.jpg', 'Pizza Margherita');

-- Dump dati per la tabella: ingredient

-- Dump dati per la tabella: review


-- Reset delle sequenze per la generazione degli ID
SELECT setval('credentials_seq', (SELECT MAX(id) FROM public.credentials));
SELECT setval('users_seq', (SELECT MAX(id) FROM public.users));
SELECT setval('recipe_seq', (SELECT MAX(id) FROM public.recipe));
SELECT setval('ingredient_seq', (SELECT MAX(id) FROM public.ingredient));
SELECT setval('review_seq', (SELECT MAX(id) FROM public.review));
