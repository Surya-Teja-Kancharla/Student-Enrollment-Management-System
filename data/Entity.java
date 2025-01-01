package data;

public class Entity 
{
    protected String id;
    protected String name;

    public Entity(String id, String name) 
    {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }
}
