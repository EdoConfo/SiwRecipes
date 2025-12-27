/* Dati iniziali salvati automaticamente il Sab 27 Dic 2025 18:30:00 CET */

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
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (1, 'Carbonara Romana', 'Un classico della cucina romana.
Cremosa e saporita, senza panna!', '1. Cuocere la pasta in acqua bollente salata.
2. Rosolare il guanciale in padella.
3. Sbattere i tuorli con il pecorino e pepe.
4. Scolare la pasta e mantecare fuori dal fuoco con le uova.', 'Primi Piatti', 2, 25, '2025-01-15 12:30:00', 1, 'Conforti-Edoardo-titolo-1766854366928.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (2, 'Tiramisù Classico', 'Il dolce italiano più amato nel mondo.
Perfetto per ogni occasione.', '1. Preparare il caffè e lasciarlo raffreddare.
2. Montare i tuorli con lo zucchero e il mascarpone.
3. Inzuppare i savoiardi nel caffè e creare strati alternati con la crema.
4. Spolverare con cacao amaro.', 'Dolci', 2, 40, '2025-02-10 14:15:00', 2, 'Tabacchini-Tommaso-Tiramisù-Classico-1707574500.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (3, 'Lasagna alla Bolognese', 'Ricca e sostanziosa.
Il piatto della domenica per eccellenza.', '1. Preparare il ragù alla bolognese con lunga cottura.
2. Preparare la besciamella.
3. Alternare strati di sfoglia, ragù, besciamella e parmigiano.
4. Infornare a 180 gradi per 40 minuti.', 'Primi Piatti', 3, 120, '2025-03-05 09:00:00', 3, 'Orlandi-Emanuele-Lasagna-alla-Bolognese-1709629200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (4, 'Pizza Margherita', 'La regina delle pizze.
Semplice ma insuperabile.', '1. Impastare farina, acqua, lievito e sale.
2. Lasciar lievitare per 24 ore.
3. Stendere il disco e condire con pomodoro e mozzarella.
4. Cuocere in forno al massimo della temperatura.', 'Lievitati', 3, 30, '2025-01-20 19:30:00', 5, 'Sprynskyy-Nazar-Pizza-Margherita-1705779000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (5, 'Risotto ai Funghi', 'Cremoso e profumato.
Ideale per le cene autunnali.', '1. Tostare il riso con un soffritto di cipolla.
2. Sfumare con vino bianco.
3. Aggiungere il brodo poco alla volta.
4. A metà cottura unire i funghi porcini.
5. Mantecare con burro e parmigiano.', 'Primi Piatti', 3, 45, '2025-04-12 20:00:00', 6, 'Di Lorenzo-Daniele-Risotto-ai-Funghi-1712952000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (6, 'Cotoletta alla Milanese', 'Croccante fuori, tenera dentro.
Un secondo piatto intramontabile.', '1. Battere le fettine di vitello.
2. Passarle nella farina, poi nell''uovo sbattuto, infine nel pangrattato.
3. Friggere nel burro chiarificato fino a doratura.', 'Secondi Piatti', 2, 30, '2025-02-28 13:00:00', 7, 'Ambrosio-Mattia-Cotoletta-alla-Milanese-1709125200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (7, 'Spritz Aperol', 'L''aperitivo italiano per eccellenza.
Fresco e dissetante.', '1. Riempire un calice di ghiaccio.
2. Versare 3 parti di Prosecco.
3. Aggiungere 2 parti di Aperol.
4. Completare con 1 parte di Soda e una fetta d''arancia.', 'Bevande', 1, 5, '2025-05-01 18:00:00', 1, 'Conforti-Edoardo-Spritz-Aperol-1714586400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (8, 'Insalata Caprese', 'I colori dell''Italia nel piatto.
Fresca e leggera.', '1. Tagliare i pomodori e la mozzarella a fette.
2. Disporre alternati su un piatto.
3. Condire con olio, sale, origano e basilico fresco.', 'Antipasti', 1, 10, '2025-06-15 12:00:00', 4, 'Conforti-Edoardo-Insalata-Caprese-1718452800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (9, 'Pollo al Forno con Patate', 'Il classico pranzo della domenica.
Semplice e gustoso.', '1. Tagliare le patate a spicchi.
2. Condire pollo e patate con olio, sale e rosmarino.
3. Infornare a 200 gradi per circa un''ora.', 'Secondi Piatti', 1, 70, '2025-01-10 11:00:00', 2, 'Tabacchini-Tommaso-Pollo-al-Forno-con-Patate-1704884400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (10, 'Focaccia Genovese', 'Alta, soffice e unta al punto giusto.
Perfetta per la merenda.', '1. Preparare l''impasto e stenderlo in teglia.
2. Creare i buchi con le dita.
3. Emulsionare acqua e olio e versare sopra.
4. Cospargere di sale grosso e cuocere.', 'Lievitati', 3, 180, '2025-03-20 16:00:00', 3, 'Orlandi-Emanuele-Focaccia-Genovese-1710946800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (11, 'Verdure Grigliate', 'Contorno sano e colorato.
Ottimo accompagnamento per carne e pesce.', '1. Lavare e tagliare le verdure a fette.
2. Grigliare su piastra ben calda.
3. Condire con olio, aglio e prezzemolo.', 'Contorni', 1, 20, '2025-04-05 19:00:00', 5, 'Sprynskyy-Nazar-Verdure-Grigliate-1712343600.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (12, 'Torta di Mele', 'Il dolce della nonna.
Soffice e profumato alla cannella.', '1. Sbucciare e tagliare le mele.
2. Preparare l''impasto con uova, zucchero e farina.
3. Unire le mele e versare nello stampo.
4. Cuocere in forno a 180 gradi.', 'Dolci', 2, 60, '2025-02-15 15:30:00', 6, 'Di Lorenzo-Daniele-Torta-di-Mele-1708007400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (13, 'Pasta al Pesto', 'Un primo piatto veloce e profumato.
Il sapore della Liguria.', '1. Pestare basilico, pinoli, aglio e formaggi nel mortaio (o frullare).
2. Aggiungere olio a filo.
3. Condire la pasta cotta al dente, aggiungendo un po'' di acqua di cottura.', 'Primi Piatti', 1, 15, '2025-05-10 13:15:00', 7, 'Ambrosio-Mattia-Pasta-al-Pesto-1715346900.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (14, 'Orata al Cartoccio', 'Pesce leggero e saporito.
Cottura sana che mantiene i sapori.', '1. Pulire l''orata.
2. Adagiare su carta forno con pomodorini e capperi.
3. Chiudere il cartoccio e infornare per 20 minuti.', 'Secondi Piatti', 2, 30, '2025-06-01 20:30:00', 1, 'Conforti-Edoardo-Orata-al-Cartoccio-1717273800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (15, 'Bruschetta al Pomodoro', 'Antipasto rustico e saporito.
L''inizio perfetto per una cena.', '1. Tostare le fette di pane casereccio.
2. Strofinare con aglio fresco.
3. Condire con pomodori a cubetti, olio e basilico.', 'Antipasti', 1, 10, '2025-07-01 19:00:00', 2, 'Tabacchini-Tommaso-Bruschetta-al-Pomodoro-1719860400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (16, 'Patate al Forno Croccanti', 'Il contorno che piace a tutti.
Croccanti fuori e morbide dentro.', '1. Sbollentare le patate per 5 minuti.
2. Condire con olio e rosmarino.
3. Infornare a temperatura alta finché non sono dorate.', 'Contorni', 1, 50, '2025-01-25 12:45:00', 3, 'Orlandi-Emanuele-Patate-al-Forno-Croccanti-1706186700.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (17, 'Negroni Sbagliato', 'Un cocktail nato per errore.
Meno alcolico del classico, ma delizioso.', '1. Mettere ghiaccio nel bicchiere.
2. Versare Vermouth Rosso e Campari.
3. Completare con Spumante Brut invece del Gin.', 'Bevande', 1, 5, '2025-03-15 18:30:00', 4, 'Conforti-Edoardo-Negroni-Sbagliato-1710527400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (18, 'Panzerotti Fritti', 'Specialità pugliese.
Calzoni fritti ripieni di bontà.', '1. Preparare l''impasto per pizza.
2. Stendere dei dischi piccoli.
3. Farcire con pomodoro e mozzarella.
4. Richiudere a mezzaluna e friggere in olio bollente.', 'Lievitati', 3, 90, '2025-04-20 20:00:00', 5, 'Sprynskyy-Nazar-Panzerotti-Fritti-1713643200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (19, 'Cannoli Siciliani', 'Il re della pasticceria siciliana.
Cialda croccante e cuore di ricotta.', '1. Friggere le cialde avvolte nei cilindri.
2. Setacciare la ricotta e mescolarla con lo zucchero.
3. Farcire i cannoli solo al momento di servire.', 'Dolci', 4, 60, '2025-05-25 14:00:00', 6, 'Di Lorenzo-Daniele-Cannoli-Siciliani-1716638400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (20, 'Spaghetti alle Vongole', 'Profumo di mare.
Un classico della Vigilia e dell''estate.', '1. Far aprire le vongole in padella con aglio e olio.
2. Sgusciare una parte delle vongole.
3. Scolare la pasta e saltarla nel sugo di vongole con prezzemolo.', 'Primi Piatti', 2, 20, '2025-06-30 20:00:00', 7, 'Ambrosio-Mattia-Spaghetti-alle-Vongole-1719777600.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (21, 'Carbonara Vegetariana', 'Una variante per chi non mangia carne.
Zucchine al posto del guanciale.', '1. Friggere le zucchine a cubetti.
2. Preparare la crema di uova e pecorino.
3. Condire la pasta con zucchine e crema.', 'Primi Piatti', 2, 25, '2025-02-05 12:45:00', 1, 'Conforti-Edoardo-Carbonara-Vegetariana-1707137100.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (22, 'Tiramisù alle Fragole', 'Versione estiva e fresca.
Piace molto ai bambini.', '1. Preparare uno sciroppo di fragole.
2. Fare la crema al mascarpone classica.
3. Inzuppare i savoiardi nello sciroppo e alternare con crema e fragole fresche.', 'Dolci', 2, 45, '2025-05-15 15:00:00', 2, 'Tabacchini-Tommaso-Tiramisù-alle-Fragole-1715778000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (23, 'Parmigiana di Melanzane', 'Strati di pura goduria.
Melanzane fritte, pomodoro e mozzarella.', '1. Friggere le melanzane a fette.
2. Fare un sugo di pomodoro semplice.
3. Alternare in teglia melanzane, sugo, mozzarella e parmigiano.
4. Infornare.', 'Secondi Piatti', 3, 90, '2025-08-10 12:00:00', 3, 'Orlandi-Emanuele-Parmigiana-di-Melanzane-1723284000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (24, 'Pizza Napoletana', 'Cornicione alto e impasto morbido.
La vera pizza.', '1. Impasto a lunga lievitazione.
2. Stesura a mano spingendo l''aria verso i bordi.
3. Cottura velocissima ad altissima temperatura.', 'Lievitati', 5, 45, '2025-01-28 20:00:00', 4, 'Conforti-Edoardo-Pizza-Napoletana-1706468400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (25, 'Risotto alla Milanese', 'Il risotto giallo.
Con vero zafferano in pistilli.', '1. Soffritto di cipolla e midollo.
2. Tostatura riso e sfumatura vino.
3. Aggiungere brodo e zafferano sciolto.
4. Mantecare con burro freddo.', 'Primi Piatti', 3, 40, '2025-10-05 20:30:00', 5, 'Sprynskyy-Nazar-Risotto-alla-Milanese-1728153000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (26, 'Scaloppine al Limone', 'Secondo veloce e fresco.
Carne tenerissima.', '1. Infarinare le fettine di vitello.
2. Rosolare nel burro.
3. Aggiungere succo di limone e far restringere la salsa.', 'Secondi Piatti', 1, 15, '2025-03-10 13:00:00', 6, 'Di Lorenzo-Daniele-Scaloppine-al-Limone-1710072000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (27, 'Mojito Cubano', 'Cocktail estivo per eccellenza.
Menta, lime e rum.', '1. Pestare delicatamente lime e zucchero di canna.
2. Aggiungere la menta e premere piano.
3. Ghiaccio, Rum bianco e soda.', 'Bevande', 2, 10, '2025-07-20 22:00:00', 7, 'Ambrosio-Mattia-Mojito-Cubano-1721505600.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (28, 'Insalata di Mare', 'Antipasto fresco e raffinato.
Molluschi e crostacei.', '1. Lessare separatamente polpo, calamari e gamberi.
2. Unire tutto e condire con emulsione di olio e limone.
3. Lasciar insaporire in frigo.', 'Antipasti', 3, 60, '2025-08-15 12:30:00', 1, 'Conforti-Edoardo-Insalata-di-Mare-1723717800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (29, 'Arrosto di Vitello', 'Il piatto delle feste.
Morbido e succoso.', '1. Legare la carne con spago.
2. Rosolare su tutti i lati.
3. Sfumare con vino e cuocere lentamente con brodo.', 'Secondi Piatti', 3, 120, '2025-12-25 12:00:00', 2, 'Tabacchini-Tommaso-Arrosto-di-Vitello-1735124400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (30, 'Babà al Rum', 'Dolce tipico napoletano.
Spugnoso e inzuppato.', '1. Impasto lievitato molto morbido.
2. Doppia lievitazione negli stampini.
3. Cottura e immersione nella bagna al rum.', 'Dolci', 5, 180, '2025-04-01 15:00:00', 3, 'Orlandi-Emanuele-Babà-al-Rum-1711976400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (31, 'Caponata Siciliana', 'Contorno agrodolce ricco di sapori.
Melanzane protagoniste.', '1. Friggere le melanzane.
2. Cuocere sedano, cipolla, capperi e olive.
3. Unire tutto con pomodoro, aceto e zucchero.', 'Contorni', 3, 50, '2025-09-01 19:30:00', 4, 'Conforti-Edoardo-Caponata-Siciliana-1725211800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (32, 'Pasta alla Norma', 'Omaggio a Bellini.
Pomodoro, melanzane e ricotta salata.', '1. Friggere le melanzane a cubetti.
2. Preparare un sugo di pomodoro fresco.
3. Condire la pasta e aggiungere abbondante ricotta salata.', 'Primi Piatti', 2, 30, '2025-06-20 13:00:00', 5, 'Sprynskyy-Nazar-Pasta-alla-Norma-1718881200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (33, 'Salmone al Forno', 'Secondo di pesce semplice e sano.
Ricco di Omega 3.', '1. Marinare il salmone con limone ed erbe.
2. Cuocere in forno per 15 minuti.
3. Servire con verdure.', 'Secondi Piatti', 1, 20, '2025-03-05 20:00:00', 6, 'Di Lorenzo-Daniele-Salmone-al-Forno-1709665200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (34, 'Cocktail Martini', 'L''aperitivo di James Bond.
Secco ed elegante.', '1. Raffreddare il bicchiere.
2. Mescolare Gin e Vermouth Dry nel mixing glass con ghiaccio.
3. Filtrare e decorare con oliva.', 'Bevande', 2, 5, '2025-11-10 19:00:00', 7, 'Ambrosio-Mattia-Cocktail-Martini-1731261600.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (35, 'Pane Casereccio', 'Il profumo del pane fatto in casa.
Crosta croccante.', '1. Impasto con farina, acqua, lievito madre.
2. Pieghe di rinforzo e lievitazione lunga.
3. Cottura in forno con vapore iniziale.', 'Lievitati', 4, 300, '2025-01-12 08:00:00', 1, 'Conforti-Edoardo-Pane-Casereccio-1705042800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (36, 'Crostata di Frutta', 'Base di frolla e frutta fresca.
Colorata e golosa.', '1. Cuocere il guscio di pasta frolla "alla cieca".
2. Riempire con crema pasticcera.
3. Decorare con frutta fresca di stagione e gelatina.', 'Dolci', 3, 60, '2025-05-30 16:00:00', 2, 'Tabacchini-Tommaso-Crostata-di-Frutta-1717077600.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (37, 'Amatriciana', 'Il gusto verace di Amatrice.
Guanciale, pomodoro e pecorino.', '1. Rosolare il guanciale finché diventa trasparente.
2. Aggiungere il pomodoro e cuocere.
3. Scolare i bucatini e saltare nel sugo con pecorino.', 'Primi Piatti', 2, 25, '2025-02-15 13:00:00', 3, 'Orlandi-Emanuele-Amatriciana-1708000800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (38, 'Polpette al Sugo', 'Come quelle della nonna.
Morbide e saporite.', '1. Impastare carne macinata, uova, pane ammollato e parmigiano.
2. Formare le palline.
3. Cuocere direttamente nel sugo di pomodoro a fuoco lento.', 'Secondi Piatti', 2, 45, '2025-04-10 12:30:00', 4, 'Conforti-Edoardo-Polpette-al-Sugo-1712745000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (39, 'Tortino al Cioccolato', 'Cuore caldo e fondente.
Irresistibile.', '1. Sciogliere cioccolato e burro.
2. Montare uova e zucchero, unire le farine.
3. Riempire i pirottini e congelare.
4. Cuocere 15 minuti prima di servire.', 'Dolci', 3, 25, '2025-12-01 20:45:00', 5, 'Sprynskyy-Nazar-Tortino-al-Cioccolato-1733082300.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (40, 'Brioche col Tuppo', 'La colazione siciliana.
Da inzuppare nella granita.', '1. Impasto ricco con uova e burro.
2. Formare le palline grandi e sovrapporre quelle piccole (il tuppo).
3. Spennellare con uovo e cuocere.', 'Lievitati', 4, 180, '2025-07-15 09:00:00', 6, 'Di Lorenzo-Daniele-Brioche-col-Tuppo-1721026800.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (41, 'Zucchine Trifolate', 'Contorno semplice e versatile.
Si abbina a tutto.', '1. Tagliare le zucchine a rondelle o cubetti.
2. Saltare in padella con olio e aglio.
3. Aggiungere prezzemolo a fine cottura.', 'Contorni', 1, 15, '2025-06-05 19:45:00', 7, 'Ambrosio-Mattia-Zucchine-Trifolate-1717609500.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (42, 'Bellini', 'Cocktail veneziano.
Polpa di pesca bianca e prosecco.', '1. Frullare le pesche bianche e filtrare la polpa.
2. Mettere nel flûte.
3. Versare delicatamente il Prosecco freddo.', 'Bevande', 1, 10, '2025-08-20 18:00:00', 1, 'Conforti-Edoardo-Bellini-1724169600.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (43, 'Olive Ascolane', 'L''antipasto marchigiano famoso ovunque.
Laborioso ma ne vale la pena.', '1. Denocciolare le olive verdi giganti a spirale.
2. Preparare il ripieno di carne mista e spezie.
3. Farcire, impanare e friggere.', 'Antipasti', 5, 120, '2025-09-10 12:00:00', 2, 'Tabacchini-Tommaso-Olive-Ascolane-1725962400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (44, 'Gnocchi alla Sorrentina', 'Filanti e gustosi.
Pomodoro, mozzarella e basilico.', '1. Preparare gli gnocchi di patate.
2. Cuocere e condire con sugo di pomodoro.
3. Mettere in teglia con mozzarella e gratinare.', 'Primi Piatti', 2, 45, '2025-05-01 13:00:00', 3, 'Orlandi-Emanuele-Gnocchi-alla-Sorrentina-1714561200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (45, 'Tagliata di Manzo', 'Per gli amanti della carne al sangue.
Rucola e grana per completare.', '1. Cuocere il controfiletto intero su piastra rovente.
2. Lasciar riposare la carne.
3. Tagliare a fette e servire su letto di rucola.', 'Secondi Piatti', 2, 20, '2025-10-20 20:00:00', 4, 'Conforti-Edoardo-Tagliata-di-Manzo-1729447200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (46, 'Panna Cotta ai Frutti di Bosco', 'Dolce al cucchiaio delicato.
Facilissimo da preparare.', '1. Scaldare panna, latte e zucchero.
2. Aggiungere la colla di pesce ammollata.
3. Versare negli stampini e raffreddare.
4. Servire con coulis di frutti di bosco.', 'Dolci', 1, 15, '2025-04-25 14:00:00', 5, 'Sprynskyy-Nazar-Panna-Cotta-ai-Frutti-di-Bosco-1714046400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (47, 'Danubio Salato', 'Tante palline di brioche farcite.
Ideale per le feste.', '1. Preparare impasto brioche salato.
2. Farcire ogni pallina con salumi e formaggi.
3. Disporre vicine in teglia tonda e cuocere.', 'Lievitati', 3, 120, '2025-11-01 16:00:00', 6, 'Di Lorenzo-Daniele-Danubio-Salato-1730473200.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (48, 'Peperonata', 'Contorno ricco e saporito.
Ottimo caldo o freddo.', '1. Tagliare peperoni, cipolle e pomodori.
2. Cuocere a fuoco lento con coperchio.
3. Aggiungere aceto a fine cottura se piace.', 'Contorni', 1, 40, '2025-07-05 19:30:00', 7, 'Ambrosio-Mattia-Peperonata-1720197000.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (49, 'Gin Tonic', 'Il long drink più famoso.
Ginepro e bollicine.', '1. Riempire il bicchiere di ghiaccio.
2. Versare il Gin.
3. Colmare con Acqua Tonica e una scorza di limone.', 'Bevande', 1, 5, '2025-06-10 22:30:00', 1, 'Conforti-Edoardo-Gin-Tonic-1718051400.jpg');
INSERT INTO public.recipe (id, title, description, procedure, category, difficulty, prep_time, creation_date, author_id, image) VALUES (50, 'Prosciutto e Melone', 'L''antipasto estivo per definizione.
Dolce e salato insieme.', '1. Pulire il melone e tagliarlo a fette.
2. Adagiare sopra il prosciutto crudo dolce.
3. Servire freddo.', 'Antipasti', 1, 10, '2025-07-25 12:15:00', 2, 'Tabacchini-Tommaso-Prosciutto-e-Melone-1721902500.jpg');

-- Dump dati per la tabella: ingredient
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (1, 1, 'Spaghetti', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (2, 1, 'Guanciale', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (3, 1, 'Pecorino Romano', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (4, 1, 'Tuorli', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (5, 1, 'Pepe Nero', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (6, 2, 'Mascarpone', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (7, 2, 'Savoiardi', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (8, 2, 'Caffè', '300', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (9, 2, 'Zucchero', '120', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (10, 2, 'Uova', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (11, 2, 'Cacao Amaro', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (12, 3, 'Sfoglia Lasagna', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (13, 3, 'Ragù alla Bolognese', '600', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (14, 3, 'Besciamella', '500', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (15, 3, 'Parmigiano Reggiano', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (16, 4, 'Farina 00', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (17, 4, 'Acqua', '300', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (18, 4, 'Lievito di Birra', '3', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (19, 4, 'Pomodoro San Marzano', '400', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (20, 4, 'Mozzarella Fiordilatte', '250', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (21, 4, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (22, 5, 'Riso Carnaroli', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (23, 5, 'Funghi Porcini', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (24, 5, 'Brodo Vegetale', '1', 'l');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (25, 5, 'Burro', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (26, 5, 'Cipolla', '1', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (27, 6, 'Cotoletta di Vitello', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (28, 6, 'Uova', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (29, 6, 'Pangrattato', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (30, 6, 'Burro Chiarificato', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (31, 7, 'Prosecco', '90', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (32, 7, 'Aperol', '60', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (33, 7, 'Soda', '30', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (34, 7, 'Arancia', '1', 'fetta');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (35, 8, 'Pomodoro Ramato', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (36, 8, 'Mozzarella di Bufala', '250', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (37, 8, 'Basilico', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (38, 8, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (39, 9, 'Pollo intero', '1', 'kg');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (40, 9, 'Patate', '1', 'kg');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (41, 9, 'Rosmarino', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (42, 9, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (43, 10, 'Farina 0', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (44, 10, 'Acqua', '350', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (45, 10, 'Lievito di Birra', '7', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (46, 10, 'Olio EVO', '50', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (47, 10, 'Sale Grosso', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (48, 11, 'Zucchine', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (49, 11, 'Melanzane', '1', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (50, 11, 'Peperoni', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (51, 11, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (52, 12, 'Mele', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (53, 12, 'Farina 00', '250', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (54, 12, 'Zucchero', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (55, 12, 'Uova', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (56, 12, 'Lievito per Dolci', '1', 'bustina');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (57, 13, 'Trofie', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (58, 13, 'Basilico', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (59, 13, 'Pinoli', '15', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (60, 13, 'Parmigiano Reggiano', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (61, 13, 'Pecorino Sardo', '20', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (62, 13, 'Olio EVO', '100', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (63, 14, 'Orata', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (64, 14, 'Pomodorini', '10', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (65, 14, 'Capperi', '1', 'cucchiaio');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (66, 14, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (67, 15, 'Pane Casereccio', '4', 'fette');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (68, 15, 'Pomodori', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (69, 15, 'Aglio', '1', 'spicchio');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (70, 15, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (71, 16, 'Patate', '1', 'kg');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (72, 16, 'Rosmarino', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (73, 16, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (74, 17, 'Spumante Brut', '1', 'parte');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (75, 17, 'Vermouth Rosso', '1', 'parte');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (76, 17, 'Campari', '1', 'parte');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (77, 17, 'Arancia', '1', 'fetta');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (78, 18, 'Pasta per Pizza', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (79, 18, 'Mozzarella', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (80, 18, 'Pomodoro', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (81, 18, 'Olio di Semi', '1', 'l');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (82, 19, 'Cialde Cannoli', '6', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (83, 19, 'Ricotta di Pecora', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (84, 19, 'Zucchero a Velo', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (85, 19, 'Gocce di Cioccolato', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (86, 20, 'Spaghetti', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (87, 20, 'Vongole Veraci', '1', 'kg');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (88, 20, 'Aglio', '2', 'spicchi');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (89, 20, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (90, 21, 'Spaghetti', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (91, 21, 'Zucchine', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (92, 21, 'Uova', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (93, 21, 'Pecorino Romano', '80', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (94, 22, 'Savoiardi', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (95, 22, 'Fragole', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (96, 22, 'Mascarpone', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (97, 22, 'Zucchero', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (98, 23, 'Melanzane', '1', 'kg');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (99, 23, 'Passata di Pomodoro', '700', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (100, 23, 'Mozzarella', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (101, 23, 'Parmigiano Reggiano', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (102, 24, 'Farina 00', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (103, 24, 'Acqua', '300', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (104, 24, 'Lievito', '2', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (105, 24, 'Pomodoro San Marzano', '400', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (106, 24, 'Mozzarella di Bufala', '250', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (107, 25, 'Riso Carnaroli', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (108, 25, 'Zafferano', '1', 'bustina');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (109, 25, 'Brodo di Carne', '1', 'l');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (110, 25, 'Midollo di Bue', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (111, 26, 'Fettine di Vitello', '400', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (112, 26, 'Limone', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (113, 26, 'Farina', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (114, 26, 'Burro', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (115, 27, 'Rum Bianco', '50', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (116, 27, 'Lime', '1/2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (117, 27, 'Zucchero di Canna', '2', 'cucchiaini');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (118, 27, 'Menta', '10', 'foglie');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (119, 27, 'Soda', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (120, 28, 'Polpo', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (121, 28, 'Calamari', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (122, 28, 'Gamberi', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (123, 28, 'Limone', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (124, 28, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (125, 29, 'Arrosto di Vitello', '800', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (126, 29, 'Vino Bianco', '1', 'bicchiere');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (127, 29, 'Brodo', '500', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (128, 29, 'Rosmarino', '1', 'rametto');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (129, 30, 'Farina Manitoba', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (130, 30, 'Uova', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (131, 30, 'Burro', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (132, 30, 'Rum', '200', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (133, 30, 'Zucchero', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (134, 31, 'Melanzane', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (135, 31, 'Sedano', '2', 'gambi');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (136, 31, 'Cipolla', '1', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (137, 31, 'Capperi', '1', 'cucchiaio');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (138, 31, 'Aceto', '1/2', 'bicchiere');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (139, 32, 'Pasta Corta', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (140, 32, 'Melanzane', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (141, 32, 'Pomodoro', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (142, 32, 'Ricotta Salata', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (143, 33, 'Trancio di Salmone', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (144, 33, 'Limone', '1', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (145, 33, 'Erbe Aromatiche', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (146, 33, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (147, 34, 'Gin', '60', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (148, 34, 'Vermouth Dry', '10', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (149, 34, 'Oliva Verde', '1', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (150, 35, 'Farina Tipo 1', '1', 'kg');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (151, 35, 'Acqua', '700', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (152, 35, 'Lievito Madre', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (153, 35, 'Sale', '20', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (154, 36, 'Pasta Frolla', '1', 'rotolo');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (155, 36, 'Crema Pasticcera', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (156, 36, 'Frutta Fresca', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (157, 36, 'Gelatina', '1', 'bustina');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (158, 37, 'Bucatini', '320', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (159, 37, 'Guanciale', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (160, 37, 'Pomodoro Pelato', '400', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (161, 37, 'Pecorino Romano', '80', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (162, 38, 'Macinato Misto', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (163, 38, 'Uova', '1', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (164, 38, 'Pane', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (165, 38, 'Passata di Pomodoro', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (166, 39, 'Cioccolato Fondente', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (167, 39, 'Burro', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (168, 39, 'Zucchero', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (169, 39, 'Uova', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (170, 40, 'Farina Manitoba', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (171, 40, 'Uova', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (172, 40, 'Burro', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (173, 40, 'Latte', '100', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (174, 40, 'Zucchero', '80', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (175, 41, 'Zucchine', '4', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (176, 41, 'Aglio', '1', 'spicchio');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (177, 41, 'Prezzemolo', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (178, 41, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (179, 42, 'Polpa di Pesca', '1', 'parte');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (180, 42, 'Prosecco', '2', 'parti');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (181, 43, 'Olive Verdi Giganti', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (182, 43, 'Carne Mista', '300', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (183, 43, 'Uova', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (184, 43, 'Pangrattato', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (185, 44, 'Gnocchi di Patate', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (186, 44, 'Passata di Pomodoro', '400', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (187, 44, 'Mozzarella', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (188, 44, 'Basilico', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (189, 45, 'Controfiletto Manzo', '400', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (190, 45, 'Rucola', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (191, 45, 'Grana Padano', '50', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (192, 45, 'Olio EVO', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (193, 46, 'Panna Fresca', '500', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (194, 46, 'Latte Intero', '100', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (195, 46, 'Zucchero', '100', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (196, 46, 'Colla di Pesce', '10', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (197, 47, 'Farina 0', '500', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (198, 47, 'Latte', '250', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (199, 47, 'Olio di Semi', '50', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (200, 47, 'Salumi Misti', '150', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (201, 48, 'Peperoni Misti', '3', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (202, 48, 'Cipolle', '2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (203, 48, 'Pomodori Pelati', '200', 'g');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (204, 49, 'Gin', '50', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (205, 49, 'Acqua Tonica', '150', 'ml');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (206, 49, 'Limone', '1', 'fetta');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (207, 49, 'Ghiaccio', 'q.b.', '');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (208, 50, 'Melone', '1/2', 'n');
INSERT INTO public.ingredient (id, recipe_id, name, quantity, unit_of_measure) VALUES (209, 50, 'Prosciutto Crudo', '150', 'g');

-- Dump dati per la tabella: review
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-01-16 14:00:00', 1, 1, 'Davvero ottima, la vera carbonara!', 'Eccellente');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 5, '2025-02-11 15:30:00', 2, 2, 'Buono ma un po'' troppo dolce per i miei gusti.', 'Buono');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 6, '2025-03-06 10:00:00', 3, 3, 'Mamma mia che bontà!', 'Spettacolare');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 1, '2025-01-21 21:00:00', 4, 4, 'La pizza fatta in casa migliore di sempre.', 'Top');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (3, 2, '2025-04-13 21:00:00', 5, 5, 'Il riso è rimasto un po'' duro.', 'Discreto');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 3, '2025-03-01 14:00:00', 6, 6, 'Perfetta panatura croccante.', 'Ottima');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-05-02 19:00:00', 7, 7, 'Rinfrescante e bilanciato.', 'Perfetto');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 5, '2025-06-16 13:00:00', 8, 8, 'Semplice e veloce, ingredienti freschi.', 'Fresco');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 6, '2025-01-11 12:30:00', 9, 9, 'Pollo tenerissimo e patate saporite.', 'Classico');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 7, '2025-03-21 17:00:00', 10, 10, 'Buona, ma ci vuole pazienza per la lievitazione.', 'Gustosa');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 1, '2025-04-06 20:00:00', 11, 11, 'Contorno leggero e sano.', 'Bene');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 2, '2025-02-16 16:30:00', 12, 12, 'Profumo incredibile in tutta la casa.', 'Deliziosa');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 3, '2025-05-11 14:15:00', 13, 13, 'Il pesto fatto in casa è un''altra cosa.', 'Verde');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-06-02 21:30:00', 14, 14, 'Pesce cotto alla perfezione.', 'Sano');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 5, '2025-07-02 20:00:00', 15, 15, 'L''antipasto estivo migliore.', 'Estate');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 6, '2025-01-26 13:45:00', 16, 16, 'Davvero croccanti come promesso.', 'Crock');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 7, '2025-03-16 19:30:00', 17, 17, 'Meglio del classico Negroni!', 'Sorpresa');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 1, '2025-04-21 21:00:00', 18, 18, 'Fritti benissimo, non unti.', 'Gnam');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 2, '2025-05-26 15:00:00', 19, 19, 'Difficili da fare ma ne vale la pena.', 'Sicilia');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 3, '2025-07-01 21:00:00', 20, 20, 'Sapore di mare autentico.', 'Mare');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 4, '2025-02-06 13:45:00', 21, 21, 'Buona alternativa alla carne.', 'Veggie');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 5, '2025-05-16 16:00:00', 22, 22, 'Fresco e goloso.', 'Primavera');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 6, '2025-08-11 13:00:00', 23, 23, 'Un piatto unico che sazia e soddisfa.', 'Ricco');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 7, '2025-01-29 21:00:00', 24, 24, 'Impasto leggerissimo.', 'Napoli');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 1, '2025-10-06 21:30:00', 25, 25, 'Giallo intenso e sapore unico.', 'Oro');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 2, '2025-03-11 14:00:00', 26, 26, 'Veloce e saporito.', 'Citrus');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 3, '2025-07-21 23:00:00', 27, 27, 'Mi sento ai Caraibi.', 'Party');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-08-16 13:30:00', 28, 28, 'Molluschi tenerissimi.', 'Tenero');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 5, '2025-12-26 13:00:00', 29, 29, 'Il pranzo di Natale è salvo!', 'Natale');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 6, '2025-04-02 16:00:00', 30, 30, 'Ci vuole molta pazienza ma risultato top.', 'Lungo');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 7, '2025-09-02 20:30:00', 31, 31, 'Agrodolce bilanciato perfettamente.', 'Siculo');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 1, '2025-06-21 14:00:00', 32, 32, 'La ricotta salata fa la differenza.', 'Catania');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 2, '2025-03-06 21:00:00', 33, 33, 'Salmone rimasto morbido.', 'Omega3');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 3, '2025-11-11 20:00:00', 34, 34, 'Molto secco, per veri intenditori.', 'Strong');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-01-13 09:00:00', 35, 35, 'Pane caldo a colazione, impagabile.', 'Fragranza');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 5, '2025-05-31 17:00:00', 36, 36, 'Bellissima da vedere e buona da mangiare.', 'Colori');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 6, '2025-02-16 14:00:00', 37, 37, 'Sapore deciso, ottima.', 'Deciso');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 7, '2025-04-11 13:30:00', 38, 38, 'Morbide e succose.', 'Comfort');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 1, '2025-12-02 21:45:00', 39, 39, 'Cuore liquido perfetto!', 'Fondente');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 2, '2025-07-16 10:00:00', 40, 40, 'Con la granita è la morte sua.', 'Colazione');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 3, '2025-06-06 20:45:00', 41, 41, 'Contorno veloce che salva la cena.', 'Easy');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-08-21 19:00:00', 42, 42, 'Pesche fresche si sentono.', 'Frizzy');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 5, '2025-09-11 13:00:00', 43, 43, 'Un lavoro immane ma che soddisfazione.', 'Impresa');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 6, '2025-05-02 14:00:00', 44, 44, 'Filante e gratinata bene.', 'Filante');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 7, '2025-10-21 21:00:00', 45, 45, 'Cottura al sangue perfetta.', 'Carne');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (4, 1, '2025-04-26 15:00:00', 46, 46, 'Tremolante al punto giusto.', 'Soft');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 2, '2025-11-02 17:00:00', 47, 47, 'Ogni pallina una sorpresa.', 'Festa');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 3, '2025-07-06 20:30:00', 48, 48, 'Saporita, da fare la scarpetta.', 'Peppers');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 4, '2025-06-11 23:30:00', 49, 49, 'Il classico che non tramonta mai.', 'GinGin');
INSERT INTO public.review (rating, author_id, creation_date, id, recipe_id, text, title) VALUES (5, 5, '2025-07-26 13:15:00', 50, 50, 'Abbinamento sempre vincente.', 'Summer');


-- Reset delle sequenze per la generazione degli ID
SELECT setval('credentials_seq', (SELECT MAX(id) FROM public.credentials));
SELECT setval('users_seq', (SELECT MAX(id) FROM public.users));
SELECT setval('recipe_seq', (SELECT MAX(id) FROM public.recipe));
SELECT setval('ingredient_seq', (SELECT MAX(id) FROM public.ingredient));
SELECT setval('review_seq', (SELECT MAX(id) FROM public.review));