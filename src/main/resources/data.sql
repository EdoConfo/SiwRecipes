/* Dati iniziali salvati automaticamente il Ven 26 Dic 2025 13:28:54 CET */

-- Dump dati per la tabella: users
INSERT INTO public.users (date_of_birth, id, email, name, surname) VALUES (NULL, 1, 'edo.conforti@stud.uniroma3.it', 'Edoardo', 'Conforti');

-- Dump dati per la tabella: credentials
INSERT INTO public.credentials (id, user_id, password, role, username) VALUES (1, 1, '$2a$10$Afbg0slzv5j1inrULsS9oeOhds6wL057yjbE26w3wiHJQspZO8FZ.', 'DEFAULT', 'EdoConfo');

-- Dump dati per la tabella: recipe
INSERT INTO public.recipe (difficulty, prep_time, author_id, creation_date, id, description, procedure, category, image, title) VALUES (2, 2, 1, '2025-12-25 01:22:14.539085', 5, 'ssss', '', 'Primi Piatti', 'Conforti-Edoardo-pizza-margherita-1766656335088.jpg', 'Pizza Margherita');
INSERT INTO public.recipe (difficulty, prep_time, author_id, creation_date, id, description, procedure, category, image, title) VALUES (4, 20, 1, '2025-12-26 01:26:07.589673', 6, '', '1. ffjfj
2. ffjfjf
Cottura
3. 
4. 
5. ', 'Antipasti', 'Conforti-Edoardo-massiccio-dildo-nero-1766748163053.jpg', 'Massiccio dildo nero');

-- Dump dati per la tabella: ingredient
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (1, 5, 'Farina', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (3, 6, 'Palle', '2', 'Nazar');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (4, 6, 'Farina', '2', 'N');

-- Dump dati per la tabella: review


-- Reset delle sequenze per la generazione degli ID
SELECT setval('credentials_seq', (SELECT MAX(id) FROM public.credentials));
SELECT setval('users_seq', (SELECT MAX(id) FROM public.users));
SELECT setval('recipe_seq', (SELECT MAX(id) FROM public.recipe));
SELECT setval('ingredient_seq', (SELECT MAX(id) FROM public.ingredient));
SELECT setval('review_seq', (SELECT MAX(id) FROM public.review));
