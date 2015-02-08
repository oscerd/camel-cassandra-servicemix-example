CREATE KEYSPACE IF NOT EXISTS simplex WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};
CREATE TABLE IF NOT EXISTS simplex.songs (id uuid PRIMARY KEY, title text, album text, artist text, tags set<text>, data blob);
CREATE INDEX IF NOT EXISTS album_idx ON simplex.songs(album);
CREATE INDEX IF NOT EXISTS title_idx ON simplex.songs(title);
INSERT INTO simplex.songs (id, title, album, artist, tags) VALUES (now(), 'Intolerance', 'Undertow', 'Tool',  {'1993', 'Alternative Metal'});
INSERT INTO simplex.songs (id, title, album, artist, tags) VALUES (now(), 'Prison Sex', 'Undertow', 'Tool',  {'1993', 'Alternative Metal'});
INSERT INTO simplex.songs (id, title, album, artist, tags) VALUES (now(), 'Sober', 'Undertow', 'Tool',  {'1993', 'Alternative Metal'});
