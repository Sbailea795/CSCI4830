

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class getsetTest{
	
	 GetProfAss getProfAss;

	    @BeforeEach                                         
	    void getUp() {
	    	getProfAss = new GetProfAss();
	    }

	    @Test                                               
	    @DisplayName("Simple addition")   
	    void testGet() {
	        //assertEquals(20, getProfAss.doGet(),     
	        //        "Adding reviews should work");  
	    }

	SetProfAss setProfAss;
		
		@BeforeEach                                         
		void setUp() {
			setProfAss = new SetProfAss();
		}
		
		@Test                                               
		@DisplayName("Simple addition")   
		void testSet() {
		    //assertEquals(20, getProfAss.doGet(),     
		    //        "look up of users should work");  
		}

}