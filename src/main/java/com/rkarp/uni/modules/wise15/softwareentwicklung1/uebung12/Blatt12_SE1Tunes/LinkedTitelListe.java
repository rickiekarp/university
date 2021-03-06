package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung12.Blatt12_SE1Tunes;

/**
 * Diese Klasse implementiert das Interface TitelListe mit einer doppelt verketteten Liste.
 *
 * @author Till Aust
 * @author Axel Schmolitzky
 * @author Petra Becker-Pechau
 * @author Alexander Pokahr
 * @author Christian Spaeh
 * @author Fredrik Winkler
 * @version WiSe 2013/14
 */
public class LinkedTitelListe implements TitelListe
{
    // Der Kopf der verketteten Liste
    private DoppellinkKnoten _listenkopf;

    // Das Ende der verketteten Liste
    private DoppellinkKnoten _listenende;

    // Die logische Laenge der Liste (Kardinalitaet).
    private int _anzahlTitel;

    /**
     * Initialisiert eine neue LinkedTitelListe.
     */
    public LinkedTitelListe()
    {
        // Diese Implementierung verwendet zwei Waechter-Knoten,
        // einen fuer den Listenanfang, einen fuer das Ende.
        // Sie markieren technisch die Grenzen der Liste und enthalten keine Titel.
        // Sie erleichtern das Einfuegen und Entfernen von Titeln,
        // weil viele Sonderfaelle entfallen.
        _listenkopf = new DoppellinkKnoten();
        _listenende = new DoppellinkKnoten();
        _listenkopf.setzeNachfolger(_listenende);
        _listenende.setzeVorgaenger(_listenkopf);
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

        // Prüfe ob der einzufügende Titel gültig ist
        if (!titel.gibTitelname().isEmpty() && position >= 0) {
            // Erzeuge neues Listenelement
            DoppellinkKnoten neuerKnoten = new DoppellinkKnoten();
            neuerKnoten.setzeTitel(titel);

            // Erster Eintrag der Liste
            if(_anzahlTitel == 0){
                neuerKnoten.setzeNachfolger(_listenende);
                neuerKnoten.setzeVorgaenger(_listenkopf);
                _listenkopf.setzeNachfolger(neuerKnoten);
                _listenende.setzeVorgaenger(neuerKnoten);
            } else {
                // Setze Position des aktuellen Elementes
                DoppellinkKnoten knoten = _listenkopf;
                for(int i = 1; i <= position; i++) {
                    //System.out.println("gib nachfolger " + i + " - " + temp.gibNachfolger().gibTitel());
                    knoten = knoten.gibNachfolger();
                }

                if (position == 0) {
                    // Setze Referenzen an Position = 0
                    knoten = knoten.gibNachfolger();
                    _listenkopf.setzeNachfolger(neuerKnoten);
                    neuerKnoten.setzeVorgaenger(_listenkopf);
                    neuerKnoten.setzeNachfolger(knoten);
                    knoten.setzeVorgaenger(neuerKnoten);
                }
                else
                {
                    // Setze Referenzen an Position != 0
                    neuerKnoten.setzeNachfolger(knoten.gibNachfolger());
                    neuerKnoten.setzeVorgaenger(knoten);
                    knoten.setzeNachfolger(neuerKnoten);
                }
            }
            _anzahlTitel++;
        }
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

        DoppellinkKnoten knoten = _listenkopf;
        for (int i = 0; i < _anzahlTitel; ++i)
        {
            knoten = knoten.gibNachfolger();
            if (knoten.gibTitel().equals(titel)) {
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

        DoppellinkKnoten knoten = _listenkopf;
        for (int i = 0; i <= position; ++i)
        {
            knoten = knoten.gibNachfolger();
        }

        return knoten.gibTitel();
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

        // Setze Position des aktuellen Elementes
        DoppellinkKnoten knoten = _listenkopf;
        for (int i = 0; i <= position; i++)
        {
            knoten = knoten.gibNachfolger();
        }

        // Entferne Element an Position = 0
        if (position == 0) {
            _listenkopf.setzeNachfolger(knoten.gibNachfolger());
        } else {
            knoten.gibVorgaenger().setzeNachfolger(knoten.gibNachfolger());
            knoten.gibNachfolger().setzeVorgaenger(knoten.gibVorgaenger());
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
        int anzahl = _anzahlTitel;
        for (int i = 0; i < anzahl; i++)
        {
            entferne(0);
        }
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
