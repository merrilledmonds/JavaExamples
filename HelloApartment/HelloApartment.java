import java.util.*;

public class HelloApartment{

     public static void main(String []args){
        System.out.println("Hello World");
        
		// Test the Room and Owner classes
        Room myRoom = new Room();
        System.out.println("Owner is currently: "+myRoom.getOwner());
        myRoom.setOwner(new Owner());
		System.out.println("Owner is currently: "+myRoom.getOwner());
		myRoom.getOwner().setName("John Doe");
		myRoom.getOwner().setPhone("2125551234");
		System.out.println("Owner is currently: "+myRoom.getOwner());
		
		// Release myRoom for garbage collection
		// I'm used to manual memory management from C++, so not entirely necessary for Java
		myRoom = null;
		
		// Now, create an "apartment." This could be its own class, but for the sake
		// of simplicity, let's just use an ArrayList.
		ArrayList<Room> apartment = new ArrayList<>();
		
		int numberOfRooms = 15;
		Owner management = new Owner("Management",new Double(5550000000L));
		for(int iter = 0; iter<numberOfRooms; iter++){
			// Let's randomly assign "rooms" (min. 2'x2') to management, or John Doe
			if(Math.random()>0.5){
				// Make room sizes incremental, so we can distinguish them
				apartment.add(new Room(7*iter+2, 3*iter+2, management));
			}else{
				// Create a random phone number from 1000000000-99999999999
				double randomPhone = Math.random()*(double)8999999999L + (double)1000000000L;
				apartment.add(new Room(10*iter+2, 5*iter+2, new Owner("John Doe "+iter,randomPhone)));
			}
		}
		
		// Now let's find the total square footage of the "apartment",
		// and since we're already iterating over the ArrayList, let's
		// print out some details for each room to double check
		double totalSqFt = 0;
		for(Room room : apartment){
			System.out.println("Room : "+room);
			totalSqFt += room.getLength()*room.getWidth();
		}
		// Note that rooms owned by the management will all reference the same Owner
		// object - the "Management" Owner object. Therefore, changes made to the 
		// object will show up for all of the Rooms. This allows for an amazing
		// amount of flexibility when compared to functional programming. Instead of
		// trying to keep track of which array index corresponds to which array index
		// and what the value of the element at that index corresponds to in another
		// array, etc... You just reference the actual object, and any changes you make
		// are immediately avaliable and intuitively accessible to every programmer
		// working on the project.
		System.out.println("Total Square Footage for Apartment: "+totalSqFt);
		
     }
}
