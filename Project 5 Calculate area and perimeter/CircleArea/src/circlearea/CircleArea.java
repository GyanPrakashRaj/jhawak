
package circlearea;

/**
 *
 * @author GYAN PRAKASH RAJ
 */
import java.io.*;
import java.lang.*;
public class CircleArea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int r =0;
            try{
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Radius of Circle  : ");
		r = Integer.parseInt(br1.readLine());
		double area = Math.PI*r*r;
		System.out.println("Area of Circle : "+area);
		double	perimeter =2*Math.PI*r ;
		System.out.println("Perimeter of Circle : "+perimeter);          
                       
            }
            catch(Exception e){
              System.out.println("Error :  "+e);
            }
        
    }
    
}
