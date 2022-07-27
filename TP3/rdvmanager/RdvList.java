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

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Manager of an appointment list.
 *
 * @version 0.1
 * @author M. Sighireanu
 * @author YOU
 */
public class RdvList {

    private LinkedList<Rdv> list;
    private int quota;
    static final int DEFAULT_QUOTA=100;

    /**
     * Default constructor
     */
    public RdvList() {
        this.list = new LinkedList<Rdv>();
        this.quota = DEFAULT_QUOTA; 
    }

    /**
     * Get the quota
     *
     * @return the current quota
     */
    public int getQuota() {
        return quota;
    }

    /**
     * Set a new quota if bigger than the size of the list
     *
     * @param aQuota new value for quota
     * @return true iff the setting is done
     */
    public boolean setQuota(int aQuota) {
        // TODO: for Java 11 use the line below
        // if (aQuota < Objects.requireNonNullElse(this.list.size(), this.quota))
        if (aQuota < Objects.requireNonNull(this.list.size())) {
            return false;
        }
        this.quota = aQuota;
        return true;
    }

    /**
     * Add an appointment in the list
     *
     * @param aRdv new appointment
     * @return true iff add done
     */
    public boolean add(Rdv aRdv) {
        // TODO: EXO 2.4
        
    	if (this.quota > Objects.requireNonNull(this.list.size())) {
        //check if the new rdv does not overlap an other in the list
			for(Rdv r : this.list){
				if(aRdv.overlap(r)){
					throw new RuntimeException("The Rdv you try to add overlaps an existing one");
				}
			}
				this.list.add(aRdv);
				return true;
			}
			return false;
	}

    /**
     * Print rendezvous from lists after date @param d
     *
     * @param output output stream
     * @param d starting date
     */
    public void printFrom(PrintStream output, LocalDateTime d) {
        if (list == null) {
            output.println("Empty list! (quota = " + this.quota + ")");
            return;
        }
        boolean isEmpty = true;
        LocalDate day = d.toLocalDate();
        for (Rdv r : this.list) {
            if (r.getStart().toLocalDate().equals(day) && r.getStart().isAfter(d)) {
                output.println(r);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            output.println("No rendez-vous after " + d);
        }
    }

    public int printFromTo(PrintStream output, LocalDateTime from, LocalDateTime to) {
        if (list == null) {
            output.println("Empty list! (quota = " + this.quota + ")");
            return 0;
        }
        int count=0;
        for (Rdv r : this.list) {
            if (r.startsBetween(from, to)) {
                output.println(r);
                count++;
            }
        }
        if (count==0) {
            output.println("No rendez-vous between " + from + " and " + to + ".");
        }
        return count;
    }

    public void removeFromTo(PrintStream output, LocalDateTime from, LocalDateTime to) {
        int removedCount = 0;
        if (list != null) {
            for (Rdv rdv : this.list) {
                if (rdv.startsBetween(from, to)) {
                    list.remove(rdv);
                    removedCount++;
                }
            }
        }
        output.println(
                (removedCount == 0 ? "No" : removedCount) + "No rendez-vous between " + from + " and " + to + ".");
    }


    /**
     * Save the list in CSV format
     *
     * @param output output stream
     */
    public void saveCSV(PrintStream output) {
        output.println(";" + this.quota);
        for (Rdv rdv : this.list) {
            output.println(rdv.toCSV());
        }
    }
}
