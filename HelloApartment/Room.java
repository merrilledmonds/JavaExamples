public class Room{
    private Owner owner;
    // |    |     | These are all arbitrary names, and are picked solely for
    // |    |     | convenience. Good code must be maintainable, maintainable
    // |    |     | code should make sense. Calling this
    // |    |     |     private Human who;
    // |    |     | would make no difference for the machine, but it would for me.
    // |    | In C++, this would be a pointer* to the Owner object in the heap,
    // |    | but Java runs on a Virtual Machine, so having a pointer to a location
    // |    | in memory is unnecessary, so Java uses what they call "references",
    // |    | which are different from C++ "references" in that they are more akin
	// |    | to handles. Note that once the handle points to null, garbage collection
	// |    | kicks in to free memory.
    // | private allows me to impose certain access restrictions on myself (more so
    // | than the program) or anyone else who I want using my code, so that I limit
    // | the points where my code can break. E.g. restricting this now drastically
    // | changes how I write code later. Instead of calling
    // |         myRoom.owner = new Owner();
    // | elsewhere in the code, where I wouldn't normally think of looking when
    // | I go back for my next code update or rework that involves the Room class,
    // | and potentially breaking something because I later decided to change how
    // | I want my program to assign or remove owners, I might instead call
    // |         myRoom.assignOwner(trustees.getChairman());
    // | and assume that the only code that touches the Room's owner field is
    // | the Room class itself.

    private double width;
    private double length;
    
    // Overloaded constructors - might be useful for certain applications,
    // but constructors with fewer arguments might introduce errors down the line
    // if you don't check for null references
    // You can also call the superclass constructor within this constructor
    // for further control, e.g. super(args...); instead of this(args...);
    public Room(){
        this(0, 0, null);
    }
    public Room(double width, double length){
        this(width, length, null);;
    }
    public Room(double width, double length, Owner owner){
        // The only time I'll directly assign these variables is the constructor
        this.width = width;
        this.length = length;
        this.owner = owner;
    }
    
    public double getWidth(){
        // The benefit of having getter methods is similar to the benefits of
        // having setter methods. However, the most important difference is that
        // regardless of the method's signature (specifically, its return type),
        // a getter method allows the abstraction of the actual data into whatever
        // form we want. We could have just as easily not stored the actual width
        // of the room (say, if the Room itself has many Compartments) and instead
        // called a class method that would calculate it for us (e.g. a method
        // like sumCompartmentWidths() or something similar). With a getter method,
        // the internals of the class do not matter to anyone working WITH the class
        // (not working ON the class). Again, we black box the internals, and
        // instead present a method that is guaranteed to give an integer width.
        // However, for this example, we'll just return Room.width:
        return width;
    }
    public void setWidth(double newWidth){
        // The benefit of having setter methods is that you can now manipulate the
        // input - something that wouldn't be guaranteed if Room wasn't an object,
        // and its member variables weren't private. That is to say, separating
        // the assignment as a method (instead of setting .width, for example)
        // allows you to do any number of things, including
        // * input sanitization (e.g. trim, reencode, or cherry-pick stuff)
        // * input validation (e.g. throw errors if provided with bad input,
        //                     or silently substitute a valid value)
        // * user experience "niceties" such as log messages, warnings, etc.
        // * feeding the input into some other function before using it
        // * etc.
        // and allows you to do much more "under the hood" than a simple value
        // change. For example, let us now require our widths to be greater than
        // 0, but less than 100, for the sake of example, and display a warning
        // if the method argument is outside that range. We can then choose how
        // we process the input. Let us say that we wish to use the range as a
        // sort of threshold, and that arguments >100 fall back to 100, and
        // arguments <0 fall back to 0.
        if(newWidth<0){
            System.out.println("Warning: You tried to set the width of a room to <0.");
            System.out.println("         Assuming new width of 0.");
            newWidth = 0;
        }else if(newWidth>100){
            System.out.println("Warning: You tried to set the width of a room to >100.");
            System.out.println("         Assuming new width of 100.");
            newWidth = 100;
        }
        width = newWidth;
    }
    
    public double getLength(){
        return length;
    }
    public void setLength(double newLength){
        length = newLength;
    }
    
    public Owner getOwner(){
        return owner;
    }
    public void setOwner(Owner newOwner){
        owner = newOwner;
    }
	
	// For debugging purposes, let's override the standard Object.toString() method
	// that is called when "casting" the class to a string e.g. when you say
	//         System.out.println("Some String: " + someClassInstance);
	@Override public String toString(){
		return "Room ["+(int)width+"x"+(int)length+" | Sq.ft. "+(int)(width*length)+" | Owner: "+owner+"]";
	}
}