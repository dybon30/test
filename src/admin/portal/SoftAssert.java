package admin.portal;

import java.util.Map;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

public class SoftAssert extends Assertion {

	private Map<AssertionError, IAssert<?>> m_errors = Maps.newHashMap();
	 
	  @Override
	  public void executeAssert(IAssert<?> a) {
	    try {
	      a.doAssert();
	    } catch(AssertionError ex) {
	      m_errors.put(ex, a);
	    }
	  }
	 
	  public void assertAll() {
	    if (! m_errors.isEmpty()) {
	      StringBuilder sb = new StringBuilder("The following asserts failed: ");
	      boolean first = true;
	      for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
	        if (first) {
	          first = false;
	        } else {
	          sb.append(", ");
	        }
	        sb.append(ae.getKey().getMessage());
	      }
	      throw new AssertionError(sb.toString());
	    }
    }
}
