package Server;

public class Event {

    private EventType _type;
    private Object [] _param;
    
    
    public enum EventType {
        packet_recieving,
        end;
    }

    public Event(EventType type , Object... param){
        _type = type;
        _param = param;
    }
    
    public EventType getType(){
        return _type;
    }

    public Object[] getParam(){
        return _param;
    }
}

