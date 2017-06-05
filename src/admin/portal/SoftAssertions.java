package admin.portal;

import org.junit.Assert;

	//Implementation Of Soft Assertion 
	public class SoftAssertions{
		
		public void assertEquals(String expected, String actual){
			try{
				Assert.assertEquals(expected, actual);
			}catch(AssertionError e){
				log("mymessage: "+e.getMessage());
			    throw new AssertionError(e);
			}
		}
		
		public void assertTrue(boolean str){
			try{
				Assert.assertTrue(str);
			}catch(AssertionError e){
				log("mymessage: "+e.getMessage());
			    throw new AssertionError(e);
			}
		}

		private void log(String string) {
			// TODO Auto-generated method stub
			
		}

		
		/*@Override public void executeAssert(Assert a){ 
			try{ 
				a.doAssert(); 
			}catch(AssertionError ex){ 
				System.out.println(a.getMessage()); 
			} 
		}*/

} 
