
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double min, double max) {
        this.minDepth = min;
        this.maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth);
    }
    
    public String getName()
    {
        return "getName nije implementiran";
    }
}
