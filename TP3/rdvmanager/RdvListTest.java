/*
 * Copyright (C) 2019 sighirea
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
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




/**
 *
 * @author sighirea
 */
public class RdvListTest {

    private RdvList rl;
    
    public RdvListTest() {
    }
    
    
    @BeforeEach
    public void createList() {
        rl=new RdvList();
        rl.setQuota(3);
    }
    
    /**
     * Test of getQuota method, of class RdvList. 
     */
    @Test
    public void testGetQuota() {
        RdvList instance = new RdvList();
        assertEquals(RdvList.DEFAULT_QUOTA, instance.getQuota());
    }

    /**
     * Add some Rdvs to a list
     * @param rl The list
     */
    void addThreeRdv() {
        rl.add(new Rdv("Rdv1",LocalDate.of(2020, 01, 01),LocalTime.of(12,00),30));
        rl.add(new Rdv("Rdv2",LocalDate.of(2020, 01, 02),LocalTime.of(12,00),30));
        rl.add(new Rdv("Rdv3",LocalDate.of(2020, 01, 03),LocalTime.of(12,00),30));
    }
    
    //List to test add method
    void addTest() {
    	RdvList test = new RdvList();
    	test.add(new Rdv("Rdv1",LocalDate.of(2020, 01, 01),LocalTime.of(12,00),30));
    	test.add(new Rdv("Rdv2",LocalDate.of(2020, 01, 01),LocalTime.of(13,00),90));
    	test.add(new Rdv("Rdv2",LocalDate.of(2020, 01, 01),LocalTime.of(13,30),30));
    }

    /**
     * Test of setQuota method, of class RdvList.
     */
    @Test
    public void testSetQuotaBelowExisting() {
        addThreeRdv();
        assertFalse(rl.setQuota(2));
    }
    
    
    @Test
    //TP3 Q5
    public void testAdd(){
    	Exception e;
    	e=assertThrows(RuntimeException.class, 
                () -> { addTest();}
                );
        assertTrue(e.getMessage().contains("The Rdv you try to add overlaps an existing one"));
    }

    @Test
    public void testSetQuotaEqualExisting() {
        addThreeRdv();
        assertTrue(rl.setQuota(3));
    }
    @Test
    public void testSetQuotaOverExisting() {
        addThreeRdv();
        assertTrue(rl.setQuota(4));
    }
    
    @Test
    public void testQuotaChecked() {
        rl.setQuota(2);
        assertTrue(rl.add(new Rdv("Rdv1",LocalDate.of(2020, 01, 01),LocalTime.of(12,00),30)));
        assertTrue(rl.add(new Rdv("Rdv2",LocalDate.of(2020, 01, 02),LocalTime.of(12,00),30)));
        assertFalse(rl.add(new Rdv("Rdv3",LocalDate.of(2020, 01, 03),LocalTime.of(12,00),30)));
    }
}
