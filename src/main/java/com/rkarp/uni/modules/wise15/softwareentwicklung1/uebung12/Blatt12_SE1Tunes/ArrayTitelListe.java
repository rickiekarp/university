package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung12.Blatt12_SE1Tunes;

/**
 * Diese Klasse implementiert das Interface TitelListe mit "wachsenden" Arrays.
 * 
 * @author Till Aust
 * @author Axel Schmolitzky
 * @author Petra Becker-Pechau
 * @author Alexander Pokahr
 * @author Christian Spaeh
 * @author Fredrik Winkler
 * @version WiSe 2013/14
 */
public class ArrayTitelListe implements TitelListe
{
    // In diesem Array sind die Referenzen auf die enthaltenen Titel abgelegt.
    // Die Laenge des Arrays entspricht der Kapazitaet der Liste und muss daher nicht separat gespeichert werden.
    private Titel[] _titelArray;

    // Die Kardinalitaet der Liste.
    private int _anzahlTitel;

    // Die Anfangskapazitaet einer jeden neuen Liste.
    private static final int ANFANGSKAPAZITAET = 10;

    /**
     * Initialisiert eine neue <code>ArrayTitelListe</code>.
     */
    public ArrayTitelListe()
    {
        _titelArray = new Titel[ANFANGSKAPAZITAET];
        _anzahlTitel = 0;
    }

    /**
     * Aufg 12.1.4
     * Initialisiert eine neue <code>ArrayTitelListe</code> mit Angabe einer Anfangskapazität.
     */
    public ArrayTitelListe(int kapazitaet)
    {
        _titelArray = new Titel[kapazitaet];
        _anzahlTitel = 0;
    }

    /**
     * Fuege einen Titel an der Position <code>position</code> in die Titelliste
     * ein. Alle folgenden Eintraege werden um eine Position verschoben.
     * Wenn <code>position</code> gleich der Laenge der Titelliste ist, dann
     * fuege den <code>titel</code> am Ende an.
     * 
     * @param titel Der einzufuegende Titel (darf nicht null sein).
     * @param position Die Position, an welcher der Titel eingefuegt werden soll.
     */
    public void fuegeEin(Titel titel, int position)
    {
        darfNichtNullSein(titel);
        mussGueltigeEinfuegepositionSein(position);

        //Überprüfe ob _titelArray (fast) voll ist und vergrößere es
        if (_anzahlTitel == _titelArray.length - 1) {
            Titel[] temp = new Titel[2*_anzahlTitel];
            //Schreibe alle Elemente in das neue Array
            for (int i = 0; i < _titelArray.length; i++) {
                temp[i] = _titelArray[i];
            }
            _titelArray = temp;
        }

        //Überprüfe ob das element an der position eingefügt werden kann
        if (_titelArray[position] != null) {
            Titel[] temp2 = new Titel[_titelArray.length];
            int count = 0; // Schritte bis eine freie Position vorhanden ist
            for (int i = position; i < temp2.length; i++) {
                temp2[count] = _titelArray[i];
                if (_titelArray[i] != null)  {
                    count++;
                }
            }

            //setze element an die position
            _titelArray[position] = titel;

            //füge elemente aus temporärem array ein
            for (int i = 0; i < count; i++) {
                _titelArray[position + i + 1] = temp2[i];
            }
        } else {
            _titelArray[position] = titel;
        }
        _anzahlTitel++;
    }

    /**
     * Pruefe, ob ein Titel in der Liste enthalten ist.
     * 
     * @param titel Der Titel, welcher in der Liste gesucht werden soll.
     * @return <code>true</code> wenn der Titel in der Liste ist,
     *         ansonsten <code>false</code>.
     */
    public boolean enthaelt(Titel titel)
    {
        darfNichtNullSein(titel);

        for (Titel aTitel : _titelArray) {
            if (aTitel != null && aTitel.equals(titel)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gib den Titel an der angegebenen Position zurueck.
     * 
     * @param position Die Position des Titels, der zurueckgeben werden soll.
     * @return Der Titel an der Position <code>position</code>.
     */
    public Titel gibTitel(int position)
    {
        mussGueltigePositionSein(position);
        return _titelArray[position];
    }

    /**
     * Entferne den Titel an der angegebenen Position. Alle folgenden Eintraege
     * werden um eine Position verschoben.
     * 
     * @param position Die Position des Titels, der entfernt werden soll.
     */
    public void entferne(int position)
    {
        mussGueltigePositionSein(position);

        _titelArray[position] = null;
        for (int i = position; i < _titelArray.length - 1; i++) {
            _titelArray[i] = _titelArray[i+1];
        }
        _anzahlTitel--;
    }

    /**
     * Gib die Laenge der Liste zurueck.
     * 
     * @return Anzahl der Titel in der Liste.
     */
    public int gibLaenge()
    {
        return _anzahlTitel;
    }

    /**
     * Entferne alle Titel aus der Liste.
     */
    public void leere()
    {
        for (int i = 0; i < _titelArray.length; i++) {
            _titelArray[i] = null;
        }
        _anzahlTitel = 0;
    }

    /**
     * Schreibt den Array-Inhalt auf die Konsole (als Debugging-Hilfe gedacht).
     */
    public void schreibeAufKonsole()
    {
        System.out.println(java.util.Arrays.toString(_titelArray));
    }

    /**
     * Liefert true fuer alle gueltigen Positionen innerhalb der Liste.
     */
    public boolean istGueltigePosition(int position)
    {
        return (position >= 0) && (position < gibLaenge());
    }

    /**
     * Wirft eine IndexOutOfBoundsException, falls es sich um eine ungueltige Position handelt.
     */
    private void mussGueltigePositionSein(int position)
    {
        if (!istGueltigePosition(position))
        {
            throw new IndexOutOfBoundsException(position + " ist keine gueltige Position");
        }
    }
    
    /**
     * Liefert true fuer alle gueltigen Einfuegepositionen innerhalb der Liste.
     */
    public boolean istGueltigeEinfuegeposition(int position)
    {
        return (position >= 0) && (position <= gibLaenge());
    }

    /**
     * Wirft eine IndexOutOfBoundsException, falls es sich um eine ungueltige Einfuegeposition handelt.
     */
    private void mussGueltigeEinfuegepositionSein(int position)
    {
        if (!istGueltigeEinfuegeposition(position))
        {
            throw new IndexOutOfBoundsException(position + " ist keine gueltige Einfuegeposition");
        }
    }
    
    /**
     * Wirft eine IllegalArgumentException, falls die uebergebene Titel-Referenz null ist.
     */
    private static void darfNichtNullSein(Titel titel)
    {
        if (titel == null)
        {
            throw new IllegalArgumentException("Die Titel-Referenz darf nicht null sein.");
        }
    }
}
