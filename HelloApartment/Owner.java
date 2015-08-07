public class Owner{
    // Why decouple something as simple as a room's "owner" into its own class?
    //
    // You don't need to - a string might be fine. But if you foresee a need for
    // more complicated bookkeeping in the future (e.g. owners might need to be
    // referenced for other things, such as a homeowners assn. board position, or
    // you might want to keep track of one person's entire real estate portfolio
    // or something), it might be beneficial to create a new class for "owners"
    // and reference them elsewhere.
    // The added benefit is that you just added a layer of abstraction for your
    // own benefit: You no longer have to remember how you kept track of the
    // owners, or what data structure you used to store their names, phone
    // numbers, etc. That is now all hidden behind a layer of abstraction by
    // simply having created this class. Something as unintuitive as
    //         //Mental note: John Doe's location in the owners array is 5
    //         //Mental note: Contact information is stored in the 4th position
    //         //             of the owner array... or was it 3?
    //         //Mental note: Contact information went... address, phone number,
    //         //             email, then work phone. No, it went phone number,
    //         //             address....
    //         System.out.println(owners[5][3][0]);
    // can instead become the much more intuitive, much clearer, object oriented
    // version:
    //         // contactManager is a class that handles the owner "database"
    //         // getByName is a method of contactManager that returns
    //         // the corresponding Owner object
    //         // IntelliSense should automatically include getByName
    //         someOwner = contactManager.getByName("John Doe");
    //         // getPhoneNumber is a method of Owner that returns a phone number
    //         // IntelliSense should automatically include getPhoneNumber
    //         someOwner.getPhoneNumber();
    // and for the most part, you won't need to recall the inner workings of the
    // class. You have essentially created a black box, and you can use that
    // with the assumption that .getName() will give you a String, regardless of
    // how you decide to store names down the line, and that .getPhoneNumber()
    // might give you an String, regardless of how you might store phone numbers
    // down the line.
    
    private String name;
    private Double phone;
    
    public Owner(){
        this("No Owner", (Double)null);
    }
    public Owner(String name){
        this(name, (Double)null);
    }
    public Owner(String name, double phone){
        this(name, new Double(phone));
    }
    public Owner(String name, String phone){
        this(name, Double.valueOf(phone));
    }
    public Owner(String name, Double phone){
        this.name = name;
        this.phone = phone;
    }
    
    
    public String getName(){
        return name;   
    }
    public void setName(String newName){
        name = newName;   
    }
    public String getPhone(){
        if(phone!=null){
            return String.format("%4.0f",phone);
        }else{
            return "Unknown";
        }
    }
    public void setPhone(String newPhone){
        // Here, you'd do validation and sanitization, but I won't
        phone = Double.valueOf(newPhone);
    }
    public void clearPhone(){
        phone = null;
    }
	
	// For debugging purposes, let's override the standard Object.toString() method
	// that is called when "casting" the class to a string e.g. when you say
	//         System.out.println("Some String: " + someClassInstance);
	@Override public String toString(){
		return "Owner ["+name+" | "+getPhone()+"]";
	}

}