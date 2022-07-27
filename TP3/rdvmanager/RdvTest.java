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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @version 0.2
 * @author B. Denoix
 * @author YOU
 */
public class RdvTest {

    private Rdv rdv;
    final static LocalDate testDate= LocalDate.of(2020, 10, 1);
    final static LocalTime testTime= LocalTime.of(14, 20);
    final static String testDescription="test description";
    final static int testSpan=30;
    

    public RdvTest() {
    }
    
    @BeforeEach
    public void beforeEach() {
        rdv=new Rdv(testDescription,testDate,testTime,testSpan);
    }

    /**
     * Test of getDescription method, of class Rdv.
     */
    @Test
    public void testGetDescription() {
        assertEquals(testDescription, rdv.getDescription());
    }

    /**
     * Test of getStart method, of class Rdv.
     */
    @Test
    public void testGetStart() {
        assertEquals(LocalDateTime.of(testDate,testTime),rdv.getStart());
    }

    /**
     * Test of toString method, of class Rdv.
     */
    @Test
    public void testToString() {
        assertEquals("2020-10-01 14:20 (30min): test description", rdv.toString());
    }
    
    @Test
    public void testException() {
        Exception e;
        e=assertThrows(IllegalArgumentException.class, 
                () -> { new Rdv("Some rendez-vous",2015,12,10,12,00,60);}
                );
        assertTrue(e.getMessage().contains("Year out of range"));
        e=assertThrows(IllegalArgumentException.class, 
                () -> { new Rdv("Some rendez-vous",2020,2,31,12,00,60);}
                );
        assertTrue(e.getMessage().contains("Invalid date"));
        
        //TP3 Q4 : Test method constructor 2
        
			//Test year
        e=assertThrows(RuntimeException.class,
				() -> { new Rdv("Some rendez-vous",LocalDate.of(2018,11,02),LocalTime.of(12,00),10);}
				);
		assertTrue(e.getMessage().contains("Year out of range"));
		
			//Test empty description
		e=assertThrows(RuntimeException.class,
				() -> { new Rdv("",LocalDate.of(2020,11,02),LocalTime.of(12,00),10);}
				);
		assertTrue(e.getMessage().contains("No description given"));
		
	/*	//Test invalid date format
        e=assertThrows(RuntimeException.class,
				() -> { new Rdv("Some rendez-vous",LocalDate.of(2020,12,02),LocalTime.of(25,00),10);}
				);
		assertTrue(e.getMessage().contains("Error in date format")); */

    }
    
    //TP3 Q4 : Test method constructor
    
    //TP3 Q4 : Test method overlap
    @Test
    public void testOverlap(){
		/*Rdv instance = new Rdv("Test", LocalDate.of(2020, 10, 1), LocalTime.of(10, 30),90);
		Rdv test = new Rdv("Test", LocalDate.of(2020, 10, 1), LocalTime.of(11, 30), 60);
		boolean expResult = true;
		boolean result = instance.overlap(test);
		assertEquals(expResult, result);*/ 
    	assertTrue(rdv.overlap(new Rdv("Desc1",2020,10,1,14,20,10)));
        assertTrue(rdv.overlap(new Rdv("Desc2",2020,10,1,14,15,10)));
        assertTrue(rdv.overlap(new Rdv("Desc3",2020,10,1,14,10,60)));
        assertTrue(rdv.overlap(new Rdv("Desc4",2020,10,1,14,25,5)));
        assertTrue(rdv.overlap(new Rdv("Desc5",2020,10,1,14,40,10)));
        assertFalse(rdv.overlap(new Rdv("Desc6",2020,10,1,14,50,10)));
	}
}
