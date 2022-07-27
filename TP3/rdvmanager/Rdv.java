/*
 * Copyright (C) 2019 EIDD 2A SIE
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package rdvmanager;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A <code>Rdv</code> object represents an appointment with a date, a
 * description and a span.
 *
 * @version 0.1
 * @author M. Sighireanu
 * @author YOU
 */
public class Rdv {

	final static int MIN_YEAR=2020;
    final static int MAX_YEAR=2020;
    final static int MAX_SPAN=60*12;
	
    private String description="************";
    private LocalDateTime start=null;
    private int spanMinutes=0;

    /**
     * Constructor of a rendezvous from description, date, time and span.
     *
     * @param aDescription short text
     * @param aYear the year from from MIN_YEAR to MAX_YEAR
     * @param aMonth the month of year from 1 to 12
     * @param dayOfMonth the day-of-month from 1 to 31
     * @param aHour the hour-of-day from 0 to 23
     * @param aMinute the time of a day from 0 to 59
     * @param aSpan the duration in minutes
     */
    public Rdv(String aDescription, int aYear, int aMonth, int aDayOfMonth, int aHour, int aMinute, int aSpan) {
        if ((aYear < MIN_YEAR) || (aYear > MAX_YEAR))
            throw new IllegalArgumentException("Year out of range");
        if (aDescription == null || aDescription.trim().equals(""))
            throw new IllegalArgumentException("No description given");
        if (aSpan > MAX_SPAN)
            throw new IllegalArgumentException("Span too long");
        description = aDescription;
        spanMinutes = aSpan;
        // The rest of the arguments are checked by the LocalDateTime code
        // But we rethrow the exception so that an error rethrows the same exception as the others 
        try {
            start = LocalDateTime.of(aYear, aMonth, aDayOfMonth, aHour, aMinute);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid date", e);
        }
    }

    /**
     * Constructor of a rendezvous from description, date, time and span.
     *
     * @param aDescription short text
     * @param aDate the start date
     * @param aTime the start time
     * @param aSpan the duration in minutes
     */
    public Rdv(String aDescription,
            LocalDate aDate, LocalTime aTime, int aSpan) {
    	if ((aDate.getYear()<MIN_YEAR) || (aDate.getYear()>MAX_YEAR)) throw new RuntimeException("Year out of range");
        if (aDescription == null || aDescription.trim().equals(""))
            throw new RuntimeException("No description given");
        this.description = aDescription;
			this.start = LocalDateTime.of(aDate, aTime);
		this.spanMinutes = aSpan;
    }

    /**
     * Get description of the appointment.
     *
     * @return the text describing the rendezvous
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get starting time
     *
     * @return the text describing the rendezvous
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Get duration
     *
     * @return the span
     */
    public int getSpan() {
        return this.spanMinutes;
    }

    /**
     * Print to string
     *
     * @return the string of this rendezvous
     */
    public String toString() {
        return this.start.toLocalDate() + " "
                + this.start.toLocalTime() + " "
                + "(" + this.spanMinutes + "min)"
                + ": " + this.description;
    }
    
	//true if the rdv argument overlaps the instance one
	public boolean overlap(Rdv r){
		LocalDateTime end= this.start.plusMinutes(this.spanMinutes);
		LocalDateTime endR = r.start.plusMinutes(r.spanMinutes);
		
		
		if((r.start.isBefore(end) && (r.start.isAfter(this.start))) || (endR.isAfter(this.start) && endR.isBefore(end)) || ((this.startsBetween(r.start, endR) && this.endBetween(r.start, endR)))) return true;
		
		return false;
	}    

    public boolean isAtTheSameTimeAs(Rdv another) {
    	return start.equals(another.start);
    }
    
    // DRY principle for the *FromTo methods in RdVList
    public boolean startsBetween(LocalDateTime from, LocalDateTime to) {
    	return (from==null || start.isAfter(from)) && (to==null || start.isBefore(to));
    }
    
    //DRY principle for the *FromTo methods in RdVList
    public boolean endBetween(LocalDateTime from, LocalDateTime to) {
    	LocalDateTime end= this.start.plusMinutes(this.spanMinutes);
    	return (from==null || end.isAfter(from)) && (to==null || end.isBefore(to));
    }
    
    /**
     * Print to CSV line
     *
     * @return the string of this rendezvous
     */
    public String toCSV() {
        return this.start.toLocalDate() + ";"
                + this.start.toLocalTime() + ";"
                + this.spanMinutes + ";"
                + this.description;
    }
}
