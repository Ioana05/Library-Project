# Gestionarea unei librarii - Etapa 1, Proiect Java
## <ul>Tipurile de obiecte folosite sunt:<lul>
<li>Book</li>
<li>Reader</li>
<li>Borrow</li>
<li>Employee</li>
<li>Event</li>
<li> Library </li>
<li>Librarian (sublasa a clasei Employee)</li>
<li>LibraryDirector (subclasa a clasei Employee)</li>
<li>ReadWrite (interfarta folosita pentru citire in acest moment)</li>
<li> Menu </li>

## Interogari disponibile:
Dupa rulare, vor fi necesare cateva date pentru instantierea clasei biblioteca, dar si a unui obiect de tip director.
In prima parte a meniului utilizatorul va avea obtiunea sa selecteze tipul de utilizator: Librarian sau Librarian Director(am folosit doar aceste doua optiuni intrucat am gandit aplicatia ca pe una folosita in bibliotecile fizice). Pentru optiunea Library Director, utilizatorul va trebui sa introduca o parola :"IAMTHEBOSS". Astfel, in functie de utilizator, vor exista interogari diferite(cu exceptia celor care tin de evenimente).
### Interogari disponibile pentru bibliotecar:
<li> Adaugarea unui nou eveniment</li>
<li> Vizualizarea evenimentelor viitoare </li>
<li> Stergerea unui eveniment viitor</li>
<li> Cautarea unei carti dupa nume </li>
<li> Cautarea unei carti dupa autor </li>
<li> Cautarea unui cititor </li>
<li> Vizualizarea numarului de carti disponibile cu un nume dat </li>
<li> Realizarea unui retur </li>
<li> Cautarea termenului limita a unui imprumut stiind numele cartii si ID ul cititorului</li>
<li> Vizualizarea de carti imprumutate de un cititor</li>
<li> Adaugarea unei noi carti in biblioByteMeUpByteMeUpteca </li>
<li> Crearea unui abonament pentru un cititor </li>
<li> Crearea unui nou imprumut </li>

### Interogari disponibile pentru directorul bibliotecii:
<li> Adaugarea unui eveniment </li>
<li> Vizualizarea evenimentelor urmatoare</li>
<li> Stergerea unui eveniment din viitor </li>
<li> Vizualizarea listei de bibliotecari</li>
<li> Adaugarea unui nou bibliotecar </li>
<li> Stergerea unui bibliotecar din lista </li>


Pentru gestionarea obiectelor definite anterior am folosit liste si un set, ca atribut al obiectului Cititor, in care tin o arhiva a cartilor imprumutate de acesta. Lista sortata apare in contextul metodei in care poti vizualiza cartile imprumutate de un cititor, acestea fiind afisate in ordine crescatoare.