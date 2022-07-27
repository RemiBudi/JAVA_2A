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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author sighirea
 */
public class RdvManagerTest {
    
    public RdvManagerTest() {
    }
    
    /**
     * Test of getUserName method, of class RdvManager.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        RdvManager instance = new RdvManager("Dupont"); // null;
        String expResult = "Dupont";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }
    
}
