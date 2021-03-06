package net.krinsoft.teleportsuite;

/**
 * @author krinsdeath
 */
public class Request {
    public enum Type {
        TPA,
        TPAHERE,
        TP,
        TPHERE,
        TPO,
        TPOHERE;
        
        public String getName() {
            return name().toLowerCase();
        }
    }

    long timeout;
    TeleportPlayer dest;
    Type type;
    
    public Request(TeleportPlayer player, Type request) {
        this.timeout = System.currentTimeMillis();
        this.dest = player;
        this.type = request;
    }

    public long getDuration() {
        return System.currentTimeMillis() - timeout;
    }

    public TeleportPlayer getTo() { return this.dest; }
    
    public Type getType() { return this.type; }
    
    @Override
    public boolean equals(Object that) {
        return this == that || this.getClass() == that.getClass() && this.dest.getName().equals(((Request) that).dest.getName());
    }
    
}
