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

    /**
     * Default constructor
     */
    public RdvList() {
        this.list = new LinkedList<Rdv>();
        this.quota = 1000; // TODO push symbolic constant
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
     * TODO: EXO 2.3
     *
     * @param aQuota new value for quota
     * @return true iff the setting is done
     */
    public boolean setQuota(int aQuota) {
        // TODO: EXO 2.3
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
     * TODO: EXO 2.4
     *
     * @param aRdv new appointment
     * @return true iff add done
     */
    public boolean add(Rdv aRdv) {
        // TODO: EXO 2.4
        if (this.quota > Objects.requireNonNull(this.list.size())) {
            this.list.add(aRdv);
            return true;
        }
        return false;
    }

    // TODO: EXO 1.6
    /**
     * Print rendezvous from lists after date @param d
     *
     * @param output output stream
     * @param d starting date
     */
     
    // TODO: EXO 1.7 printFromTo 
    public void printFromTo(PrintStream output, LocalDateTime debut, LocalDateTime end) {
        if (list == null) {
            output.println("Empty list! (quota = " + this.quota + ")");
            return;
        }
        boolean isEmpty = true;
        
        //Print before or after a date if the other date is null
        
        if(debut==null){
			for (Rdv r : this.list) {
				if (r.getStart().isBefore(end)) {
					output.println(r);
					isEmpty = false;
				}
			}
		}
      
		else if(end==null){
			for (Rdv r : this.list) {
				if (r.getStart().isAfter(debut)) {
					output.println(r);
					isEmpty = false;
				}
			}
		}	
			
		//If the rdv is between the dates : print		
        else {
			for (Rdv r : this.list) {
            if (r.getStart().isAfter(debut) && r.getStart().isBefore(end)) {
                output.println(r);
                isEmpty = false;
            }
        }
	}
        if (isEmpty) {
            output.println("No rendez-vous between" + debut + " and "+end);
        }
    }


    // TODO: EXO 1.8 removeFromTo
    public void removeFromTo(LocalDateTime debut, LocalDateTime end){
		
		//Remove before or after a date if the other date is null
		if(debut==null){
			for (Rdv r : this.list) {
				if (r.getStart().isBefore(end)) this.list.remove(r);	
			}
		}
      
		else if(end==null){
			for (Rdv r : this.list) {
				if (r.getStart().isAfter(debut)) this.list.remove(r);
				}
		}
		
		//If the rdv is between the dates : remove	
		else{
			for (Rdv r : this.list) {
				if (r.getStart().isAfter(debut) && r.getStart().isBefore(end)) this.list.remove(r);
			}
		}
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
