/* Dati iniziali salvati automaticamente il Gio 25 Dic 2025 01:32:24 CET */

-- Dump dati per la tabella: users
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 1, 'edo.conforti@stud.uniroma3.it', 'Edoardo', 'Conforti');

-- Dump dati per la tabella: credentials
INSERT INTO public.credentials (id, user_id, password, role, username) VALUES (1, 1, '$2a$10$Afbg0slzv5j1inrULsS9oeOhds6wL057yjbE26w3wiHJQspZO8FZ.', 'DEFAULT', 'EdoConfo');

-- Dump dati per la tabella: recipe

-- Dump dati per la tabella: ingredient

-- Dump dati per la tabella: review


-- Reset delle sequenze per la generazione degli ID
SELECT setval('credentials_seq', (SELECT MAX(id) FROM public.credentials));
SELECT setval('users_seq', (SELECT MAX(id) FROM public.users));
SELECT setval('recipe_seq', (SELECT MAX(id) FROM public.recipe));
SELECT setval('ingredient_seq', (SELECT MAX(id) FROM public.ingredient));
SELECT setval('review_seq', (SELECT MAX(id) FROM public.review));
