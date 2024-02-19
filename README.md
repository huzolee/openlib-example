## Leírás

* Adott egy könyveket és szerzőket kezelő alkalmazás, amiben jelenleg ezen entitásokon lehet CRUD műveleteket végezni, valamint a könyveket szerzőhöz rendelni.
* A perzisztálás egy lokális SQLite adatbázisba történik, ami előre fel van töltve rekordokkal. 

## Megoldandó feladatok:

1. Vegyél fel egy új mezőt a könyvre.
   * Year - integer típusú
2. Készíts egy új API-t, amit meghívva minden könyvhöz kérdezze le az első publikálás dátumát az OpenLibrary-ról és mentse le az <b><ins>évszámot</ins></b> a könyv entitáshoz
   * /books/update-all-with-year
   * https://openlibrary.org/works/{:workId}.json
     * https://openlibrary.org/works/OL2163638W.json
   * first_publish_date String típusú mező
     * Pl.: "January 1, 1995", "August 1992"
3. Készíts egy új API-t, ami visszaadja az összes könyvet, aminek a szerzője a paraméterben kapott országból származik és a könyv publikálási dátuma nem régebbi, mint az opcionális ("from") paraméterben kapott. Sorrendezd évszám szerint.
   * /books/query/UK
   * /books/query/UK?from=1984
