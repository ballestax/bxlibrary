/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lrod
 */
public class StringUtilTest {
    
    public StringUtilTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of randomString method, of class StringUtil.
     */
    @Test
    public void testRandomString() {
        
        String randomString = StringUtil.randomString(5);
        
        System.out.println(randomString);
        
        assertNotNull(randomString);
        
        assertEquals(randomString.length(), 5);
        
        
    
    }

    
    
}
