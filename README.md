# Gestionarea unei librarii - Etapa 1, Proiect Java
## <ul>Tipurile de obiecte folosite sunt:<lul>
<li>model.Book</li>
<li>model.Reader</li>
<li>model.Borrow</li>
<li>model.Employee</li>
<li>model.Event</li>
<li> model.Library </li>
<li>model.Librarian (sublasa a clasei model.Employee)</li>
<li>model.LibraryDirector (subclasa a clasei model.Employee)</li>
<li>model.ReadWrite (interfarta folosita pentru citire in acest moment)</li>
<li> View.Menu </li>

## Interogari disponibile:
Dupa rulare, vor fi necesare cateva date pentru instantierea clasei biblioteca, dar si a unui obiect de tip director.
In prima parte a meniului utilizatorul va avea obtiunea sa selecteze tipul de utilizator: model.Librarian sau model.Librarian Director(am folosit doar aceste doua optiuni intrucat am gandit aplicatia ca pe una folosita in bibliotecile fizice). Pentru optiunea model.Library Director, utilizatorul va trebui sa introduca o parola :"IAMTHEBOSS". Astfel, in functie de utilizator, vor exista interogari diferite(cu exceptia celor care tin de evenimente).
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