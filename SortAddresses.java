

import java.util.Comparator;

//**********************IPSorted()****************//

public class SortAddresses implements Comparator<String>, java.io.Serializable
{
 public int compare(String ip1, String ip2) 
 {
  return ip1.compareTo(ip2);
 }
}
