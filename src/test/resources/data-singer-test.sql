-- Inserting a test singers
INSERT INTO "SINGER" (singer_id, firstname, lastname) VALUES (1, 'Kayne', 'West');
INSERT INTO "SINGER" (singer_id, firstname, lastname) VALUES (2, 'Travis', 'Scott');

-- Inserting songs for the test
INSERT INTO "SONG" (song_id, title, release_year, rating) VALUES (1, 'Song 1', 2020, 5);
INSERT INTO "SONG" (song_id, title, release_year, rating) VALUES (2, 'Song 2', 2020, 4);
INSERT INTO "SONG" (song_id, title, release_year, rating) VALUES (3, 'Song 3', 2022, 3);
INSERT INTO "SONG" (song_id, title, release_year, rating) VALUES (4, 'Song 4', 2022, 5);

-- Inserting song for singer 1
INSERT INTO "SINGS" (singer_id, song_id) VALUES (1,1);
INSERT INTO "SINGS" (singer_id, song_id) VALUES (1,2);
INSERT INTO "SINGS" (singer_id, song_id) VALUES (1,3);
-- Inserting song for singer 2
INSERT INTO "SINGS" (singer_id, song_id) VALUES (2,4);

