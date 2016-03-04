
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    
    private double minMagnitude;
    private double maxMagnitude;
    
    public MagnitudeFilter(double min, double max) {
        this.minMagnitude = min;
        this.maxMagnitude = max;
    }

    public boolean satisfies(QuakeEntry qe){
        double depth = qe.getMagnitude();
        return(depth >= minMagnitude && depth <= maxMagnitude);
    }
    
    public String getName() {
        return "getName nije implementiran";
    }
}
